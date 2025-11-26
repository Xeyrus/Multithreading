package resource_sharing.synchronized_method;

public class IncrementingThreadSynchronized extends Thread {

    private InventoryCounterSynchronized inventoryCounter;

    public IncrementingThreadSynchronized(InventoryCounterSynchronized inventoryCounterSynchronized) {
        this.inventoryCounter = inventoryCounterSynchronized;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            inventoryCounter.increment();
        }
    }
}
