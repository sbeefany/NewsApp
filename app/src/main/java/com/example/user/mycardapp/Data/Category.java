package com.example.user.mycardapp.Data;

public class Category {

    private final int id;
    private final String name;

    Category (int id , String name) {
        this.id = id;
        this.name = name;
    }

    public int getId () {
        return id;
    }

    public String getName () {
        return name;
    }

}
