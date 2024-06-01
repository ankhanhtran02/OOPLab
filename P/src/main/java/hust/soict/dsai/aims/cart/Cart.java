package hust.soict.dsai.aims.cart;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Media;

import java.util.ArrayList;

public class Cart {
    public static final int MAX_NUMBER_ORDERED = 20;
    private ArrayList<Media> itemsOrdered = new ArrayList<Media>();
    public void addMedia(Media media){
        if (itemsOrdered.size() == MAX_NUMBER_ORDERED){
            System.out.println("Maximum number of orders exceeded");
            return;
        }
        itemsOrdered.add(media);
        System.out.println("Added the media to the cart");
    }
    public void removeMedia(Media media){
        if (itemsOrdered.contains(media)){
            itemsOrdered.remove(media);
            System.out.println("Removed the media from the cart");
        }
        else{
            System.out.println("The media doesn't exist in the cart");
        }
    }
    public float totalCost() {
        float totalCost = 0;
        for (Media item: itemsOrdered) {
            totalCost += item.getCost();
        }
        return totalCost;
    }

    public void print() {
        System.out.println("***********************CART***********************");
        System.out.println("Ordered Items:");
        int i = 0;
        for (Media item: itemsOrdered) {
            i++;
            System.out.println(i + ". " + item.toString());
        }
        System.out.println("Total cost: " + totalCost());
        System.out.println("***************************************************");
    }

    public Media searchByTitle(String title){
        for (Media item: itemsOrdered){
            if (item.isMatch(title)){
                return item;
            }
        }
        return null;
    }
    public Media searchById(int id){
        for (Media item: itemsOrdered){
            if (item.getId() == id){
                return item;
            }
        }
        return null;
    }

    public void filter(int id){
        Media item = searchById(id);
        System.out.println(item);
    }
    public void filter(String title){
        Media item = searchByTitle(title);
        System.out.println(item);
    }
    public void sort(int option){
        if (option == 1) {
            itemsOrdered.sort(Media.COMPARE_BY_COST_TITLE);
        }
        else{
            itemsOrdered.sort(Media.COMPARE_BY_TITLE_COST);
        }
        print();
    }

    public void placeOrder(){
        System.out.println("Your order has been placed");
        itemsOrdered.clear();
    }
}

