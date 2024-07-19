package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.store.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField titleField;
    private JTextField categoryField;
    private JTextField directorField;
    private JTextField lengthField;
    private JTextField costField;

    public AddDigitalVideoDiscToStoreScreen(Store store, Cart cart) {
        super(store, cart, "Add DVD");
    }

    JPanel createCenter() {
        JPanel center = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel titleLabel = new JLabel("Title:");
        titleField = new JTextField(20);
        JLabel categoryLabel = new JLabel("Category:");
        categoryField = new JTextField(20);
        JLabel directorLabel = new JLabel("Director:");
        directorField = new JTextField(20);
        JLabel lengthLabel = new JLabel("Length (minutes):");
        lengthField = new JTextField(20);
        JLabel costLabel = new JLabel("Cost (USD):");
        costField = new JTextField(20);

        // Set preferred size to reduce height
        Dimension textFieldSize = new Dimension(150, 25);
        titleField.setPreferredSize(textFieldSize);
        categoryField.setPreferredSize(textFieldSize);
        directorField.setPreferredSize(textFieldSize);
        lengthField.setPreferredSize(textFieldSize);
        costField.setPreferredSize(textFieldSize);

        JButton addButton = new JButton(this.title);
        addButton.addActionListener(new AddButtonListener());

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        center.add(titleLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        center.add(titleField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        center.add(categoryLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        center.add(categoryField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        center.add(directorLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        center.add(directorField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        center.add(lengthLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        center.add(lengthField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        center.add(costLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        center.add(costField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        center.add(addButton, gbc);

        return center;
    }

    private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String title = titleField.getText();
            String category = categoryField.getText();
            String director = directorField.getText();
            String lengthText = lengthField.getText();
            String costText = costField.getText();

            if (title.isEmpty() || category.isEmpty() || director.isEmpty() || lengthText.isEmpty() || costText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int length;
            float cost;
            try {
                length = Integer.parseInt(lengthText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Length must be an integer.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                cost = Float.parseFloat(costText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Cost must be a number.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            DigitalVideoDisc dvd = new DigitalVideoDisc(title, category, director, length, cost);
            store.addMedia(dvd);
            JOptionPane.showMessageDialog(null, "DVD added to store successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Clear fields after successful addition
            titleField.setText("");
            categoryField.setText("");
            directorField.setText("");
            lengthField.setText("");
            costField.setText("");
        }
    }

    private class ViewCartButtonListener implements ActionListener{
        private Cart cart;
        private AddDigitalVideoDiscToStoreScreen addScreen;
        private Store store;

        ViewCartButtonListener(Cart cart, AddDigitalVideoDiscToStoreScreen addScreen, Store store){
            this.cart = cart;
            this.addScreen = addScreen;
            this.store = store;
        }
        @Override
        public void actionPerformed(ActionEvent e){
            new CartScreen(this.cart, this.store);
            this.addScreen.dispose();
        }
    }

    private class ViewStoreButtonListener implements ActionListener{
        private Cart cart;
        private AddDigitalVideoDiscToStoreScreen addScreen;
        private Store store;

        ViewStoreButtonListener(Cart cart, AddDigitalVideoDiscToStoreScreen addScreen, Store store){
            this.cart = cart;
            this.addScreen = addScreen;
            this.store = store;
        }
        @Override
        public void actionPerformed(ActionEvent e){
            new StoreScreen(this.store, this.cart);
            this.addScreen.dispose();
        }
    }

    public static void main(String[] args) {
        Store s = new Store();
        Cart c = new Cart();
        new AddDigitalVideoDiscToStoreScreen(s, c);
    }
}
