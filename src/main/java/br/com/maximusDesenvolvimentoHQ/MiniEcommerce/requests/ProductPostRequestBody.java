package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests;

public class ProductPostRequestBody {
    private String name;
    private float price;
    private float oldPrice;
    private String category;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ProductPostRequestBody() {
    }

    public ProductPostRequestBody(String name, float price, float oldPrice, String category) {
        this.name = name;
        this.price = price;
        this.oldPrice = oldPrice;
        this.category = category;
    }
}
