package lock_free_operations.lock_free_data_structure;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

public class LockFreeStack<T> {
    private AtomicReference<StackNode<T>> head = new AtomicReference<>();
    private AtomicInteger counter = new AtomicInteger(0);

    public void push(T value) {
        StackNode<T> newHeadNode = new StackNode<>(value);

        while (true) {
            StackNode<T> currentHead = head.get();
            newHeadNode.next = currentHead;
            if (head.compareAndSet(currentHead, newHeadNode)) {
                counter.incrementAndGet();
                return;
            } else {
                LockSupport.parkNanos(1);
            }
        }
    }

    public T pop() {
        StackNode<T> currentHead = head.get();
        StackNode<T> newHead;
        while (currentHead != null) {
            newHead = currentHead.next;
            if (head.compareAndSet(currentHead, newHead)) {
                counter.incrementAndGet();
                return currentHead.value;
            } else {
                LockSupport.parkNanos(1);
                currentHead = head.get();
            }
        }
        return currentHead != null ? currentHead.value : null;
    }

    public int getCounter() {
        return counter.get();
    }
}
