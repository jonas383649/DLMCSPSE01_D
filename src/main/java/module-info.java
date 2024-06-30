module com.iu.memorylearnapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.iu.memorylearnapp to javafx.fxml;
    exports com.iu.memorylearnapp;
}