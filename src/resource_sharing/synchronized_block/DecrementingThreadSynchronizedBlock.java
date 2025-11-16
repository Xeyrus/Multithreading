package resource_sharing.synchronized_block;

public class DecrementingThreadSynchronizedBlock extends Thread{

    private InventoryCounterSynchronizedBlock inventoryCounterSynchronizedBlock;

    public DecrementingThreadSynchronizedBlock(InventoryCounterSynchronizedBlock inventoryCounterSynchronizedBlock) {
        this.inventoryCounterSynchronizedBlock = inventoryCounterSynchronizedBlock;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            inventoryCounterSynchronizedBlock.decrement();
        }
    }
}
