package ds.tutorials.communication.server;

import ds.tutorial.communication.grpc.generated.SetBalanceRequest;
import ds.tutorial.communication.grpc.generated.SetBalanceResponse;
import ds.tutorial.communication.grpc.generated.SetBalanceServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.apache.zookeeper.KeeperException;

import java.util.List;

public class SetBalanceServiceImpl extends SetBalanceServiceGrpc.SetBalanceServiceImplBase {
  private ManagedChannel channel = null;
  SetBalanceServiceGrpc.SetBalanceServiceBlockingStub clientStub = null;
  private BankServer server;

  public SetBalanceServiceImpl(BankServer server) {
    this.server = server;
  }

  @Override
  public void
  setBalance(ds.tutorial.communication.grpc.generated.SetBalanceRequest request,
             io.grpc.stub.StreamObserver<ds.tutorial.communication.grpc.generated.SetBalanceResponse> responseObserver) {
    String accountId = request.getAccountId();
    double value = request.getValue();
    boolean status = false;
    if (server.isLeader()) {
      // Act as primary
      try {
        System.out.println("Updating account balance as Primary");
        updateBalance(accountId, value);
        updateSecondaryServers(accountId, value);
        status = true;
      } catch (Exception e) {
        System.out.println("Error while updating the account balance" + e.getMessage());
        e.printStackTrace();
      }
    } else {
      // Act As Secondary
      if (request.getIsSentByPrimary()) {
        System.out.println("Updating account balance on secondary, on Primary 's command");
        updateBalance(accountId, value);
      } else {
        SetBalanceResponse response =
          callPrimary(accountId, value);
        if (response.getStatus()) {
          status = true;
        }
      }
    }
    SetBalanceResponse response = SetBalanceResponse
      .newBuilder()
      .setStatus(status)
      .build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  private void updateBalance(String accountId, double value) {
    server.setAccountBalance(accountId, value);
    System.out.println("Account " + accountId + " updated to value" + value);
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
}
