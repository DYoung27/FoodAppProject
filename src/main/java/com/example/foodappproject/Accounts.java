package com.example.foodappproject;

import java.util.HashMap;

public class Accounts {
    public static HashMap<String,String> accounts = new HashMap<String, String>();
    public static void createAccount(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {

        }
        accounts.put(username, password);
        System.out.println(accounts);
    }

}
