package resource_sharing.synchronized_block;

public class InventoryCounterSynchronizedBlock {

    Object lock = new Object();

    private int items = 0;

    public void increment(){
        synchronized (this.lock) {
            items++;
        }
    }

    public void decrement(){
        synchronized (this.lock) {
            items--;
        }
    }

    public int getItems(){
        synchronized (lock) {
            return items;
        }
    }

}
