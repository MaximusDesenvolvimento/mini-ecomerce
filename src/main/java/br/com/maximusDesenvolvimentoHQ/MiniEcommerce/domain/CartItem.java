package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain;

public class CartItem {

    private String id;
    private String productId;
    private Float productQuantity;
    private String name;
    private float price;
    private float oldPrice;
    private String category;
    private String urlImage;
    private String sha;
    private String dateCriation;

    public CartItem(String id, String productId, Float productQuantity, String name, float price, float oldPrice, String category, String urlImage, String sha, String dateCriation) {
        this.id = id;
        this.productId = productId;
        this.productQuantity = productQuantity;
        this.name = name;
        this.price = price;
        this.oldPrice = oldPrice;
        this.category = category;
        this.urlImage = urlImage;
        this.sha = sha;
        this.dateCriation = dateCriation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Float getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Float productQuantity) {
        this.productQuantity = productQuantity;
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

    public String getDateCriation() {
        return dateCriation;
    }

    public void setDateCriation(String dateCriation) {
        this.dateCriation = dateCriation;
    }
}
