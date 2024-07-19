package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.Track;
import hust.soict.dsai.aims.store.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AddBookToStoreScreen extends AddItemToStoreScreen{
    private JTextField titleField;
    private JTextField categoryField;
    private JTextField costField;
    private ArrayList<JTextField> authorFields;
    private JPanel authorsPanel;
    public AddBookToStoreScreen(Store store, Cart cart) {
        super(store, cart, "Add book");
        authorFields = new ArrayList<>();
    }

    @Override
    JPanel createCenter() {
        JPanel center = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel titleLabel = new JLabel("Title:");
        titleField = new JTextField(20);
        JLabel categoryLabel = new JLabel("Category:");
        categoryField = new JTextField(20);
        JLabel costLabel = new JLabel("Cost (USD):");
        costField = new JTextField(20);

        // Set preferred size to reduce height
        Dimension textFieldSize = new Dimension(150, 25);
        titleField.setPreferredSize(textFieldSize);
        categoryField.setPreferredSize(textFieldSize);
        costField.setPreferredSize(textFieldSize);

        JButton addBookButton = new JButton(this.title);
        addBookButton.addActionListener(new AddBookButtonListener());
        JButton addAuthorButton = new JButton("Add author");
        addAuthorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAuthorField();
            }
        });

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
        center.add(costLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        center.add(costField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        center.add(addAuthorButton, gbc);

        authorsPanel = new JPanel();
        authorsPanel.setLayout(new BoxLayout(authorsPanel, BoxLayout.Y_AXIS));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        center.add(authorsPanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        center.add(addBookButton, gbc);

        JScrollPane scrollPane = new JScrollPane(center);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        return mainPanel;
    }

    private void addAuthorField() {
        JPanel authorPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel authorLabel = new JLabel("Author " + (authorFields.size() + 1) + ":" + "        ");
        JTextField authorField = new JTextField(20);
        authorField.setPreferredSize(new Dimension(150, 25));

        authorFields.add(authorField);

        gbc.gridx = 0;
        gbc.gridy = authorFields.size() - 1;
        authorPanel.add(authorLabel, gbc);

        gbc.gridx = 1;
        authorPanel.add(authorField, gbc);

        authorsPanel.add(authorPanel);
        authorsPanel.revalidate();
        authorsPanel.repaint();
    }

    private class AddBookButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String title = titleField.getText();
            String category = categoryField.getText();
            String costText = costField.getText();

            if (title.isEmpty() || category.isEmpty() || costText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            float cost;
            try {
                cost = Float.parseFloat(costText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Cost must be a float.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            ArrayList<String> authors = new ArrayList<>();
            for (int i = 0; i < authorFields.size(); i++) {
                String authorName = authorFields.get(i).getText();
                if (!authorName.isEmpty()) {
                    authors.add(authorName);
                }
            }

            if (authors.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please add at least one author.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            List<String> authorsList = authors;
            Book book = new Book(title, category, cost, authorsList);
            store.addMedia(book);
            JOptionPane.showMessageDialog(null, "Book added to store successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Clear fields after successful addition
            titleField.setText("");
            categoryField.setText("");
            costField.setText("");
            authorsPanel.removeAll();
            authorFields.clear();
            authorsPanel.revalidate();
            authorsPanel.repaint();
        }
    }

    public static void main(String[] args) {
        Store s = new Store();
        Cart c = new Cart();
        new AddBookToStoreScreen(s, c);
    }
}
