package resource_sharing.non_synchronized;

public class InventoryCounter {
    private int items = 0;

    public void increment(){
        items++;
    }

    public void decrement(){
        items--;
    }

    public int getItems(){
        return items;
    }

}
