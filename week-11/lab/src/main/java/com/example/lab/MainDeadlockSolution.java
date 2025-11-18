package com.example.lab;

/**
 * Section 11: Solution to the deadlock problem
 * Both methods now acquire locks in the same order (lock1 then lock2)
 */
public class MainDeadlockSolution {
  // Create two static locks
  private static final Object lock1 = new Object();
  private static final Object lock2 = new Object();

  // Method that acquires lock1 then lock2
  public static void myMethod() {
    // Obtain lock 1
    synchronized (lock1) {
      System.out.println("myMethod obtained lock 1");

      // Sleep for 500 milliseconds
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }

      // Obtain lock 2
      synchronized (lock2) {
        System.out.println("myMethod obtained lock 2");
      }
      System.out.println("myMethod released lock 2");
    }
    System.out.println("myMethod released lock 1");
  }

  // UPDATED: Now acquires lock1 then lock2 (same order as myMethod)
  public static void otherMethod() {
    // Obtain lock 1 (CHANGED from lock2)
    synchronized (lock1) {
      System.out.println("otherMethod obtained lock 1");

      // Sleep for 500 milliseconds
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }

      // Obtain lock 2
      synchronized (lock2) {
        System.out.println("otherMethod obtained lock 2");
      }
      System.out.println("otherMethod released lock 2");
    }
    System.out.println("otherMethod released lock 1");
  }

  public static void main(String[] args) {
    System.out.println("Starting deadlock solution demonstration...");
    System.out.println("Both methods now acquire locks in the same order.");
    System.out.println();

    // Create and start a thread that runs myMethod
    Thread tA = new Thread(MainDeadlockSolution::myMethod, "Thread-A");

    // Create and start a thread that runs otherMethod
    Thread tB = new Thread(MainDeadlockSolution::otherMethod, "Thread-B");

    tA.start();
    tB.start();

    System.out.println("Both threads started. No deadlock should occur!");
  }
}
