package ds.tutorials.communication.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class BankServer {
  public static void main (String[] args) throws Exception{
    int serverPort = 11436;
    // we can use server builder to create the server
    Server server = ServerBuilder
      .forPort(serverPort)
      .addService(new BalanceServiceImpl()) // host my check balance services with this implementation in the port
      .build();
    server.start();
    System.out.println("BankServer Started and ready to accept requests on port " + serverPort);
    server.awaitTermination();
  }
}
