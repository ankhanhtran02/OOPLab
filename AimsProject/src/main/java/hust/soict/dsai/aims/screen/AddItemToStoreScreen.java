package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.store.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class AddItemToStoreScreen extends JFrame {
    protected Store store;
    protected Cart cart;
    protected String title;
    private JTextField titleField;
    private JTextField categoryField;
    private JTextField directorField;
    private JTextField lengthField;
    private JTextField costField;

    public AddItemToStoreScreen(Store store, Cart cart, String title) {
        this.store = store;
        this.cart = cart;
        this.title = title;

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);

        setTitle(this.title);
        setSize(800, 500);
        setVisible(true);
    }

    JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }

    JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");
        JMenu smUpdateStore = new JMenu("Update Store");
        JMenuItem addBookItem = new JMenuItem("Add Book");
        addBookItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddBookToStoreScreen(store, cart);
                AddItemToStoreScreen.this.dispose();
            }
        });
        smUpdateStore.add(addBookItem);

        JMenuItem addCDItem = new JMenuItem("Add CD");
        addCDItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddCompactDiscToStoreScreen(store, cart);
                AddItemToStoreScreen.this.dispose();
            }
        });
        smUpdateStore.add(addCDItem);

        JMenuItem addDVDItem = new JMenuItem("Add DVD");
        addDVDItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddDigitalVideoDiscToStoreScreen(store, cart);
                AddItemToStoreScreen.this.dispose();
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

    JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
        JLabel title = new JLabel(this.title);
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground(Color.CYAN);
        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(Box.createRigidArea(new Dimension(10, 10)));
        return header;
    }

    abstract JPanel createCenter();


    private class ViewCartButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new CartScreen(cart, store);
            AddItemToStoreScreen.this.dispose();
        }
    }

    private class ViewStoreButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new StoreScreen(store, cart);
            AddItemToStoreScreen.this.dispose();
        }
    }
}
