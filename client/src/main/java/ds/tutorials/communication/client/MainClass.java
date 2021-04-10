package ds.tutorials.communication.client;

public class MainClass {
    public static void main(String[] args) throws InterruptedException {
        String host = args[0];
        int port = Integer.parseInt(args[1].trim());
        String operation = args[2];

        if (args.length != 3) {
            System.out.println("Usage CheckBalanceServiceClient <host> <port> <s(et)|c(heck)");
            System.exit(1);
        }

        if ("s".equals(operation)) {
            SetBalanceServiceClient client = new SetBalanceServiceClient(host, port);
            client.initializeConnection();
            client.processUserRequests();
            client.closeConnection();
        } else {
            CheckBalanceServiceClient client = new CheckBalanceServiceClient(host,
                    port);
            client.initializeConnection();
            client.processUserRequests();
            client.closeConnection();
        }
    }
}
