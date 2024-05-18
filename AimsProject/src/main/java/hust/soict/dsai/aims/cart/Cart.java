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

    public void searchById(int id) {
        boolean found = false;
        System.out.println("Search: " + id);
        System.out.println("Search result:");
        int qtyOrdered = itemsOrdered.size();
        for (int i = 0; i < qtyOrdered; i++ ) {
            Media item = itemsOrdered.get(i);
            if (item.getId() == id) {
                System.out.println(item.toString());
                found = true;
                break;
            }
            }
        if (!found) {
            System.out.println("No match is found.");
        }
    }

    public void searchByTitle(String title) {
        System.out.println("Search: " + title);
        System.out.println("Search result:");
        boolean found = false;
        int qtyOrdered = itemsOrdered.size();
        for (int i = 0; i < qtyOrdered; i++) {
            Media item = itemsOrdered.get(i);
            if (item.isMatch(title)) {
                System.out.println(item.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No match is found.");
        }
    }
}

