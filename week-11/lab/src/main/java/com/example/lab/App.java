package com.example.lab;

/**
 * Main class demonstrating Java threading concepts (Sections 1-9)
 */

// Section 1: Class that implements Runnable
class Test implements Runnable {
    @Override
    public void run() {
        System.out.println("Test.run() is running on thread: " + Thread.currentThread().getName());
    }
}

// Section 3: Class derived from Thread
class Test2 extends Thread {
    @Override
    public void run() {
        System.out.println("Test2.run() is running on thread: " + Thread.currentThread().getName());
    }
}

public class App {
    // Section 5-9: Static method to show numbers
    public static synchronized void showNumbers() {
        String threadName = Thread.currentThread().getName();

        for (int i = 1; i <= 10; i++) {
            System.out.println("Thread: " + threadName + " - Number: " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Thread: " + threadName + " done.");
    }

    public static void main(String[] args) throws InterruptedException {
        // Section 1: Print the name of the current thread
        System.out.println("Main started on thread: " + Thread.currentThread().getName());

        // Section 1: Create and call run() on Test instance (runs on main thread)
        Test testInstance = new Test();
        testInstance.run();

        // Section 2: Run Test's run method on another thread
        Thread t1 = new Thread(testInstance);
        t1.start();

        // Section 3: Create and start Test2 thread
        Test2 test2Instance = new Test2();
        test2Instance.start();

        // Section 4: Create anonymous class that implements Runnable
        Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous Runnable is running on thread: " + Thread.currentThread().getName());
            }
        };
        Thread t3 = new Thread(anonymousRunnable);
        t3.start();

        // Wait a bit for the initial threads to complete before starting reporters
        Thread.sleep(1000);
        System.out.println("\n--- Starting Reporter Threads ---\n");

        // Section 5-9: Create 3 threads that run the static showNumbers method
        Thread rnt1 = new Thread(App::showNumbers, "Reporter-1");
        Thread rnt2 = new Thread(App::showNumbers, "Reporter-2");
        Thread rnt3 = new Thread(App::showNumbers, "Reporter-3");

        rnt1.start();
        rnt2.start();
        rnt3.start();

        // Section 9: Make main thread wait for the other threads to finish
        rnt1.join();
        rnt2.join();
        rnt3.join();

        // Section 8: Display main thread done message
        System.out.println("Thread: " + Thread.currentThread().getName() + " done.");
    }
}
