package resource_sharing.synchronized_method;

public class DecrementingThreadSynchronized extends Thread{

    private InventoryCounterSynchronized inventoryCounterSynchronized;

    public DecrementingThreadSynchronized(InventoryCounterSynchronized inventoryCounterSynchronized) {
        this.inventoryCounterSynchronized = inventoryCounterSynchronized;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            inventoryCounterSynchronized.decrement();
        }
    }
}
