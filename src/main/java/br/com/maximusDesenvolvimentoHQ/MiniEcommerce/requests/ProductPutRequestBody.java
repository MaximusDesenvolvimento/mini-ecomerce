package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests;

import org.springframework.web.multipart.MultipartFile;

public class ProductPutRequestBody {
    private String id;
    private String name;
    private float price;
    private float oldPrice;
    private String category;
    private MultipartFile image;

    public org.springframework.web.multipart.MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ProductPutRequestBody() {
    }

    public ProductPutRequestBody(String id, String name, float price, float oldPrice, String category, MultipartFile image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.oldPrice = oldPrice;
        this.category = category;
        this.image = image;
    }
}
