package com.example.iitpfoodapp;

public class FoodItem {
    private String foodName;
    private String foodPrice;

    public FoodItem(String fname,String fprice)
    {
        foodName=fname;
        foodPrice=fprice;
    }

    public String getFoodName() {
        return foodName;
    }
    public String getFoodPrice()
    {
        return foodPrice;
    }

}
