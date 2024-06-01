module hust.soict.dsai {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens hust.soict.dsai.javafx to javafx.fxml;
    exports hust.soict.dsai.javafx;
}