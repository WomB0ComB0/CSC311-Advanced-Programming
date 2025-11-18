package com.example.lab;

/**
 * Section 10: Demonstration of a deadlock scenario
 * This program will hang due to circular lock dependency
 */
public class MainDeadlock {
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

  // Method that acquires lock2 then lock1 (opposite order - causes deadlock)
  public static void otherMethod() {
    // Obtain lock 2
    synchronized (lock2) {
      System.out.println("otherMethod obtained lock 2");

      // Sleep for 500 milliseconds
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }

      // Obtain lock 1
      synchronized (lock1) {
        System.out.println("otherMethod obtained lock 1");
      }
      System.out.println("otherMethod released lock 1");
    }
    System.out.println("otherMethod released lock 2");
  }

  public static void main(String[] args) {
    System.out.println("Starting deadlock demonstration...");
    System.out.println("WARNING: This program will hang due to deadlock!");
    System.out.println();

    // Create and start a thread that runs myMethod
    Thread tA = new Thread(MainDeadlock::myMethod, "Thread-A");

    // Create and start a thread that runs otherMethod
    Thread tB = new Thread(MainDeadlock::otherMethod, "Thread-B");

    tA.start();
    tB.start();

    System.out.println("Both threads started. Deadlock should occur...");
  }
}
