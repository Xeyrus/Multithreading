package lock_free_operations.lock_free_data_structure;

public class StandardStack<T> {
    private StackNode<T> head;
    private int counter = 0;

    // One thread can read one value
    // Another thread can write another value
    // So make method syncronized
    public synchronized void push(T value) {
        StackNode<T> newHead = new StackNode<>(value);
        newHead.next = head;
        head = newHead;
        counter++;
    }

    // Here also make method synchronized
    public synchronized T pop() {
        if (head == null) {
            return null;
        }
        T value = head.value;
        head = head.next;
        counter++;
        return value;
    }

    public int getCounter() {
        return counter;
    }
}
