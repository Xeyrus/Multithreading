package resource_sharing;

public class RaceCondition {
    public static void main(String[] args) throws InterruptedException {
        InventoryCounter ic = new InventoryCounter();
        IncrementingThread it = new IncrementingThread(ic);
        DecrementingThread dt = new DecrementingThread(ic);

        it.start();
        dt.start();

        it.join();
        dt.join();

        System.out.println("We currently have "+ ic.getItems()+" items");
    }
}
