package resource_sharing.synchronized_block;

public class IncrementingThreadSynchronizedBlock extends Thread{

    private InventoryCounterSynchronizedBlock inventoryCounter;

    public IncrementingThreadSynchronizedBlock(InventoryCounterSynchronizedBlock inventoryCounterSynchronizedBlock) {
        this.inventoryCounter = inventoryCounterSynchronizedBlock;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            inventoryCounter.increment();
        }
    }
}
