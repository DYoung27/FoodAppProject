package com.example.foodappproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        HelloController.loggedIn = false;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Image icon = new Image("https://i.pinimg.com/originals/dd/9d/c9/dd9dc9d83423bc037b511d73b29e6b80.png");

    // alt: https://i.pinimg.com/736x/38/e8/a8/38e8a81543c479e58f523d5b21c0208d.jpg

        stage.getIcons().add(icon);
        stage.setTitle("Food Inventory system");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}