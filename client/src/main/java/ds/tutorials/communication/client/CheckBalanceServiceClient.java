package ds.tutorials.communication.client;

import ds.tutorial.communication.grpc.generated.CheckBalanceRequest;
import ds.tutorial.communication.grpc.generated.CheckBalanceResponse;
import ds.tutorial.communication.grpc.generated.CheckBalanceServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;

public class CheckBalanceServiceClient {
    private ManagedChannel channel = null;
    CheckBalanceServiceGrpc.CheckBalanceServiceBlockingStub clientStub = null;
    String host = null;
    int port = -1;

    public CheckBalanceServiceClient (String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void initializeConnection () {
        System.out.println("Initializing Connecting to server at " + host + ":" +
                port);
        channel = ManagedChannelBuilder.forAddress("localhost", port)
                .usePlaintext()
                .build();
        clientStub = CheckBalanceServiceGrpc.newBlockingStub(channel);
    }
    public void closeConnection() {
        channel.shutdown();
    }

    public void processUserRequests() throws InterruptedException {
        while (true) {
            Scanner userInput = new Scanner(System.in);
            System.out.println("\nEnter Account ID to check the balance :");
            String accountId = userInput.nextLine().trim();
            System.out.println("Requesting server to check the account balance for " + accountId.toString());
            CheckBalanceRequest request = CheckBalanceRequest
                    .newBuilder()
                    .setAccountId(accountId)
                    .build();
            CheckBalanceResponse response = clientStub.checkBalance(request);
            System.out.printf("My balance is " + response.getBalance() + " LKR");
            Thread.sleep(1000);
        }
    }
}
