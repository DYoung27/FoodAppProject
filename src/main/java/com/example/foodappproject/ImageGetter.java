package com.example.foodappproject;

import javafx.scene.image.Image;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageGetter {
    public Image img;
    public ImageGetter(String image) {
        URL imgUrl = null;
        try {
            imgUrl = new URL(image);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        try {
            img = new Image(imgUrl.openStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public ImageGetter(URL image) {
        try {
            img = new Image(image.openStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}