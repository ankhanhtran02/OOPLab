package hust.soict.dsai.aims.store;

import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Media;

import java.util.ArrayList;

public class Store {
    public static int MAX_QUANTITY = 50000;
    private ArrayList<Media> itemsInStore = new ArrayList<Media>();
    public void addMedia(Media media){
        if (itemsInStore.size() == MAX_QUANTITY){
            System.out.println("Maximum quantity exceeded");
            return;
        }
        itemsInStore.add(media);
        System.out.println("Added the media to the store");
    }
    public void removeMedia(Media media){
        if (itemsInStore.contains(media)){
            itemsInStore.remove(media);
            System.out.println("Removed the media from the store");
        }
        else{
            System.out.println("The media doesn't exist in the store");
        }
    }
    public void print(){
        System.out.println("***********************STORE***********************");
        System.out.println("In-store items:");
        for (Media item: itemsInStore){
            System.out.println(item.toString());
        }
        System.out.println("***************************************************");
    }

    public Media searchByTitle(String title){
        for (Media item: itemsInStore){
            if (item.isMatch(title)){
                return item;
            }
        }
        return null;
    }
    public Media searchByID(int id){
        for (Media item: itemsInStore){
            if (item.getId() == id){
                return item;
            }
        }
        return null;
    }
}
