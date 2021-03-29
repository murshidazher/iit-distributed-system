package ds.tutorials.communication.server;

import ds.tutorial.communication.grpc.generated.BalanceServiceGrpc;
import ds.tutorial.communication.grpc.generated.CheckBalanceResponse;

import java.util.Random;

public class BalanceServiceImpl extends BalanceServiceGrpc.BalanceServiceImplBase {

  @Override
  public void checkBalance(ds.tutorial.communication.grpc.generated.CheckBalanceRequest request,
                           io.grpc.stub.StreamObserver<ds.tutorial.communication.grpc.generated.CheckBalanceResponse> responseObserver) {

    String accountId = request.getAccountId(); System.out.println("Request received..");
    double balance = getAccountBalance(accountId);
    CheckBalanceResponse response = CheckBalanceResponse
      .newBuilder()
      .setBalance(balance)
      .build();
    System.out.println("Responding, balance for account " + accountId + " is " + balance);
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }
  private double getAccountBalance(String accountId) {
    System.out.println("Checking balance for Account " + accountId);
    return new Random().nextDouble() * 10000;
  }
}
