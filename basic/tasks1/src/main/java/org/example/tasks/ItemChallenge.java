package org.example.tasks;

class Item{
    public static int numItems = 0;
    final int ID;
    Item(int id){
        ID = id;
    }
    public static int getNextID(){
        return Item.numItems++;
    }
    public static Item getItem(){
        int id = Item.getNextID();
        Item item = new Item(id);
        return item;
    }
    public static void printNumItems(){
        System.out.println("Num items: " + Item.numItems);
    }
    public void printId(){
        System.out.println("ID: " + this.ID);
    }

}
public class ItemChallenge {
    public static void main(String[] args) {
        for(int i=0; i < 3; i++){
            Item item = Item.getItem();
            item.printId();
        }
        Item.printNumItems();
    }
}
