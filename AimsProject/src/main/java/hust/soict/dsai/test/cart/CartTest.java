package hust.soict.dsai.test.cart;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.DigitalVideoDisc;

public class CartTest {
    public static void main(String[] args) {
        Cart cart = new Cart();
        DigitalVideoDisc d1 = new DigitalVideoDisc("Whisper of the Heart", "Animation", "Yoshifumi Kondo", 111, 8.3f);
        DigitalVideoDisc d2 = new DigitalVideoDisc("Contratiempo", "Crime", "Oriol Paulo", 106, 30.0f);
        DigitalVideoDisc[] dvdList = {d1, d2};
//        cart.addDigitalVideoDisc(dvdList);
        cart.print();
        cart.searchById(1);
        cart.searchByTitle("The scorpio has no heart");
    }
}
