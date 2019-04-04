package com.example.iitpfoodapp;

import java.util.ArrayList;

public class finalFoodList {

    ArrayList<foodList> foodListArrayList;

    finalFoodList() {
        foodListArrayList = new ArrayList<>();
    }

    finalFoodList(ArrayList<foodList> foodListArrayList) {
        this.foodListArrayList = foodListArrayList;
    }
    ArrayList<foodList> getFoodListArrayList() {
        return foodListArrayList;
    }

}
