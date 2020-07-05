package com.example.tourguide;

public class Item {

    private String itemName;
    private String itemDescription;
    private int itemImage;

    public Item(String name, String description, int imageRsourceId){
        this.itemName = name;
        this.itemDescription = description;
        this.itemImage = imageRsourceId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public int getItemImage() {
        return itemImage;
    }

}
