package ds.tutorials.synchronization.lock;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class DummyProcess {

  public static final String ZOOKEEPER_URL = "127.0.0.1:2181";
  private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

  public static void main(String[] args) {
    DistributedLock.setZooKeeperURL(ZOOKEEPER_URL);
    try {
      DistributedLock lock = new DistributedLock("myLock", "");
      lock.acquireLock();
      System.out.println("I Got the lock at " + getCurrentTimeStamp());
      accessSharedResource();
      lock.releaseLock();
      System.out.println("Releasing the lock " + getCurrentTimeStamp());
    } catch (IOException | KeeperException |
      InterruptedException e) {
      System.out.println("Error while creating Distributed Lock myLock :" + e.getMessage());
      e.printStackTrace();
    }
  }

  // simulate making some processes for a time period
  private static void accessSharedResource() throws
    InterruptedException {
    Random r = new Random();
    long sleepDuration = Math.abs(r.nextInt() % 20);
    System.out.println("Accessing critical section. Time remaining : " + sleepDuration + " seconds.");
    Thread.sleep(sleepDuration * 1000);
  }

  private static String getCurrentTimeStamp() {
    return timeFormat.format(new Date(System.currentTimeMillis()));
  }
}

