package com.example.bottomsheetbehaviorexample.model;

import java.util.Date;

// Reference Homepage URL : https://github.com/writtmeyer/recyclerviewdemo

public class DemoModel {
    private static int nextId = 0;
    public String label;
    public Date dateTime;
    public String pathToImage;
    public int id = ++nextId;
}