package lock_free_operations.lock_free_data_structure;

public class StackNode<T> {

    public T value;
    public StackNode<T> next;

    public StackNode(T value) {
        this.value = value;
    }

}
