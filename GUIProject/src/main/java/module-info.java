module hust.soict.dsai.javafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens hust.soict.dsai.javafx to javafx.fxml;
    exports hust.soict.dsai.javafx;
}