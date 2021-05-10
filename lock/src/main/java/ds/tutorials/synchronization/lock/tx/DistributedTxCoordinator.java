package ds.tutorials.synchronization.lock.tx;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class DistributedTxCoordinator extends DistributedTx {
  public DistributedTxCoordinator(DistributedTxListener listener) {
    super(listener);
  }

  void onStartTransaction(String transactionId, String participantId) {
    try {
      currentTransaction = "/" + transactionId; // 1. just creates the root node for the transactions
      client.createNode(currentTransaction, true, CreateMode.PERSISTENT, "".getBytes(StandardCharsets.UTF_8));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // 2. states the coordinator to perform th transactions, to perform the transaction then we need to collect the vote
  // if a children send a ABORT atleast one then it will be GLOBAL_ABORT else it means all the childrens are okay
  // then a GLOBAL_COMMIT
  public boolean perform() throws KeeperException, InterruptedException {
    List<String> childrenNodePaths = client.getChildrenNodePaths(currentTransaction);
    boolean result = true;
    byte[] data;
    System.out.println("Child count :" + childrenNodePaths.size());
    for (String path : childrenNodePaths) {
      path = currentTransaction + "/" + path;
      System.out.println("Checking path :" + path);
      data = client.getData(path, false);
      String dataString = new String(data);
      if (!VOTE_COMMIT.equals(dataString)) {
        System.out.println("Child " + path + "caused the transaction to abort.Sending GLOBAL_ABORT");
          sendGlobalAbort();
        result = false;
      }
    }
    System.out.println("All nodes are okay to committhe transaction.Sending GLOBAL_COMMIT");
      sendGlobalCommit();
    reset(); // after performing transaction we reset it so that it will delete all the waiting nodes in the queue
    return result;
  }

  // A GLOBAL_COMMIT is writing in the root node, so all children listening to it can see it
  public void sendGlobalCommit() throws KeeperException, InterruptedException {
    if (currentTransaction != null) {
      System.out.println("Sending global commit for " + currentTransaction);
        client.write(currentTransaction,
          DistributedTxCoordinator.GLOBAL_COMMIT.getBytes(StandardCharsets.UTF_8));
      listener.onGlobalCommit();
    }
  }

  public void sendGlobalAbort() throws KeeperException, InterruptedException {
    if (currentTransaction != null) {
      System.out.println("Sending global abort for " + currentTransaction);
        client.write(currentTransaction,
          DistributedTxCoordinator.GLOBAL_ABORT.getBytes(StandardCharsets.UTF_8));
      listener.onGlobalAbort();
    }
  }

  private void reset() throws KeeperException, InterruptedException {
    client.forceDelete(currentTransaction);
    currentTransaction = null;
  }
}
