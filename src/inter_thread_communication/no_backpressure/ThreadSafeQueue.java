package inter_thread_communication.no_backpressure;

import java.util.LinkedList;
import java.util.Queue;

public class ThreadSafeQueue {
    private Queue<MatricesPair> queue = new LinkedList<>();
    private boolean isEmpty = true;
    private boolean isTerminated = false;

    public synchronized void add(MatricesPair matricesPair) {
        queue.add(matricesPair);
        isEmpty = false;
        notify();
    }

    public synchronized MatricesPair remove() {
        while (isEmpty && !isTerminated) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }

        if (queue.size() == 1) {
            isEmpty = true;
        }

        if (queue.size() == 0 && isTerminated) {
            return null;
        }

        System.out.println("Queue size : " + queue.size());

        return queue.remove();
    }

    public synchronized void terminate() {
        isTerminated = true;
        notifyAll();
    }
}
