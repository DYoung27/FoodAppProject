package com.example.foodappproject;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AccountController implements Initializable {
    private final SceneController switcher = new SceneController();
    public Button homeBtn;
    public VBox loginBox;
    public VBox createAccountBox;
    public Button switchBtn;
    public TextField loginUserName;
    public PasswordField loginPassword;
    public TextField createUserName;
    public PasswordField createPassword;
    public PasswordField createConPassword;
    public Text createErrorLabel;
    public Text loginErrorLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setShadow(loginBox);
        setShadow(createAccountBox);

        loginBox.setOpacity(0);

        switchBtn.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
        switchBtn.setTextFill(Color.BLACK);

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

    public void switchToMainPage (ActionEvent event) throws IOException {
        switcher.switchToMainPage(event);
    }
    public void setShadow(VBox box) {
        DropShadow ds = new DropShadow();
        ds.setOffsetY(2.0);
        ds.setOffsetX(2.0);
        ds.setColor(Color.GRAY);
        box.setEffect(ds);
    }
    public void switchDisplayFunc() {
        loginBox.setOpacity(1 - loginBox.getOpacity());
        createAccountBox.setOpacity(1 - createAccountBox.getOpacity());

        if (Objects.equals(switchBtn.getText(),"Create")) {
            switchBtn.setText("Login");
            switchBtn.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
            switchBtn.setTextFill(Color.WHITE);
            createAccountBox.setTranslateX(-2000);
            clearLoginFields();
        }
        else {
            switchBtn.setText("Create");
            switchBtn.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
            switchBtn.setTextFill(Color.BLACK);
            createAccountBox.setTranslateX(0);
            clearCreateFields();
        }
        loginErrorLabel.setText("");
        createErrorLabel.setText("");
    }
    public void createAccount () {
        createErrorLabel.setText("");
        if(!createBoxesFilled()) {
            createErrorLabel.setText("Not all fields filled");
        }
        else if (Accounts.accounts.containsKey(createUserName.getText())) {
            createErrorLabel.setText("Username already exists");
        }
        else if (!Objects.equals(createPassword.getText(), createConPassword.getText())) {
            createErrorLabel.setText("Passwords don't match");
        }
        else {
            Accounts.accounts.put(createUserName.getText(), createPassword.getText());
            clearCreateFields();
        }
    }
    public void loginAccount(ActionEvent event) throws IOException {
        loginErrorLabel.setText("");
        if(!loginBoxesFilled()) {
            loginErrorLabel.setText("Not all fields filled");
        }
        else if (HelloController.loggedIn) {
            loginErrorLabel.setText("Already logged in");
        }
        else if (!Accounts.accounts.containsKey(loginUserName.getText())) {
            loginErrorLabel.setText("No account with this username");
        }
        else if (!Objects.equals(loginPassword.getText(), Accounts.accounts.get(loginUserName.getText()))) {
            loginErrorLabel.setText("Password incorrect");
        }
        else {
            HelloController.loggedIn = true;
            HelloController.loginName = loginUserName.getText();
            clearLoginFields();
            switchToMainPage(event);
        }
    }
    public Boolean createBoxesFilled () {
        return !createUserName.getText().isEmpty() &&
                !createPassword.getText().isEmpty() &&
                !createConPassword.getText().isEmpty();
    }
    public Boolean loginBoxesFilled () {
        return !loginUserName.getText().isEmpty() &&
                !loginPassword.getText().isEmpty();
    }
    public void clearCreateFields () {
        createUserName.clear();
        createPassword.clear();
        createConPassword.clear();
    }
    public void clearLoginFields () {
        loginUserName.clear();
        loginPassword.clear();
    }
}
