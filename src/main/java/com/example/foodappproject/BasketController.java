package com.example.foodappproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BasketController implements Initializable{
    private final SceneController switcher = new SceneController();
    public Button homeBtn;
    public ListView<String> basketList;
    public Button deleteBtn;
    public Button clearBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // ObservableList<String> items = FXCollections.observableArrayList();
        //items.addAll(HelloController.basket.keySet());
        basketList.getItems().addAll(HelloController.basket);

        ImageView homeIconFrame = new ImageView();
        Image homeIcon = new Image("https://www.nicepng.com/png/full/115-1153942_white-home-icon-png-white-home-logo-transparent.png");

        homeIconFrame.setFitHeight(30);
        homeIconFrame.setFitWidth(30);
        homeIconFrame.setImage(homeIcon);

        homeBtn.setGraphic(homeIconFrame);
        homeBtn.setOnAction(event -> {
            try {
                switchToMainPage(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void switchToMainPage(ActionEvent event) throws IOException {
        switcher.switchToMainPage(event);
    }

    public void deleteItem(ActionEvent event) {
        int index = basketList.getSelectionModel().getSelectedIndex();
        HelloController.basket.remove(index);
        basketList.getItems().clear();
        basketList.getItems().addAll(HelloController.basket);
    }

    public void clearItems(ActionEvent event) {
        HelloController.basket.clear();
        basketList.getItems().clear();

    }
}
