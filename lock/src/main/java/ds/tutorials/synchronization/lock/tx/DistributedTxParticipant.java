package ds.tutorials.synchronization.lock.tx;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.nio.charset.StandardCharsets;

public class DistributedTxParticipant extends DistributedTx implements Watcher {
  private static final String PARTICIPANT_PREFIX = "/txp_";
  private String transactionRoot;

  public DistributedTxParticipant(DistributedTxListener listener) {
    super(listener);
  }

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

  public void voteAbort() {
    try {
      if (currentTransaction != null) {
        System.out.println("Voting to abort the transaction " +
          currentTransaction);
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

  void onStartTransaction(String transactionId, String
    participantId) {
    try {
      transactionRoot = "/" + transactionId;
      currentTransaction = transactionRoot + PARTICIPANT_PREFIX + participantId;
      client.createNode(currentTransaction, true, CreateMode.EPHEMERAL, "".getBytes(StandardCharsets.UTF_8));
      client.addWatch(transactionRoot);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void handleRootDataChange() {
    try {
      byte[] data =
        client.getData(transactionRoot, true);
      String dataString = new String(data);
      if
      (DistributedTxCoordinator.GLOBAL_COMMIT.equals(dataString)) {
        listener.onGlobalCommit();
      } else if
      (DistributedTxCoordinator.GLOBAL_ABORT.equals(dataString
        )) {
        listener.onGlobalAbort();
      } else {
        System.out.println("Unknown data changein the root :" + dataString);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void process(WatchedEvent event) {
    Event.EventType type = event.getType();
    if
    (Event.EventType.NodeDataChanged.equals(type)) {
      if (transactionRoot != null &&
        event.getPath().equals(transactionRoot)) {
        handleRootDataChange();
      }
    }
    if (Event.EventType.NodeDeleted.equals(type)) {
      if (transactionRoot != null &&
        event.getPath().equals(transactionRoot)) {
        rest();
      }
    }
  }
}
