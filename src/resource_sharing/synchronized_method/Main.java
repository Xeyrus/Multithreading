package resource_sharing.synchronized_method;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        InventoryCounterSynchronized ic = new InventoryCounterSynchronized();
        IncrementingThreadSynchronized it = new IncrementingThreadSynchronized(ic);
        DecrementingThreadSynchronized dt = new DecrementingThreadSynchronized(ic);

        it.start();
        dt.start();

        it.join();
        dt.join();

        System.out.println("We currently have "+ ic.getItems()+" items");
    }
}
