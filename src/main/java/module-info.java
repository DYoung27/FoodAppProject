module com.example.foodappproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;


    opens com.example.foodappproject to javafx.fxml;
    exports com.example.foodappproject;
}