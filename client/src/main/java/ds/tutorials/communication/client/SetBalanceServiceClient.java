package ds.tutorials.communication.client;

import ds.tutorial.communication.grpc.generated.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;

public class SetBalanceServiceClient {
    private ManagedChannel channel = null;
    SetBalanceServiceGrpc.SetBalanceServiceBlockingStub clientStub = null;
    String host = null;
    int port = -1;

    public SetBalanceServiceClient (String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void initializeConnection () {
        System.out.println("Initializing Connecting to server at " + host + ":" +
                port);
        channel = ManagedChannelBuilder.forAddress("localhost", port)
                .usePlaintext()
                .build();
        clientStub = SetBalanceServiceGrpc.newBlockingStub(channel);
    }
    public void closeConnection() {
        channel.shutdown();
    }

    public void processUserRequests() throws InterruptedException {
        while (true) {
            Scanner userInput = new Scanner(System.in);
            System.out.println("\nEnter Account ID, amount to set the balance :");
            String input[] = userInput.nextLine().trim().split(",");
            String accountId = input[0];
            double amount = Double.parseDouble(input[1]);
            System.out.println("Requesting server to set the account balance to " + amount + " for " + accountId.toString());
            SetBalanceRequest request = SetBalanceRequest
                    .newBuilder()
                    .setAccountId(accountId)
                    .setValue(amount)
                    .setIsSentByPrimary(false)
                    .build();
            SetBalanceResponse response = clientStub.setBalance(request);
            System.out.printf("Transaction Status " + (response.getStatus() ? "Sucessful" : "Failed"));
            Thread.sleep(1000);
        }
    }
}
