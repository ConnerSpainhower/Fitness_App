package com.example.testingapps;

public class user {
    private int id;
    private String username, password, calories;

    public user(int id, String username, String password, String calories) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.calories = calories;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCalories() {
        return calories;
    }
}

