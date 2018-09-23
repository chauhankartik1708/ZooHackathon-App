package com.example.kartik_chauhan.zoohackathon.Seller;

public class RecyclerItems {
    private String item;
    private String price;
    private String desc;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public RecyclerItems(String item, String price, String desc) {
        this.item = item;
        this.price = price;
        this.desc = desc;
    }
}
