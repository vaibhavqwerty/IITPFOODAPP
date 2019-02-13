package com.example.iitpfoodapp;

public class FoodItem {
    private String foodName;
    private String foodPrice;
    private int price;

    public FoodItem(String fname,String fprice,int Price)
    {
        foodName=fname;
        foodPrice=fprice;
        price=Price;
    }

    public String getFoodName() {
        return foodName;
    }
    public String getFoodPrice()
    {
        return foodPrice;
    }
    public int getPrice(){return price;}
}
