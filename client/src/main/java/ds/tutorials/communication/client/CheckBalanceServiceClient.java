package ds.tutorials.communication.client;

import ds.tutorial.communication.grpc.generated.BalanceServiceGrpc;
import ds.tutorial.communication.grpc.generated.CheckBalanceRequest;
import ds.tutorial.communication.grpc.generated.CheckBalanceResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;

public class CheckBalanceServiceClient {
  private ManagedChannel channel = null;
  BalanceServiceGrpc.BalanceServiceBlockingStub clientStub = null;
  String host = null;
  int port = -1;

  public static void main(String[] args) throws InterruptedException { String host = null;
    int port = -1;
    if (args.length != 2) {
      System.out.println("Usage CheckBalanceServiceClient <host> <port>");
      System.exit(1);
    }
    host = args[0];
    port = Integer.parseInt(args[1].trim());
    // We create check balance service client using grpc
    CheckBalanceServiceClient client = new CheckBalanceServiceClient(host,
      port);
    client.initializeConnection();
    client.processUserRequests();
    client.closeConnection();
  }
  public CheckBalanceServiceClient (String host, int port) { this.host = host;
    this.port = port;
  }
  private void initializeConnection () {
    System.out.println("Initializing Connecting to server at " + host + ":" +
      port);
    // we need to use ManagedChannelBuilder and create the connection
    channel = ManagedChannelBuilder.forAddress("localhost", port)
      .usePlaintext() // this uses plaintext else we can use keystore and encrypt the connection
      .build(); // build the channel
    clientStub = BalanceServiceGrpc.newBlockingStub(channel); // we use the client stub connected to the channel
  }
  private void closeConnection() {
    channel.shutdown();
  }

  private void processUserRequests() throws InterruptedException {
    while (true) {
      Scanner userInput = new Scanner(System.in);
      System.out.println("\nEnter Account ID to check the balance :");
      String accountId = userInput.nextLine().trim();
      System.out.println("Requesting server to check the account balance for " + accountId.toString());
      CheckBalanceRequest request = CheckBalanceRequest
        .newBuilder()
        .setAccountId(accountId)
        .build();
      // blocking stub and we will get the response
      CheckBalanceResponse response = clientStub.checkBalance(request);
      System.out.printf("My balance is " + response.getBalance() + " LKR");
      Thread.sleep(1000);
    }
  }
}
