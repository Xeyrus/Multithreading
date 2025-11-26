package threading_models;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IOBoundApplication {

    private static final int NUMBER_OF_TASKS = 10000;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        // performTasks(); // ~10 sec
        // performTasksFixed(); // ~10 sec
        performTasksFixed100(); // ~90 sec due to lot of context switches
        System.out.println("Total time taken: " + (System.currentTimeMillis() - start) + " ms");
    }

    private static void performTasks() {
        try (ExecutorService executor = Executors.newCachedThreadPool()) {
            for (int i = 0; i < NUMBER_OF_TASKS; i++) {
                executor.submit(() -> {
                    blockingIOOperation();
                });
            }
        }
    }

    private static void performTasksFixed() {
        try (ExecutorService executor = Executors.newFixedThreadPool(1000)) {
            for (int i = 0; i < NUMBER_OF_TASKS; i++) {
                executor.submit(() -> {
                    blockingIOOperation();
                });
            }
        }
    }

    // 99 context switches for 100 task as each thread does 1 blocking I/O operation
    private static void performTasksFixed100() {
        try (ExecutorService executor = Executors.newFixedThreadPool(1000)) {
            for (int i = 0; i < NUMBER_OF_TASKS; i++) {
                executor.submit(new Runnable() {
                    @Override
                    public void run() {
                        for (int j = 0; j < 100; j++) {
                            blockingIOOperation();
                        }
                    }
                });
            }
        }
    }

    public static void blockingIOOperation() {
        System.out.println("Performing blocking I/O operation in thread: " + Thread.currentThread());
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}

// IO bound application are blocking most of the time
// No of cores = No of threads
// (Wont work here as threads are blocked most of the time)

/*
 * Threads-per-Task/Thread-per-request model analysis:
 * - Number of threads we can create is limited
 * - Threads consume stack memory and other resources
 * - Too many threads - Application crashes
 * - Too few threads - CPU underutilization and low throughput
 * - Price of context switches :
 * - OS trying to fully utilize CPU
 * - As soon as there's a blocking I/O operation, OS unshedules that thread
 * - Too many threads and frequent context switches lead to CPU being busy
 * running OS code instead of application code (Thrashing)
 * 
 * 
 * Thread per Task Model was standard for many years but not optimal for
 * performance and CPU utilization.
 */