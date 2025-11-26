package resource_sharing.atomicInteger;

public class IncrementingThreadAtomic extends Thread {

    private InventoryCounterAtomic inventoryCounter;

    public IncrementingThreadAtomic(InventoryCounterAtomic inventoryCounterSynchronized) {
        this.inventoryCounter = inventoryCounterSynchronized;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            inventoryCounter.increment();
        }
    }
}
