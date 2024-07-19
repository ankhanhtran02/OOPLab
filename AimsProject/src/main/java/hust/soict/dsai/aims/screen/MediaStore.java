package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.exception.PlayerException;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MediaStore extends JPanel {
    private Media media;
    private Cart cart;
    public MediaStore(Cart cart, Media media) {
        this.cart = cart;
        this.media = media;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel title = new JLabel(media.getTitle());
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 20));
        title.setAlignmentX(CENTER_ALIGNMENT);
        JLabel cost = new JLabel(media.getCost() + " $");
        cost.setAlignmentX(CENTER_ALIGNMENT);
        JPanel container = new JPanel();
        JButton addToCartButton = new JButton("Add to cart");
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cart.addMedia(media);
                JOptionPane.showMessageDialog(null, media.getTitle() + " added to cart.");
            }
        });
        container.setLayout(new FlowLayout(FlowLayout.CENTER));
        container.add(addToCartButton);
        if (media instanceof Playable) {
            JButton playButton = new JButton("Play");
            playButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        ((Playable) media).play();
                    } catch (PlayerException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            container.add(playButton);
        }
        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(cost);
        this.add(Box.createVerticalGlue());
        this.add(container);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public Cart getCart() {
        return cart;
    }
}