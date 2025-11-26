package backpressure;

import java.util.LinkedList;
import java.util.Queue;

public class ThreadSafeQueue {
    private Queue<MatricesPair> queue = new LinkedList<>();
    private boolean isEmpty = true;
    private boolean isTerminated = false;
    private static int CAPACITY = 5;

    public synchronized void add(MatricesPair matricesPair) {
        while (queue.size() == CAPACITY) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        queue.add(matricesPair);
        isEmpty = false;
        notify();
    }

    public synchronized MatricesPair remove() {
        MatricesPair matricesPair = null;
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
        matricesPair = queue.remove();

        if (queue.size() == CAPACITY - 1) {
            notifyAll();
        }

        return matricesPair;
    }

    public synchronized void terminate() {
        isTerminated = true;
        notifyAll();
    }
}
