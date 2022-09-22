package com.example.foodappproject;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;

public class ItemController {
    private final SceneController switcher = new SceneController();
    public Item item;
    public AnchorPane anchor;
    public Label title;
    public Button homeBtn;
    public Text productTitle;
    public ImageView productImg;
    public VBox badgeBox;
    public ScrollPane scrollBox;
    public ScrollPane descScrollBox;
    public VBox descriptionBox;
    public Button addButton;

    public void setItem(Item i) {
        this.item = i;
    }

    public void setDisplay() {
        System.out.println(item.badges);
        productTitle.setText(item.title);
        productTitle.setFont(Font.font(30));
        toggleButton();

        ImageGetter itemImg = new ImageGetter(item.image);
        productImg.setImage(itemImg.img);

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

        scrollBoxFunc(scrollBox, badgeBox);
        scrollBoxFunc(descScrollBox, descriptionBox);

        for (String badge: item.badges) {
            Label badgeLabel = new Label();
            badgeLabel.setMinWidth(badgeBox.getWidth());
            badgeLabel.setText("\u2022"+badge);
            badgeBox.getChildren().add(badgeLabel);
        }
        Label descLabel = new Label();
        descLabel.setText(item.description);
        descLabel.setMaxWidth(descriptionBox.getWidth() + 100);
        descLabel.setWrapText(true);
        descriptionBox.getChildren().add(descLabel);
    }

    public void switchToMainPage (ActionEvent event) throws IOException {
        switcher.switchToMainPage(event);
    }
    public void scrollBoxFunc (ScrollPane sBox, VBox vBox) {
        sBox.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.setMinSize(sBox.getWidth(), sBox.getHeight());
        if (sBox.getHeight() > vBox.getHeight()) {
            sBox.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        }
    }

    public void addToBasket(ActionEvent event) {
        HelloController.basket.add(item.title);
        System.out.println(HelloController.basket);
    }
    public void toggleButton () {
        if (HelloController.loggedIn) {
            addButton.setOpacity(1);
            addButton.setCursor(Cursor.HAND);
            addButton.setOnAction(this::addToBasket);
        }
        else {
            addButton.setOpacity(0);
            addButton.setCursor(Cursor.DEFAULT);
            addButton.setOnAction(null);
        }
    }
}
