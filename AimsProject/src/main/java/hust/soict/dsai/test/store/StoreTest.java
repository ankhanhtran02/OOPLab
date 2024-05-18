package hust.soict.dsai.test.store;

import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.store.Store;

public class StoreTest {
    public static void main(String[] args) {
        Store store = new Store();
        DigitalVideoDisc d1 = new DigitalVideoDisc("Whisper of the Heart", "Animation", "Yoshifumi Kondo", 111, 8.3f);
        DigitalVideoDisc d2 = new DigitalVideoDisc("Contratiempo", "Crime", "Oriol Paulo", 106, 30.0f);
//        DigitalVideoDisc d3 = new DigitalVideoDisc("Scorpion", "Crime", 25.59f);
//        store.addDVD(d1);
//        store.addDVD(d2);
//        store.addDVD(d3);
        store.print();
//        store.removeDVD(d2);
        store.print();

    }
}
