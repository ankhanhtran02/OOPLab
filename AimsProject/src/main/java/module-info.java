module hust.soict.dsai {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires javafx.swing;

    opens hust.soict.dsai.aims.media to javafx.base;
    opens hust.soict.dsai.aims.screen to javafx.fxml;
    exports hust.soict.dsai.aims.screen;
}