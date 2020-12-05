package com.example.toronto.mystudyapp.view;

public class Recycler_item {
    int image;
    String title;

    int getImage(){
        return this.image;
    }
    String getTitle(){
        return this.title;
    }

    Recycler_item(int image, String title){
        this.image=image;
        this.title=title;
    }
}
