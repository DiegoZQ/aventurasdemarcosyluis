module com.example.aventurasdemarcoyluis {
    requires javafx.controls;
    requires javafx.fxml;


    opens animantia to javafx.fxml;
    exports animantia;
}