package resource_sharing.synchronized_method;

public class InventoryCounterSynchronized {
    private int items = 0;

    public synchronized void increment() {
        items++;
    }

    public synchronized void decrement() {
        items--;
    }

    public synchronized int getItems() {
        return items;
    }

}
