package resource_sharing.synchronized_block;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        InventoryCounterSynchronizedBlock ic = new InventoryCounterSynchronizedBlock();
        IncrementingThreadSynchronizedBlock it = new IncrementingThreadSynchronizedBlock(ic);
        DecrementingThreadSynchronizedBlock dt = new DecrementingThreadSynchronizedBlock(ic);

        it.start();
        dt.start();

        it.join();
        dt.join();

        System.out.println("We currently have "+ ic.getItems()+" items");
    }
}
