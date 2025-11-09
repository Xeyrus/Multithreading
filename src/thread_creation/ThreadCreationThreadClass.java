package thread_creation;

public class ThreadCreationThreadClass {
    public static void main(String[] args) {
        Thread thread = new NewThread();
        thread.start();
    }

    private static class NewThread extends Thread {
        public void run(){
            System.out.println("Hello from "+ this.getName()); // Thread.currentThread().getName() : got internal method
            // this.
        }
    }
}


