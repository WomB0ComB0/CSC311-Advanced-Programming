package com.example.lab;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FixedThreadPoolExecutor {

    public static void main(String[] args) {
        // Create a fixed thread pool with 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        System.out.println("Submitting tasks for a fixed thread pool of 3 threads...");

        // Submit 10 tasks
        for (int i = 0; i < 10; i++) {
            final int loopNum = i;
            
            executor.submit(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.printf("Message from %s, loop num = %d\n", threadName, loopNum);
                // Adding a small delay to better see threads working in parallel
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        // Initiate shutdown and wait for tasks to complete
        executor.shutdown();
        try {
            if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println("All tasks finished.");
    }
}
