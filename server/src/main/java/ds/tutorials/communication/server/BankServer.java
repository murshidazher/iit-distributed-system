package ds.tutorials.communication.server;

import ds.tutorials.synchronization.lock.DistributedLock;
import ds.tutorials.synchronization.lock.tx.DistributedTx;
import ds.tutorials.synchronization.lock.tx.DistributedTxCoordinator;
import ds.tutorials.synchronization.lock.tx.DistributedTxParticipant;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class BankServer {
  private int serverPort;
  private DistributedLock leaderLock;
  private AtomicBoolean isLeader = new AtomicBoolean(false);
  private byte[] leaderData;
  private Map<String, Double> accounts = new HashMap();

  DistributedTx transaction;
  SetBalanceServiceImpl setBalanceService;
  CheckBalanceServiceImpl checkBalanceService;

  public BankServer(String host, int port) throws InterruptedException, IOException, KeeperException {
    this.serverPort = port;
    leaderLock = new DistributedLock("BankServerCluster", buildServerData(host, port));
    setBalanceService = new SetBalanceServiceImpl(this);
    checkBalanceService = new CheckBalanceServiceImpl(this);
    transaction = new DistributedTxParticipant(setBalanceService);
  }

  public DistributedTx getTransaction() {
    return transaction;
  }

  public static String buildServerData(String IP, int port) {
    StringBuilder builder = new StringBuilder();
    builder.append(IP).append(":").append(port);
    return builder.toString();
  }

  private void tryToBeLeader() throws KeeperException, InterruptedException {
    Thread leaderCampaignThread = new Thread(new LeaderCampaignThread());
    leaderCampaignThread.start();
  }

  public void startServer() throws IOException, InterruptedException, KeeperException {
    Server server = ServerBuilder
      .forPort(serverPort)
      .addService(checkBalanceService) // host both checkbalance and setbalance services
      .addService(setBalanceService)
      .build();
    server.start();
    System.out.println("BankServer Started and ready to accept requests on port " + serverPort);
    tryToBeLeader();

    server.awaitTermination();
  }

  public synchronized String[] getCurrentLeaderData() {
    return new String(leaderData).split(":");
  }

  public List<String[]> getOthersData() throws KeeperException, InterruptedException {
    List<String[]> result = new ArrayList<>();
    List<byte[]> othersData = leaderLock.getOthersData();
    for (byte[] data : othersData) {
      String[] dataStrings = new
        String(data).split(":");
      result.add(dataStrings);
    }
    return result;
  }

  public void setAccountBalance(String accountId, double value) {
    accounts.put(accountId, value);
  }

  public double getAccountBalance(String accountId) {
    Double value = accounts.get(accountId);
    return (value != null) ? value : 0.0;
  }

  // to check if the current server is the leader
  public boolean isLeader() {
    return isLeader.get();
  }

  // if its the leader set the data
  private synchronized void setCurrentLeaderData(byte[] leaderData) {
    this.leaderData = leaderData;
  }

  public static void main (String[] args) throws Exception{
    if (args.length != 1) {
      System.out.println("Usage executable-name <port>");
    }

    int serverPort = Integer.parseInt(args[0]);
    DistributedLock.setZooKeeperURL("localhost:2181");
    DistributedTx.setZooKeeperURL("localhost:2181");

    BankServer server = new BankServer("localhost", serverPort);
    server.startServer();

  }

  // actions to take when its the leader
  private void beTheLeader() {
    System.out.println("I got the leader lock. Now actingas primary");
    isLeader.set(true);
    transaction = new DistributedTxCoordinator(setBalanceService);
  }

  //  A separate thread implementation which continuously tries to acquire the lock.
  //  While this lock is busy with election main thread can continue to serve the requests.
  class LeaderCampaignThread implements Runnable {
    private byte[] currentLeaderData = null;

    @Override
    public void run() {
      System.out.println("Starting the leader Campaign");
      try {
        boolean leader = leaderLock.tryAcquireLock();
        while (!leader) {
          byte[] leaderData = leaderLock.getLockHolderData();
          if (currentLeaderData != leaderData) {
            // if the current object isn't the leader then give the leaders data and save it
            currentLeaderData = leaderData;
            setCurrentLeaderData(currentLeaderData);
          }
          Thread.sleep(10000);
          leader = leaderLock.tryAcquireLock();
        }
        currentLeaderData = null;
        beTheLeader();
      } catch (Exception e) {
        // throw the exception
      }
    }
  }
}
