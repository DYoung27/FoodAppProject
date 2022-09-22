package com.example.foodappproject;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import com.google.gson.Gson;
public class API {
    public static HttpClient client;

    public static void main() {

        Foods foods = ValidateLink("https://api.spoonacular.com/food/products/search?query=a&apiKey=3773caf1b91f4d93a3337e8477292ce4");
        System.out.println(foods);
        for (Object obj: foods.products){
            Gson gson = new Gson();
            String resFood = gson.toJson(obj);
            Food food = gson.fromJson(resFood, Food.class);
        }
    }
    public static Foods ValidateLink(String link) {
       // HttpRequest req = HttpRequest.newBuilder(URI.create(link)).GET().build();

        try {
            client = HttpClient.newHttpClient();
            HttpRequest req = HttpRequest.newBuilder(URI.create(link)).GET().build();
            HttpResponse<String> res = client.send(req,
                    HttpResponse.BodyHandlers.ofString());

            String apiRes = res.body();
            Gson gson = new Gson();
            return gson.fromJson(apiRes, Foods.class);

        } catch (IOException | InterruptedException e) {
            return new Foods();
        }
    }

    public static Foods ValidateLinkProto(String link) {
        // HttpRequest req = HttpRequest.newBuilder(URI.create(link)).GET().build();

        Gson gson = new Gson();

        return gson.fromJson(link, Foods.class);

    }

    public static Item singleProduct(int id) {
        // HttpRequest req = HttpRequest.newBuilder(URI.create(link)).GET().build();

        try {
            client = HttpClient.newHttpClient();
            HttpRequest req = HttpRequest.newBuilder(URI.create(String.format("https://api.spoonacular.com/food/products/%d?apiKey=3773caf1b91f4d93a3337e8477292ce4", id))).GET().build();
            HttpResponse<String> res = client.send(req,
                    HttpResponse.BodyHandlers.ofString());
            String apiRes = res.body();
            Gson gson = new Gson();

            return gson.fromJson(apiRes, Item.class);

        } catch (IOException | InterruptedException e) {
            return new Item();
        }
    }
    public static Item singleProductProto(int id) {
        // HttpRequest req = HttpRequest.newBuilder(URI.create(link)).GET().build();

        Gson gson = new Gson();
        return gson.fromJson(seedData.burgerIndex[id], Item.class);

    }
}