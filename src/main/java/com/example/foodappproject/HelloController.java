package com.example.foodappproject;

import com.google.gson.Gson;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    public TextField searchBar;
    public BorderPane borderPane;
    public GridPane gridPane;
    public Button prevBtn;
    public Button nextBtn;
    public int offset;
    public int globalFoods;
    private final SceneController switcher = new SceneController();
    public Text burgerTitle;
    public Text pizzaTitle;
    public Text veganTitle;
    public HBox logoutBox;
    public HBox basketBox;
    static Boolean loggedIn;
    static String loginName = "";
    static ArrayList<String> basket = new ArrayList<>();

    public void clearItem() {
        gridPane.getChildren().removeIf(node -> node instanceof Label);
        for (Node obj: gridPane.getChildren()) {
            if (obj instanceof ImageView newObj) {
                newObj.setImage(null);
                newObj.setOnMouseClicked(null);
                newObj.setCursor(Cursor.DEFAULT);
            }
        }
    }
    public void searchItem(String newVal, int offset) {
        clearItem();
        if (!Objects.equals(newVal, "")) {
            newVal = newVal.replace(' ', '+');
       //     String Url = String.format("https://api.spoonacular.com/food/products/search?query=%s&offset=%d&apiKey=3773caf1b91f4d93a3337e8477292ce4", newVal, offset);
            // Foods get = API.ValidateLink(Url);
            Foods get = API.ValidateLinkProto(seedData.burgers);
         //   System.out.println(get.products);
            ArrayList<Object> foods = get.products;
            globalFoods = Math.min(get.totalProducts, 990);
            if (globalFoods <= 8 && nextBtn.isVisible()) {
                nextBtn.setVisible(false);
            }
            int row, column, count;
            row = column = count = 0;
            if (foods != null) {
                Gson gson = new Gson();
                for (Object obj : foods) {
                    String resFood = gson.toJson(obj);
                    Food food = gson.fromJson(resFood, Food.class);
                    if (food.title.toLowerCase().contains(newVal.toLowerCase())) {
                        ImageView imgFrame = (ImageView) gridPane.getChildren().get(count);
                        count++;

                        ImageGetter foodImg = new ImageGetter(food.image);

                        imgFrame.setImage(foodImg.img);
                        imgFrame.setOnMouseClicked(mouseEvent -> {
                            try {
                                switchToItemPage(mouseEvent, food.id - 1);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
                        imgFrame.setCursor(Cursor.HAND);


                        Label title = new Label();
                        title.setFont(Font.font(15));
    //                    title.setWrapText(true);
    //                    title.setMaxWidth(135);
    //                    title.setMaxHeight(40);
                        title.setText(food.title);
                        title.setTextAlignment(TextAlignment.CENTER);
                        gridPane.add(title, column, row);


                        if (column == 0) {
                            column++;
                        }
                        else if (column == 1) {
                            if (row == 3) break;
                            column--;
                            row++;
                        }
                    }
                }
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // check if logged in
        if(HelloController.loggedIn) showLoginFunc();
        else hideLogoutFunc();

        // set buttons to default invisible
        prevBtn.setVisible(false);
        nextBtn.setVisible(false);


        gridPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        // search bar listener to detect changes and change the food products displayed accordingly
        //NOT AVAILABLE WHILE SPOONACULAR ARE UNDER MAINTENANCE
        searchBar.textProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldVal, String newVal) {
                offset = 0;
//                if (prevBtn.isVisible()) {
//                    prevBtn.setVisible(false);
//                }
//
//                if (!newVal.isEmpty() && !nextBtn.isVisible()) {
//                    //nextBtn.setVisible(true);
//                    setShadow(nextBtn);
//                }
//                else if (newVal.isEmpty()) {
//                    nextBtn.setVisible(false);
//                }
                searchItem(newVal, offset);
            }
        });
    }

    public void nextPage() {
        System.out.println(offset);

        if (offset < globalFoods) {
            if(!prevBtn.isVisible()) {
             //   prevBtn.setVisible(true);
                setShadow(prevBtn);
            }
            offset += 8;
            searchItem(searchBar.textProperty().get(), offset);
        }
        else {
            nextBtn.setVisible(false);
        }
    }

    public void prevPage() {
        System.out.println(offset);

        if (offset != 0) {
            if(!nextBtn.isVisible()) {
               // nextBtn.setVisible(true);
                setShadow(nextBtn);
            }
            offset -= 8;
            searchItem(searchBar.textProperty().get(), offset);
        }
        if (offset == 0) {
            prevBtn.setVisible(false);
        }
    }

    public void burgerSearch() {
        searchBar.setText("burger");
    }

    public void pizzaSearch() {
        searchBar.setText("pizza");
    }

    public void veganSearch() {
        searchBar.setText("vegan");
    }

    public void switchToItemPage(MouseEvent mouseEvent, int id) throws IOException {
        switcher.switchToItemPage(mouseEvent, id);
    }

    public void switchToAccountPage(MouseEvent mouseEvent) throws IOException {
        switcher.switchToAccountPage(mouseEvent);
    }
    public void switchToBasketPage(MouseEvent event) throws IOException {
        switcher.switchToBasketPage(event);
    }
    public void onHoverSideBar(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof Button) {
            ((Button) mouseEvent.getSource()).setOpacity(0.5);
        }
        else {
            HBox hbox = (HBox) mouseEvent.getSource();
            for (Node el : hbox.getChildren()) {
                el.setOpacity(0.5);
            }
        }
    }
    public void outHoverSideBar(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof Button) {
            ((Button) mouseEvent.getSource()).setOpacity(1);
        }
        else {
            HBox hbox = (HBox) mouseEvent.getSource();
            for (Node el : hbox.getChildren()) {
                el.setOpacity(1);
            }
        }
    }

    public void setShadow(Button btn) {
        DropShadow ds = new DropShadow();
        ds.setOffsetY(2.0);
        ds.setOffsetX(2.0);
        ds.setColor(Color.GRAY);
        btn.setEffect(ds);

    }

    public void exitSystemFunc() {
        System.exit(0);
    }

    public void logoutFunc(MouseEvent mouseEvent) {
        HelloController.loggedIn = false;
        HelloController.loginName = "";
        HelloController.basket.clear();
        hideLogoutFunc();
    }

    // hide Logout button when logged out
    public void hideLogoutFunc() {
        logoutBox.setOpacity(0);
        logoutBox.setOnMouseClicked(null);
        logoutBox.setCursor(Cursor.DEFAULT);
        basketBox.setOpacity(0);
        basketBox.setOnMouseClicked(null);
        basketBox.setCursor(Cursor.DEFAULT);

        if(!HelloController.basket.isEmpty()) {
            HelloController.basket.clear();
        }
    }

    // show logout button when logged in
    public void showLoginFunc() {
        logoutBox.setOpacity(1);
        logoutBox.setOnMouseClicked(this::logoutFunc);
        logoutBox.setCursor(Cursor.HAND);
        basketBox.setOpacity(1);
        basketBox.setOnMouseClicked(mouseEvent -> {
            try {
                switchToBasketPage(mouseEvent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        basketBox.setCursor(Cursor.HAND);
    }
}