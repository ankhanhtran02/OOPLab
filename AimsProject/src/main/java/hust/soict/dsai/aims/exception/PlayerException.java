package hust.soict.dsai.aims.exception;
import javax.swing.*;

public class PlayerException extends Exception{
    public PlayerException(String message) {
        super(message);
        JOptionPane.showMessageDialog(null, message, "Warning", JOptionPane.WARNING_MESSAGE);
    }
}
