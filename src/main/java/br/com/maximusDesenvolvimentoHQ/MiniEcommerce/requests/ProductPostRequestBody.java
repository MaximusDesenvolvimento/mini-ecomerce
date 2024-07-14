package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.validation.ValidBase64;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.AccessType;
import org.springframework.web.multipart.MultipartFile;


public class ProductPostRequestBody {

    @NotEmpty(message = "O campo name do produto não pode ser vazio")
    @NotBlank(message = "O campo name do produto não pode ser branco")
    @NotNull(message = "O campo name do produto não pode ser nulo")
    private String name;

    @NotNull(message = "O campo price do produto não pode ser nulo")
    @DecimalMin("0.0")
    private Float price;

    @DecimalMin("0.0")
    @NotNull(message = "O campo price do produto não pode ser nulo")
    private Float oldPrice;

    @NotEmpty(message = "O campo category do produto não pode ser vazio")
    @NotBlank(message = "O campo category do produto não pode ser branco")
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

    public ProductPostRequestBody(String name, float price, float oldPrice, String category, String image) {
        this.name = name;
        this.price = price;
        this.oldPrice = oldPrice;
        this.category = category;
        this.image = image;
    }
}
