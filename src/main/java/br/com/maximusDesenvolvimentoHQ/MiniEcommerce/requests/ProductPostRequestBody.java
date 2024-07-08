package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;


public class ProductPostRequestBody {

    @NotEmpty(message = "O produto não pode ser vazio")
    @NotBlank(message = "O produto não pode ser branco")
    private String name;

    @DecimalMin("0.0")
    private float price;

    @DecimalMin("0.0")
    private float oldPrice;
    private String category;

    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
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

    public void setPrice(float price)  {
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

    public ProductPostRequestBody(String name, float price, float oldPrice, String category, MultipartFile file) {
        this.name = name;
        this.price = price;
        this.oldPrice = oldPrice;
        this.category = category;
        this.file = file;
    }
}
