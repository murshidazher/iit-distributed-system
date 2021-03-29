package ds.tutorials.synchronization.lock;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class DistributedLock implements Watcher {

  private String childPath;
  private ZooKeeperClient client;

  private String lockPath; // this is root lock path
  private boolean isAcquired = false; // sentinal to see if the lock is acquired or not
  private String watchedNode;
  CountDownLatch startFlag = new CountDownLatch(1);
  CountDownLatch eventReceivedFlag;
  public static String zooKeeperUrl ;
  private static String lockProcessPath = "/lp_";

  public static void setZooKeeperURL(String url){
    zooKeeperUrl = url;
  }

  public DistributedLock(String lockName) throws IOException, KeeperException, InterruptedException {
    this.lockPath = "/" + lockName; // lockpath should always start with /
    client = new ZooKeeperClient(zooKeeperUrl, 5000, this);
    startFlag.await(); // it will hold the lock until zookeeper tells that the node is created
    if (client.CheckExists(lockPath) == false) {
      createRootNode(); // if no root path create the root node for lock, executed only once
    }
    createChildNode();
  }

  private void createRootNode() throws InterruptedException, UnsupportedEncodingException, KeeperException {
    // root node with mode being PERSISTENT node.
    lockPath = client.createNode(lockPath, false, CreateMode.PERSISTENT);
    System.out.println("Root node created at " + lockPath);
  }

  private void createChildNode() throws InterruptedException, UnsupportedEncodingException, KeeperException {
    // |- /disLock                <- lock path (if the lock name is distributed lock)
    // |- /disLock/lp_0000000003  <- lock process path `/` indicates a child, ephimerical will have 10 digits appended to it
    childPath = client.createNode(lockPath + lockProcessPath, false, CreateMode.EPHEMERAL_SEQUENTIAL);
    System.out.println("Child node created at " + childPath);
  }

  // check whether the node is the smallest in the sequence
  public void acquireLock() throws KeeperException, InterruptedException {
    String smallestNode = findSmallestNodePath();
    // if the smallestNode path equal to the this node path
    if (smallestNode.equals(childPath)) {
      isAcquired = true;
    } else {
      do {
        System.out.println("Lock is currently acquired by node " + smallestNode + " .. hence waiting..");
        eventReceivedFlag = new CountDownLatch(1);
        watchedNode = smallestNode;
        client.addWatch(smallestNode);
        eventReceivedFlag.await(); // wait until the current lock using node is deleted
        smallestNode = findSmallestNodePath();
      } while (!smallestNode.equals(childPath)); // see am I the smallest now else do again

      isAcquired = true;
    }
  }

  public void releaseLock() throws KeeperException,
    InterruptedException {
    if (!isAcquired) {
      throw new IllegalStateException("Lock needs to be acquired first to release");
    }
    client.delete(childPath);
    isAcquired = false;
  }

  private String findSmallestNodePath() throws
    KeeperException, InterruptedException {
    List<String> childrenNodePaths = null;
    childrenNodePaths = client.getChildrenNodePaths(lockPath);
    Collections.sort(childrenNodePaths);
    String smallestPath = childrenNodePaths.get(0);
    smallestPath = lockPath + "/" + smallestPath;
    return smallestPath;
  }

  @Override
  public void process(WatchedEvent event) {
    Event.KeeperState state = event.getState();
    Event.EventType type = event.getType();
    if (Event.KeeperState.SyncConnected == state) {
      if (Event.EventType.None == type) {
        // Identify successful connection
        System.out.println("Successful connected to the server");
        startFlag.countDown();
      }
    }
    // to see if a node is deleted
    if (Event.EventType.NodeDeleted.equals(type)){
      if (watchedNode != null && eventReceivedFlag
        != null && event.getPath().equals(watchedNode)){
        System.out.println("NodeDelete event received. Trying to get the lock..");
        eventReceivedFlag.countDown();
      }
    }
  }
}
