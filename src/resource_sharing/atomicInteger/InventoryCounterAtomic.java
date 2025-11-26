package resource_sharing.atomicInteger;

import java.util.concurrent.atomic.AtomicInteger;

public class InventoryCounterAtomic {
    private AtomicInteger items = new AtomicInteger(0);

    public void increment() {
        items.incrementAndGet();
    }

    public void decrement() {
        items.decrementAndGet();
    }

    public int getItems() {
        return items.get();
    }

}
