package thread_creation;

public class ThreadCreationRunnable {
    public static void main(String[] args) {
        Runnable task = ()->{
            throw new RuntimeException("I am error");
        };

        Thread thread = new Thread(task, "thread-1");

        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("Critical Error in : "+t.getName() +" \nwith error : "+e.getMessage());
            }
        });

        thread.start();
    }
}