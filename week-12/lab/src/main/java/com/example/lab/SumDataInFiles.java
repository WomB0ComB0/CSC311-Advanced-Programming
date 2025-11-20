package com.example.lab;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SumDataInFiles {

    // Static members to be shared across threads
    private static int grandTotal = 0;
    private static final Queue<String> fileQueue = new LinkedList<>();
    private static final CountDownLatch latch = new CountDownLatch(5);

    // Static lock objects for synchronization
    private static final Object queueLock = new Object();
    private static final Object totalLock = new Object();

    public static void main(String[] args) {
        // 1. Create the data files
        createDataFiles();

        // 2. Add filenames to the queue
        for (int i = 1; i <= 5; i++) {
            fileQueue.add("file" + i + ".txt");
        }

        // 3. Create a fixed thread pool of 2 threads
        ExecutorService executor = Executors.newFixedThreadPool(2);

        System.out.println("Starting threads to process files...");

        // 4. Create and run a task for each file
        for (int i = 0; i < 5; i++) {
            executor.submit(SumDataInFiles::sumFile);
        }

        try {
            // 5. Add code to main to wait on the CountDownLatch
            System.out.println("Main thread waiting for all files to be processed...");
            latch.await(); // Main thread blocks here until latch count is 0
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Main thread was interrupted.");
        }
        
        // 6. Shutdown executor
        executor.shutdown();
        try {
            if(!executor.awaitTermination(1, TimeUnit.MINUTES)){
                executor.shutdownNow();
            }
        } catch(InterruptedException e){
            executor.shutdownNow();
        }


        // 7. Print the final grand total
        System.out.printf("All threads have finished. Grand Total: %d\n", grandTotal);
    }

    // This method will be run by the threads in the pool
    public static void sumFile() {
        String fileName;

        // Synchronize access to the shared queue
        synchronized (queueLock) {
            if (!fileQueue.isEmpty()) {
                fileName = fileQueue.poll();
            } else {
                return; // No file to process
            }
        }

        System.out.printf("%s is processing file: %s\n", Thread.currentThread().getName(), fileName);
        int fileTotal = 0;
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextInt()) {
                fileTotal += scanner.nextInt();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Synchronize access to the shared grand total
        synchronized (totalLock) {
            grandTotal += fileTotal;
        }
        
        // Decrement the CountDownLatch after the file has been processed
        latch.countDown();
    }
    
    // Helper method to create sample files with data
    private static void createDataFiles() {
        for (int i = 1; i <= 5; i++) {
            try (FileWriter writer = new FileWriter("file" + i + ".txt")) {
                for (int j = 1; j <= 5; j++) {
                    int number = (i - 1) * 5 + j;
                    writer.write(number + "\n");
                }
            } catch (IOException e) {
                System.err.println("Error creating data files.");
                e.printStackTrace();
            }
        }
    }
}
