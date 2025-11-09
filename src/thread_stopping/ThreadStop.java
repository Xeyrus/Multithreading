package thread_stopping;

public class ThreadStop {
    public static void main(String[] args) {
    Thread thread = new Thread(new BlockingThread());

    thread.start();

    thread.interrupt();     // Throws interruptedException but handled in catch block
    }
}
