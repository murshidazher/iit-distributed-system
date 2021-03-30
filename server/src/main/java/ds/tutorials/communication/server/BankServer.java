package ds.tutorials.communication.server;

import ds.tutorials.name.service.client.NameServiceClient;
import io.grpc.Server;
import io.grpc.ServerBuilder;

public class BankServer {
  public static final String NAME_SERVICE_ADDRESS = "http://localhost:2379";

  public static void main (String[] args) throws Exception{
    if (args.length != 1) {
      System.out.println("Please have the port as an argument");
      System.exit(1);
    }

    int serverPort = Integer.parseInt(args[0]);
    // we can use server builder to create the server
    Server server = ServerBuilder
      .forPort(serverPort)
      .addService(new BalanceServiceImpl()) // host my check balance services with this implementation in the port
      .build();
    server.start();
    NameServiceClient client = new NameServiceClient(NAME_SERVICE_ADDRESS);
    client.registerService("CheckBalanceService", "127.0.0.1", serverPort, "tcp");

    System.out.println("BankServer Started and ready to accept requests on port " + serverPort);
    server.awaitTermination();
  }
}
