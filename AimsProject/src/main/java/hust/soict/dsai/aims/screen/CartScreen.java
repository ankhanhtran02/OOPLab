package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.store.Store;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javax.swing.JFrame;
import java.io.IOException;
import javafx.embed.swing.JFXPanel;

public class CartScreen extends JFrame{
    private Cart cart;
    private Store store;

//    private StoreScreen storeScreen;

    public CartScreen(Cart cart, Store store) {
        super();
        this.cart = cart;
        this.store = store;
//        this.storeScreen = storeScreen;
        JFXPanel jfxPanel = new JFXPanel();
        this.add(jfxPanel);
        this.setTitle("Cart");
        this.setVisible(true);
        this.setSize(800, 800);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("cart.fxml"));
                    CartScreenController controller = new CartScreenController(cart, CartScreen.this, store);
                    loader.setController(controller);
                    Parent root = loader.load();
                    jfxPanel.setScene(new Scene(root));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        Platform.startup(() -> new CartScreen(new Cart(), new Store()));
    }

}