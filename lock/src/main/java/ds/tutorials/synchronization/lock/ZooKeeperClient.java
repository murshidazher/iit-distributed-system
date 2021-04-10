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
  // improve the createNode method to accept data as a parameter and save it in znode
  public String createNode(String path, boolean shouldWatch, CreateMode mode, byte[] data) throws KeeperException,
    InterruptedException, UnsupportedEncodingException {
    String createdPath = zooKeeper.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, mode);
    return createdPath;
  }

  // if the node exist return the node stats else null
  public boolean CheckExists(String path) throws KeeperException, InterruptedException {
    Stat nodeStat = zooKeeper.exists(path, false);
    return (nodeStat != null);
  }

  // delete a node
  public void delete(String path) throws KeeperException, InterruptedException {
    zooKeeper.delete(path, -1);
  }

  // znode (root path) get a list of child nodes attached to it and the resource is accessed based on this order
  public List<String> getChildrenNodePaths(String root) throws KeeperException, InterruptedException {
    zooKeeper.getState().toString();
    return zooKeeper.getChildren(root, false);
  }

  // adding a watch will lookout for changes, watch true will call the callback when we create the zookeeper client
  public void addWatch(String path) throws KeeperException, InterruptedException {
    zooKeeper.exists(path, true);
  }

  // Also, add a method to to fetch the stored data
  public byte[] getData(String path, boolean shouldWatch) throws KeeperException, InterruptedException {
    return zooKeeper.getData(path, shouldWatch, null);
  }
}
