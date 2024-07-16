package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ItemCartPostRequestBody {

    @NotNull(message = "productId não pode ser nulo")
    @NotBlank(message = "productId não pode ser branco")
    @NotEmpty(message = "productId não pode ser vazio")
    String productId;

    @NotNull(message = "productQuantity não pode ser nulo")
    @NotBlank(message = "productQuantity não pode ser branco")
    @NotEmpty(message = "productQuantity não pode ser vazio")
    Float productQuantity;

    public ItemCartPostRequestBody(String productId, Float productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
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
}
