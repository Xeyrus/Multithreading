package resource_sharing.atomicInteger;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        InventoryCounterAtomic ic = new InventoryCounterAtomic();
        IncrementingThreadAtomic it = new IncrementingThreadAtomic(ic);
        DecrementingThreadAtomic dt = new DecrementingThreadAtomic(ic);

        it.start();
        dt.start();

        it.join();
        dt.join();

        System.out.println("We currently have " + ic.getItems() + " items");
    }
}
