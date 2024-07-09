package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Builder
public class Product {

    @Indexed
    private String id;
    private String name;
    private float price;
    private float oldPrice;
    private String category;
    private String urlImage;
    private String sha;

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

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public Product() {
    }

    public Product(String id, String name, float price, float oldPrice, String category, String sha, String urlImage) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.oldPrice = oldPrice;
        this.category = category;
        this.sha = getSha();
        this.urlImage = urlImage;
    }
}
