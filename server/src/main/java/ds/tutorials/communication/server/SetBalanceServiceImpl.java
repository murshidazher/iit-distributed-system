package ds.tutorials.communication.server;

import ds.tutorial.communication.grpc.generated.SetBalanceRequest;
import ds.tutorial.communication.grpc.generated.SetBalanceResponse;
import ds.tutorial.communication.grpc.generated.SetBalanceServiceGrpc;
import ds.tutorials.synchronization.lock.tx.DistributedTxCoordinator;
import ds.tutorials.synchronization.lock.tx.DistributedTxListener;
import ds.tutorials.synchronization.lock.tx.DistributedTxParticipant;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class SetBalanceServiceImpl extends SetBalanceServiceGrpc.SetBalanceServiceImplBase implements DistributedTxListener {
  private ManagedChannel channel = null;
  SetBalanceServiceGrpc.SetBalanceServiceBlockingStub clientStub = null;
  private BankServer server;

  // mimics to hold the data temporarily until committed to the db like a file, log etc..
  private Map.Entry<String, Double> tempDataHolder;
  private boolean transactionStatus = false;

  public SetBalanceServiceImpl(BankServer server) {
    this.server = server;
  }

  // this is not a read operation but a write operation so we need to be consistent
  @Override
  public void
  setBalance(ds.tutorial.communication.grpc.generated.SetBalanceRequest request,
             io.grpc.stub.StreamObserver<ds.tutorial.communication.grpc.generated.SetBalanceResponse> responseObserver) {
    String accountId = request.getAccountId();
    double value = request.getValue();

    transactionStatus = false;

    if (server.isLeader()) {
      // Act as primary
      // if you are leader you need to check the other, you can update it
      // then notify all the secondary servers
      try {
        System.out.println("Updating account balance as Primary");
        startDistributedTx(accountId, value);
        updateSecondaryServers(accountId, value); // vote request is send from this
        System.out.println("going to perform");

        transactionStatus = true;

        if (value > 0){
          ((DistributedTxCoordinator)server.getTransaction()).perform();
        } else {
          ((DistributedTxCoordinator)server.getTransaction()).sendGlobalAbort();
        }
      } catch (Exception e) {
        System.out.println("Error while updating the account balance" + e.getMessage());
        e.printStackTrace();
      }
    } else {
      // Act As Secondary
      if (request.getIsSentByPrimary()) {
        // the leader can send the secondary service a request to update it balance
        System.out.println("Updating account balance on secondary, on Primary's command");
        startDistributedTx(accountId, value); // as a secondary and join for the transaction

        // dummy validation to commit or abort if value is 0 abort else commit
        if (value != 0.0d) {
          ((DistributedTxParticipant)server.getTransaction()).voteCommit();
        } else {
          ((DistributedTxParticipant) server.getTransaction()).voteAbort();
        }
      } else {
        // if the client call the secondary service, we route it to the primary server.
        // we will update if the request was acknowledged.
        SetBalanceResponse response = callPrimary(accountId, value);
        if (response.getStatus()) {
          transactionStatus = true;
        }
      }
    }
    SetBalanceResponse response = SetBalanceResponse
      .newBuilder()
      .setStatus(transactionStatus)
      .build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  // we will only update the data when the transaction commit is confirmed until then its hold in temp
  private void updateBalance() {
    if (tempDataHolder != null) {
      String accountId = tempDataHolder.getKey();
      double value = tempDataHolder.getValue();
      server.setAccountBalance(accountId, value);
      System.out.println("Account " + accountId + "updated to value " + value + " committed");
      tempDataHolder = null;
    }
  }

  private SetBalanceResponse callServer(String accountId, double value, boolean isSentByPrimary, String IPAddress, int port) {
    System.out.println("Call Server " + IPAddress +
      ":" + port);
    channel = ManagedChannelBuilder.forAddress(IPAddress, port)
      .usePlaintext()
      .build();
    clientStub = SetBalanceServiceGrpc.newBlockingStub(channel);
    SetBalanceRequest request = SetBalanceRequest
      .newBuilder()
      .setAccountId(accountId)
      .setValue(value)
      .setIsSentByPrimary(isSentByPrimary)
      .build();

    SetBalanceResponse response = clientStub.setBalance(request);
    return response;
  }

  private SetBalanceResponse callPrimary(String accountId, double value) {
    System.out.println("Calling Primary server");
    String[] currentLeaderData = server.getCurrentLeaderData();
    String IPAddress = currentLeaderData[0];
    int port = Integer.parseInt(currentLeaderData[1]);
    return callServer(accountId, value, false, IPAddress, port);
  }

  private void updateSecondaryServers(String accountId, double value) throws KeeperException, InterruptedException {
    System.out.println("Updating secondary servers");
    List<String[]> othersData = server.getOthersData();
    for (String[] data : othersData) {
      String IPAddress = data[0];
      int port = Integer.parseInt(data[1]);
      callServer(accountId, value, true, IPAddress, port);
    }
  }

  private void startDistributedTx(String accountId, double value) {
    try {
      server.getTransaction().start(accountId, String.valueOf(UUID.randomUUID()));
      tempDataHolder = new AbstractMap.SimpleEntry<>(accountId, value);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // we might flush the buffer and commit to disk
  @Override
  public void onGlobalCommit() {
    updateBalance();
  }

  // we might discard the buffer
  @Override
  public void onGlobalAbort() {
    tempDataHolder = null;
    System.out.println("Transaction Aborted by the Coordinator");
  }
}
