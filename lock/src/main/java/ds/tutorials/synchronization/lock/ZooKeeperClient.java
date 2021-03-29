package ds.tutorials.synchronization.lock;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class ZooKeeperClient {
  private ZooKeeper zooKeeper;

  // Watchers are like observable that observes the changes that are happening to the znodes
  public ZooKeeperClient(String zooKeeperUrl, int sessionTimeout, Watcher watcher) throws IOException {
    zooKeeper = new ZooKeeper(zooKeeperUrl, sessionTimeout, watcher);
  }

  // We use these to create nodes
  // path, the data you need inside the node, who can access th file (we currently specify it as open), type of the node
  // type -> ephemeral / persistant etc..
  public String createNode(String path, boolean shouldWatch, CreateMode mode) throws KeeperException,
    InterruptedException, UnsupportedEncodingException {
    String createdPath = zooKeeper.create(path, "".getBytes("UTF-8"), ZooDefs.Ids.OPEN_ACL_UNSAFE, mode);
    return createdPath;
  }

  // if the node exist return the node stats else null
  public boolean CheckExists(String path) throws KeeperException, InterruptedException {
    Stat nodeStat = zooKeeper.exists(path, false);
    return (nodeStat != null);
  }

  public void delete(String path) throws KeeperException, InterruptedException {
    zooKeeper.delete(path, -1);
  }

  public List<String> getChildrenNodePaths (String root) throws KeeperException, InterruptedException {
    zooKeeper.getState().toString();
    return zooKeeper.getChildren(root, false);
  }

  public void addWatch(String path) throws KeeperException, InterruptedException {
    zooKeeper.exists(path, true);
  }
}
