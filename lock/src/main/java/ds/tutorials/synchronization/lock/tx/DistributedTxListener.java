package ds.tutorials.synchronization.lock.tx;

public interface DistributedTxListener {
  void onGlobalCommit();

  void onGlobalAbort();
}
