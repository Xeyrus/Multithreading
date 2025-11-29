package virtual_threads;

public class VirtualThread {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = ()-> System.out.println("Inside thread : "+ Thread.currentThread());

        // Also platform thread
        Thread thread = new Thread(runnable);
        //Platform thread in a more explicit way
        Thread platformThread = Thread.ofPlatform().name("Platform Thread").unstarted(runnable);

        // Virtual thread
        Thread virtualThread = Thread.ofVirtual().name("Virtual Thread").unstarted(runnable);

        thread.start();
        platformThread.start();
        virtualThread.start();

        thread.join();
        platformThread.join();
        virtualThread.join();
    }
}
