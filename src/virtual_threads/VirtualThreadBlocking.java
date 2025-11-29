package virtual_threads;

import java.util.ArrayList;
import java.util.List;

public class VirtualThreadBlocking {
    private static final int NUMBER_OF_THREADS = 4;
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = ()-> System.out.println("Inside thread : "+ Thread.currentThread());

        List<Thread> virtualThreads = new ArrayList<>();

        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
//            virtualThreads.add(Thread.ofVirtual().unstarted(runnable));
            virtualThreads.add(Thread.ofVirtual().unstarted(new BlockingTask()));
        }

        for (Thread thread : virtualThreads) {
            thread.start();
        }

        for (Thread thread : virtualThreads) {
            thread.join();
        }
    }

    public static class BlockingTask implements Runnable{

        @Override
        public void run() {
            System.out.println("Inside thread : "+ Thread.currentThread() + " before blocking call");
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e){
                throw new RuntimeException(e);
            }
            System.out.println("Inside thread : "+ Thread.currentThread() + " after blocking call");
        }
    }
}

/*
Inside thread : VirtualThread[#22]/runnable@ForkJoinPool-1-worker-3 after blocking call

worker-3 is a platform thread from a pool of platform threads created by JVM to work as carrier thread
 */