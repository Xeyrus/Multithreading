package questions_multithreading;

import java.util.concurrent.locks.ReentrantLock;

// Print odd even with 2 threads
public class PrintOddEven {
    int i = 0;

    void print(int x) {
        System.out.println(x + " " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock(true);
        PrintOddEven t = new PrintOddEven();
        Runnable task = () -> {
            while (true) {
                lock.lock();
                try {
                    t.print(t.i++);
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };

        Thread t1 = new Thread(task, "even");
        Thread t2 = new Thread(task, "odd");
        t1.start();
        t2.start();
    }
}