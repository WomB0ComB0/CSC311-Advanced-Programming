package com.example.threads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * COMPREHENSIVE THREAD STUDY GUIDE
 * Based on your lecture slides.
 *
 * CONCEPTS:
 * 1. Runnable: Write a class that implements the Runnable interface. Best practice (decouples task from runner).
 * 2. Extend Thread: Write a class that inherits the Thread class. Less flexible (Java only allows single inheritance).
 * 3. Anonymous Runnable: Defining the task inline using an anonymous inner class.
 * 4. Lambda Runnable: Using Java 8+ Lambda syntax `() -> {}` for concise code.
 * 5. Passing Data: Using constructors to pass data into a thread, or using "effectively final" local variables.
 * 6. Join: Waiting for a thread to complete before proceeding.
 * 7. Sleep: Pausing execution (requires handling InterruptedException).
 * 8. Synchronization: Preventing race conditions using synchronized methods or blocks.
 * 9. Executors: High-level thread management (Thread Pools).
 * 10. CountDownLatch: Waiting for multiple threads to finish.
 */

public class ThreadStudySession {

  public static void main(String[] args) {
    System.out.println("=== START OF EXAM PREP DEMO ===\n");

    // 1. Basic Runnable vs Thread
    basicThreadCreation();

    // 2. Anonymous and Lambda
    anonymousAndLambda();

    // 3. Passing Data
    passingData();

    // 4. Thread Lifecycle (Join and Sleep)
    lifecycleDemo();

    // 5. Synchronization (Critical Sections)
    synchronizationDemo();

    // 6. Executors (Thread Pools - Best Practice)
    executorServiceDemo();

    // 7. CountDownLatch (Waiting for multiple threads)
    countDownLatchDemo();

    System.out.println("\n=== END OF EXAM PREP DEMO ===");
  }

  // ==========================================
  // 1. BASIC CREATION
  // ==========================================
  static void basicThreadCreation() {
    System.out.println("--- 1. Basic Creation ---");

    // Option A: Implement Runnable (Best Practice)
    Thread t1 = new Thread(new RunThreadImplementRunnable());
    t1.start();

    // Option B: Extend Thread
    RunThreadExtendThread t2 = new RunThreadExtendThread();
    t2.start();

    try {
      t1.join();
      t2.join();
    } catch (InterruptedException e) {
    }
  }

  // Class implements the Runnable Interface
  static class RunThreadImplementRunnable implements Runnable {
    @Override
    public void run() {
      System.out.println("Thread - Implements Runnable");
    }
  }

  // Class inherits from Thread
  static class RunThreadExtendThread extends Thread {
    @Override
    public void run() {
      System.out.println("Thread - Extends Thread");
    }
  }

  // ==========================================
  // 2. ANONYMOUS & LAMBDA
  // ==========================================
  static void anonymousAndLambda() {
    System.out.println("\n--- 2. Anonymous & Lambda ---");

    // Anonymous Inner Class
    Thread t1 =
        new Thread(
            new Runnable() {
              @Override
              public void run() {
                System.out.println("Thread - Anonymous Inner Class");
              }
            });
    t1.start();

    // Lambda Expression (Java 8+)
    // Runnable is a functional interface, so we can use () -> {}
    Thread t2 =
        new Thread(
            () -> {
              System.out.println("Thread - Java Lambda Expression");
            });
    t2.start();

    try {
      t1.join();
      t2.join();
    } catch (InterruptedException e) {
    }
  }

  // ==========================================
  // 3. PASSING DATA
  // ==========================================
  static void passingData() {
    System.out.println("\n--- 3. Passing Data ---");

    // Approach A: Constructor Injection (Best for complex objects)
    Thread t1 = new Thread(new DataRunnable(101));
    t1.start();

    // Approach B: Final / Effectively Final Local Variables
    final String message = "I love threads with final variables!";
    String effectivelyFinalMsg = "This works too if not changed!";

    Thread t2 =
        new Thread(
            () -> {
              // Can access variables from outer scope if they don't change
              System.out.println("Lambda Capture: " + message);
              System.out.println("Lambda Capture: " + effectivelyFinalMsg);
            });
    t2.start();

    try {
      t1.join();
      t2.join();
    } catch (InterruptedException e) {
    }
  }

  static class DataRunnable implements Runnable {
    private int data;

    public DataRunnable(int data) {
      this.data = data;
    }

    @Override
    public void run() {
      System.out.printf("Constructor Data: %d\n", data);
    }
  }

  // ==========================================
  // 4. LIFECYCLE (JOIN & SLEEP)
  // ==========================================
  static void lifecycleDemo() {
    System.out.println("\n--- 4. Join & Sleep ---");

    Thread t =
        new Thread(
            () -> {
              System.out.println("Thread - Going to sleep for 1 sec...");
              try {
                // Sleep throws InterruptedException, must be caught
                Thread.sleep(1000);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              System.out.println("Thread - Waking up!");
            });

    t.start();

    try {
      System.out.println("Main - Waiting for thread to finish (join)...");
      t.join(); // Main thread blocks here until t finishes
      System.out.println("Main - Thread finished, resuming.");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  // ==========================================
  // 5. SYNCHRONIZATION (Slides 13-14)
  // ==========================================
  static int counter = 0; // Shared Resource
  static final Object lock = new Object(); // Lock Object

  static void synchronizationDemo() {
    System.out.println("\n--- 5. Synchronization ---");
    counter = 0;

    Runnable task =
        () -> {
          for (int i = 0; i < 1000; i++) {
            // SYNCHRONIZED BLOCK
            // Only one thread can enter this block at a time
            synchronized (lock) {
              counter++;
            }
          }
        };

    Thread t1 = new Thread(task);
    Thread t2 = new Thread(task);

    t1.start();
    t2.start();

    try {
      t1.join();
      t2.join();
    } catch (InterruptedException e) {
    }

    System.out.println("Counter (Expected 2000): " + counter);
  }

  // ==========================================
  // 6. EXECUTORS (Slides 19-25)
  // ==========================================
  static void executorServiceDemo() {
    System.out.println("\n--- 6. ExecutorService ---");

    // Create a pool with a fixed number of threads
    ExecutorService executor = Executors.newFixedThreadPool(2);

    // Submit tasks
    executor.submit(
        () -> System.out.println("Executor Task 1 - " + Thread.currentThread().getName()));
    executor.submit(
        () -> System.out.println("Executor Task 2 - " + Thread.currentThread().getName()));
    executor.submit(
        () -> System.out.println("Executor Task 3 - " + Thread.currentThread().getName()));

    // Shutdown prevents new tasks and starts cleaning up
    executor.shutdown();

    try {
      // Wait for tasks to finish
      executor.awaitTermination(5, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("Executor finished.");
  }

  // ==========================================
  // 7. COUNT DOWN LATCH (Slide 26)
  // ==========================================
  static void countDownLatchDemo() {
    System.out.println("\n--- 7. CountDownLatch ---");

    int numberOfThreads = 3;
    CountDownLatch latch = new CountDownLatch(numberOfThreads);

    for (int i = 0; i < numberOfThreads; i++) {
      new Thread(
              () -> {
                System.out.println("Worker doing work...");
                latch.countDown(); // Decrement count
              })
          .start();
    }

    try {
      latch.await(); // Main thread waits until count reaches 0
      System.out.println("All workers finished. Proceeding.");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
