package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.Track;
import hust.soict.dsai.aims.store.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField titleField;
    private JTextField categoryField;
    private JTextField costField;
    private JTextField artistField;
    private JPanel tracksPanel;
    private ArrayList<JTextField> trackTitleFields;
    private ArrayList<JTextField> trackLengthFields;

    public AddCompactDiscToStoreScreen(Store store, Cart cart) {
        super(store, cart, "Add CD");
        trackTitleFields = new ArrayList<>();
        trackLengthFields = new ArrayList<>();
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
        JLabel artistLabel = new JLabel("Artist:");
        artistField = new JTextField(20);

        // Set preferred size to reduce height
        Dimension textFieldSize = new Dimension(150, 25);
        titleField.setPreferredSize(textFieldSize);
        categoryField.setPreferredSize(textFieldSize);
        artistField.setPreferredSize(textFieldSize);
        costField.setPreferredSize(textFieldSize);

        JButton addCDButton = new JButton(this.title);
        addCDButton.addActionListener(new AddCDButtonListener());
        JButton addTrackButton = new JButton("Add track");
        addTrackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTrackField();
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

        gbc.gridx = 0;
        gbc.gridy = 3;
        center.add(artistLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        center.add(artistField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        center.add(addTrackButton, gbc);

        tracksPanel = new JPanel();
        tracksPanel.setLayout(new BoxLayout(tracksPanel, BoxLayout.Y_AXIS));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        center.add(tracksPanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        center.add(addCDButton, gbc);

        JScrollPane scrollPane = new JScrollPane(center);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        return mainPanel;
    }

    private void addTrackField() {
        JPanel trackPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        if (trackTitleFields.size() == 0) {
            JLabel trackTitleLabel = new JLabel("Title");
            JLabel trackLengthLabel = new JLabel("Length");
            gbc.gridx = 1;
            gbc.gridy = 0;
            trackPanel.add(trackTitleLabel, gbc);
            gbc.gridx = 2;
            trackPanel.add(trackLengthLabel, gbc);
        }
        JLabel trackLabel = new JLabel("Track " + (trackTitleFields.size() + 1) + ":" + "         ");
        JTextField trackTitleField = new JTextField(20);
        JTextField trackLengthField = new JTextField(10);
        trackTitleField.setPreferredSize(new Dimension(80, 25));
        trackLengthField.setPreferredSize(new Dimension(50, 25));


        trackTitleFields.add(trackTitleField);
        trackLengthFields.add(trackLengthField);

        gbc.gridx = 0;
        gbc.gridy = trackTitleFields.size();
        trackPanel.add(trackLabel, gbc);

        gbc.gridx = 1;
        trackPanel.add(trackTitleField, gbc);

        gbc.gridx = 2;
        trackPanel.add(trackLengthField, gbc);

        tracksPanel.add(trackPanel);
        tracksPanel.revalidate();
        tracksPanel.repaint();
    }

    private class AddCDButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String title = titleField.getText();
            String category = categoryField.getText();
            String artist = artistField.getText();
            String costText = costField.getText();

            if (title.isEmpty() || category.isEmpty() || artist.isEmpty() || costText.isEmpty()) {
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

            ArrayList<Track> tracks = new ArrayList<>();
            for (int i = 0; i < trackTitleFields.size(); i++) {
                String trackTitle = trackTitleFields.get(i).getText();
                String trackLengthText = trackLengthFields.get(i).getText();
                if (!trackTitle.isEmpty() && !trackLengthText.isEmpty()) {
                    int trackLength;
                    try {
                        trackLength = Integer.parseInt(trackLengthText);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Track length must be an integer.", "Warning", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    tracks.add(new Track(trackTitle, trackLength));
                }
            }

            if (tracks.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please add at least one track.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            CompactDisc cd = new CompactDisc(title, category, cost, artist, tracks);
            store.addMedia(cd);
            JOptionPane.showMessageDialog(null, "CD added to store successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Clear fields after successful addition
            titleField.setText("");
            categoryField.setText("");
            costField.setText("");
            artistField.setText("");
            tracksPanel.removeAll();
            trackTitleFields.clear();
            trackLengthFields.clear();
            tracksPanel.revalidate();
            tracksPanel.repaint();
        }
    }

    public static void main(String[] args) {
        Store s = new Store();
        Cart c = new Cart();
        new AddCompactDiscToStoreScreen(s, c);
    }
}
