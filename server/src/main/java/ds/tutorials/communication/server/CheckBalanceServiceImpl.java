package ds.tutorials.communication.server;

import ds.tutorial.communication.grpc.generated.BalanceServiceGrpc;
import ds.tutorial.communication.grpc.generated.CheckBalanceResponse;

import java.util.Random;

public class CheckBalanceServiceImpl extends BalanceServiceGrpc.BalanceServiceImplBase {

  private BankServer server;

  public CheckBalanceServiceImpl(BankServer server){
    this.server = server;
  }

  @Override
  public void checkBalance(ds.tutorial.communication.grpc.generated.CheckBalanceRequest request,
                           io.grpc.stub.StreamObserver<ds.tutorial.communication.grpc.generated.CheckBalanceResponse> responseObserver) {

    String accountId = request.getAccountId();
    System.out.println("Request received..");
    double balance = getAccountBalance(accountId);

    // then we need to send a check balance response according to grpc and build and send the response
    CheckBalanceResponse response = CheckBalanceResponse
      .newBuilder()
      .setBalance(balance)
      .build();
    System.out.println("Responding, balance for account " + accountId + " is " + balance);
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  private double getAccountBalance(String accountId) {
    // your business logic goes here
    System.out.println("Checking balance for Account " + accountId);
    return server.getAccountBalance(accountId);
  }
}
