module org.example.guiproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens org.example.guiproject to javafx.fxml;
    exports org.example.guiproject;
}