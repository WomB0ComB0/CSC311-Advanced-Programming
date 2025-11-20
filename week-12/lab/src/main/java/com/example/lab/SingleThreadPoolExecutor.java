package com.example.lab;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SingleThreadPoolExecutor {

    public static void main(String[] args) {
        // Create a single thread executor thread pool
        ExecutorService executor = Executors.newSingleThreadExecutor();

        System.out.println("Submitting tasks for a single thread pool...");

        // Create a for loop that runs 10 times
        for (int i = 0; i < 10; i++) {
            final int loopNum = i; // Use an effectively final variable for the lambda
            
            // Submit a task to the executor
            executor.submit(() -> {
                String threadName = Thread.currentThread().getName();
                // Task's code: print thread name and loop number
                System.out.printf("Message from %s, loop num = %d\n", threadName, loopNum);
            });
        }

        // Shutdown the executor
        executor.shutdown(); // Initiates shutdown, no new tasks accepted

        try {
            // Wait for the executor to terminate
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                // Force shutdown if tasks don't complete in time
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            // Re-interrupt the current thread
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println("All tasks finished.");
    }
}
