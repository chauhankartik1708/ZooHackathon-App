package com.example.kartik_chauhan.zoohackathon.Seller;

public class RecyclerItems {
    private String item;
    private String price;


    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public RecyclerItems(String item, String price) {
        this.item = item;
        this.price = price;

    }
}
