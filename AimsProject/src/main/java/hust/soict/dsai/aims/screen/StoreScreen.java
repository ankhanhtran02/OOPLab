package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.*;
import hust.soict.dsai.aims.store.Store;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class StoreScreen extends JFrame {
    private Store store;
    private Cart cart;
    JPanel createNorth(){
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }
    JMenuBar createMenuBar(){
        JMenu menu = new JMenu("Options");
        JMenu smUpdateStore = new JMenu("Update Store");
        JMenuItem addBookItem = new JMenuItem("Add Book");
        addBookItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddBookToStoreScreen(store, cart);
                StoreScreen.this.dispose();
            }
        });
        smUpdateStore.add(addBookItem);

        JMenuItem addCDItem = new JMenuItem("Add CD");
        addCDItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddCompactDiscToStoreScreen(store, cart);
                StoreScreen.this.dispose();
            }
        });
        smUpdateStore.add(addCDItem);

        JMenuItem addDVDItem = new JMenuItem("Add DVD");
        addDVDItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddDigitalVideoDiscToStoreScreen(store, cart);
                StoreScreen.this.dispose();
            }
        });
        smUpdateStore.add(addDVDItem);


        menu.add(smUpdateStore);
        JMenuItem viewStoreItem = new JMenuItem("View store");
        viewStoreItem.addActionListener(new ViewStoreButtonListener());
        menu.add(viewStoreItem);
        JMenuItem viewCartItem = new JMenuItem("View cart");
        viewCartItem.addActionListener(new ViewCartButtonListener());
        menu.add(viewCartItem);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);
        return menuBar;
    }

    JPanel createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new GridLayout (3, 3, 2, 2));

        ArrayList<Media> mediaInStore = store.getItemsInStore();
        for (int i = 0; i < mediaInStore.size(); i++) {
            MediaStore cell = new MediaStore(cart, mediaInStore.get(i));
            center.add(cell);
        }

        return center;
    }

    JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout (header, BoxLayout.X_AXIS));
        JLabel title = new JLabel("AIMS");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground(Color.CYAN);
        JButton cart = new JButton("View cart");
        cart.setPreferredSize (new Dimension(100, 50)); cart.setMaximumSize(new Dimension (100, 50));
        cart.addActionListener(new ViewCartButtonListener());
        header.add(Box.createRigidArea (new Dimension (10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(cart);
        header.add(Box.createRigidArea (new Dimension (10, 10)));
        return header;
    }

    private class ViewCartButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            new CartScreen(cart, store);
            StoreScreen.this.dispose();
        }
    }

    private class ViewStoreButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            new StoreScreen(store, cart);
            StoreScreen.this.dispose();
        }
    }

    public StoreScreen() {
        Store s = new Store();
        DigitalVideoDisc m1 = new DigitalVideoDisc("Whisper of the Heart", "Animation", "Yoshifumi Kondo", 111, 8.3f);
        DigitalVideoDisc m2 = new DigitalVideoDisc("Contratiempo", "Crime", "Oriol Paulo", 106, 30.0f);
        Book m3 = new Book("Endless Night", "Detective", 8.03f, Arrays.asList("Agatha Christie"));
        Track track1 = new Track("Billie Jean", 181);
        Track track2 = new Track("Thriller", 190);
        ArrayList<Track> tracks1 = new ArrayList<>(Arrays.asList(track1, track2));
        CompactDisc m4 = new CompactDisc("Thriller", "Music", 51.5f, "Michael Jackson", tracks1);
        DigitalVideoDisc m5 = new DigitalVideoDisc("The Shawshank Redemption", "Drama", "Frank Darabont", 142, 9.3f);
        DigitalVideoDisc m6 = new DigitalVideoDisc("Inception", "Sci-Fi", "Christopher Nolan", 148, 8.8f);
        Book m7 = new Book("The Hobbit", "Fantasy", 10.99f, Arrays.asList("J.R.R. Tolkien"));
        Track track3 = new Track("Hey Jude", 431);
        Track track4 = new Track("Let It Be", 243);
        ArrayList<Track> tracks2 = new ArrayList<>(Arrays.asList(track3, track4));
        CompactDisc m8 = new CompactDisc("The Beatles", "Music", 40.0f, "The Beatles", tracks2);
        Book m9 = new Book("To Kill a Mockingbird", "Fiction", 7.99f, Arrays.asList("Harper Lee"));

        ArrayList<Media> mediaList = new ArrayList<>(Arrays.asList(m1, m2, m3, m4, m5, m6, m7, m8, m9));
        for (Media m: mediaList) {
            s.addMedia(m);
        }
        this.store = s;
        this.cart = new Cart();
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);
        setVisible(true);
        setTitle("Store");
        setSize(1024, 768);
    }
    public StoreScreen(Store store, Cart cart){
        this.store = store;
        this.cart = cart;
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);
        setVisible(true);
        setTitle("Store");
        setSize(1024, 768);
    }


    public static void main(String[] args) {
        StoreScreen screen = new StoreScreen();

    }
}
