package com.example.foodappproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {

    private Stage stage;
    private Scene scene;

    public void switchToItemPage (MouseEvent event, int id) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("item-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());

        Image icon = new Image("https://i.pinimg.com/originals/dd/9d/c9/dd9dc9d83423bc037b511d73b29e6b80.png");
        // alt: https://i.pinimg.com/736x/38/e8/a8/38e8a81543c479e58f523d5b21c0208d.jpg

        Item get = API.singleProductProto(id);
        ItemController controller = fxmlLoader.getController();
        controller.setItem(get);

        stage.getIcons().add(icon);
        stage.setTitle("Food item page");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        controller.setDisplay();
    }
    public void switchToMainPage (ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        Image icon = new Image("https://i.pinimg.com/originals/dd/9d/c9/dd9dc9d83423bc037b511d73b29e6b80.png");
        // alt: https://i.pinimg.com/736x/38/e8/a8/38e8a81543c479e58f523d5b21c0208d.jpg
        stage.getIcons().add(icon);
        stage.setTitle("Food Searching system");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToAccountPage (MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("account-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        Image icon = new Image("https://i.pinimg.com/originals/dd/9d/c9/dd9dc9d83423bc037b511d73b29e6b80.png");
        // alt: https://i.pinimg.com/736x/38/e8/a8/38e8a81543c479e58f523d5b21c0208d.jpg
        stage.getIcons().add(icon);
        stage.setTitle("Food Searching system");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToBasketPage(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("basket-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        Image icon = new Image("https://i.pinimg.com/originals/dd/9d/c9/dd9dc9d83423bc037b511d73b29e6b80.png");
        // alt: https://i.pinimg.com/736x/38/e8/a8/38e8a81543c479e58f523d5b21c0208d.jpg
        stage.getIcons().add(icon);
        stage.setTitle("Food Searching system");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
