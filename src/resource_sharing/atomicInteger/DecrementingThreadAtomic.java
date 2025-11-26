package resource_sharing.atomicInteger;

public class DecrementingThreadAtomic extends Thread {

    private InventoryCounterAtomic inventoryCounterSynchronized;

    public DecrementingThreadAtomic(InventoryCounterAtomic inventoryCounterSynchronized) {
        this.inventoryCounterSynchronized = inventoryCounterSynchronized;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            inventoryCounterSynchronized.decrement();
        }
    }
}
