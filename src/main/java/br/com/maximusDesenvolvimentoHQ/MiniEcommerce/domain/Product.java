package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain;

public class Product {

    private int id;
    private String name;
    private float price;
    private float oldPrice;

    public Product (int id, String name, float price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Product(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(float oldPrice) {
        this.oldPrice = oldPrice;
    }
}
