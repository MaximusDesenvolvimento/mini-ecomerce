package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.response;

public class ProductGetResponseBody {

    private String id;
    private String name;
    private float price;
    private float oldPrice;
    private String category;
    private String dateCriation;
    private byte[] base64ImageEconder;

    public ProductGetResponseBody(String id, String name, float price, float oldPrice, String category, String dateCriation, byte[] base64ImageEconder) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.oldPrice = oldPrice;
        this.category = category;
        this.dateCriation = dateCriation;
        this.base64ImageEconder = base64ImageEconder;
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

    public String getDateCriation() {
        return dateCriation;
    }

    public void setDateCriation(String dateCriation) {
        this.dateCriation = dateCriation;
    }

    public byte[] getBase64ImageEconder() {
        return base64ImageEconder;
    }

    public void setBase64ImageEconder(byte[] base64ImageEconder) {
        this.base64ImageEconder = base64ImageEconder;
    }
}
