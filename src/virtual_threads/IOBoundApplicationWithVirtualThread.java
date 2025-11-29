package virtual_threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IOBoundApplicationWithVirtualThread {

    private static final int NUMBER_OF_TASKS = 10000;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        // performTasks();
        performTasksWithMoreSwitches(); // Takes ~ 10 sec with VT but ~20 sec with Cached or Fixed(1000) threads
        System.out.println("Total time taken: " + (System.currentTimeMillis() - start) + " ms");
    }

    private static void performTasks() {
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < NUMBER_OF_TASKS; i++) {
                executor.submit(() -> {
                    blockingIOOperation();
                });
            }
        }
    }

    private static void performTasksWithMoreSwitches() {
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
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

/*
 Summary:
 - Performance benefits of virtual threads for long blocking calls
 - Virtual threads are a perfect choice for IO bound applications.
 - Using virtual threads:
    - We get similar performance as thread-per-core + Non Blocking IO
    - We get the ease of programming, testing and debugging of thread-per-task + blocking API
 - Virtual threads mounting + unmounting
    - Has a bit of overhead
    - Not as much as a context switch
 */