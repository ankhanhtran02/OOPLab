package hust.soict.dsai.aims.store;

import hust.soict.dsai.aims.disc.DigitalVideoDisc;

public class Store {
    public static int MAX_QUANTITY = 1000000;
    private DigitalVideoDisc[] itemsInStore = new DigitalVideoDisc[MAX_QUANTITY];
    private int quantity = 0;
    public void addDVD(DigitalVideoDisc disc){
        if (quantity < MAX_QUANTITY) {
            itemsInStore[quantity] = disc;
            quantity++;
            System.out.println("The disc has been added.");
        } else {
            System.out.println("Maximum number of DVDs exceeded.");
        }
    }
    public void removeDVD(DigitalVideoDisc disc){
        boolean removed = false;
        for (int i = 0; i < quantity; i++) {
            if (itemsInStore[i] == disc) {
                for (int j = i; j < quantity - 1; j++) {
                    itemsInStore[j] = itemsInStore[j + 1];
                }
                quantity--;
                System.out.println("The disc has been removed.");
                removed = true;
                break;
            }
        }
        if (!removed) {
            System.out.println("The disc is not found.");
        }
    }
    public void print(){
        System.out.println("***********************STORE***********************");
        System.out.println("In-store items:");
        for (int i = 0; i < quantity; i++){
            System.out.println(itemsInStore[i].toString());
        }
        System.out.println("***************************************************");
    }
}
