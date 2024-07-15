package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.validation.ValidBase64;

public class ProductPutRequestBody {
    private String name;
    private Float price;
    private Float oldPrice;
    private String category;
    @ValidBase64
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Float oldPrice) {
        this.oldPrice = oldPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ProductPutRequestBody() {
    }

    public ProductPutRequestBody(String name, float price, float oldPrice, String category, String image) {
        this.name = name;
        this.price = price;
        this.oldPrice = oldPrice;
        this.category = category;
        this.image = image;
    }
}
