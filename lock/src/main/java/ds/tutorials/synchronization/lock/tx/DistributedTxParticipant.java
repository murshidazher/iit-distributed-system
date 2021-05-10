package ds.tutorials.synchronization.lock.tx;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.nio.charset.StandardCharsets;

public class DistributedTxParticipant extends DistributedTx implements Watcher {
  private static final String PARTICIPANT_PREFIX = "/txp_"; // transaction participant prefix
  private String transactionRoot; // we are listening on the root

  public DistributedTxParticipant(DistributedTxListener listener) {
    super(listener);
  }

  // we need to vote commit to the child node so the distributed coordinator will read that vote_commit or not and
  // make the decision
  public void voteCommit() {
    try {
      if (currentTransaction != null) {
        System.out.println("Voting to commit the transaction " + currentTransaction);
        client.write(currentTransaction, DistributedTxCoordinator.VOTE_COMMIT.getBytes(StandardCharsets.UTF_8));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // same as vote commit which will add a vote abort to the node so the distributed coordinator will read
  public void voteAbort() {
    try {
      if (currentTransaction != null) {
        System.out.println("Voting to abort the transaction " + currentTransaction);
        client.write(currentTransaction,
          DistributedTxCoordinator.VOTE_ABORT.getBytes(StandardCharsets.UTF_8));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void rest() {
    currentTransaction = null;
    transactionRoot = null;
  }

  void onStartTransaction(String transactionId, String participantId) {
    try {
      transactionRoot = "/" + transactionId;
      // -> \transaction - \txp_<id>
      currentTransaction = transactionRoot + PARTICIPANT_PREFIX + participantId;
      // create an ephemeral - no need to keep the transaction if root is unavailable and the initial data is null
      client.createNode(currentTransaction, true, CreateMode.EPHEMERAL, "".getBytes(StandardCharsets.UTF_8));
      client.addWatch(transactionRoot); // watch the root of the transactions
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // listens to the root node and if the coordinator says GLOBAL_COMMIT commit it, else if GLOBAL_ABORT abort it.
  private void handleRootDataChange() {
    try {
      byte[] data = client.getData(transactionRoot, true);
      String dataString = new String(data);
      if (DistributedTxCoordinator.GLOBAL_COMMIT.equals(dataString)) {
        listener.onGlobalCommit();
      } else if (DistributedTxCoordinator.GLOBAL_ABORT.equals(dataString)) {
        listener.onGlobalAbort();
      } else {
        System.out.println("Unknown data change in the root :" + dataString);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // watching the root node
  // if the root node is deleted the either way (GLOBAL_COMMIT/GLOBAL_ABORT) the transaction is completed.
  @Override
  public void process(WatchedEvent event) {
    Event.EventType type = event.getType();
    if
    (Event.EventType.NodeDataChanged.equals(type)) {
      if (transactionRoot != null && event.getPath().equals(transactionRoot)) {
        handleRootDataChange();
      }
    }
    if (Event.EventType.NodeDeleted.equals(type)) {
      if (transactionRoot != null && event.getPath().equals(transactionRoot)) {
        rest();
      }
    }
  }
}
