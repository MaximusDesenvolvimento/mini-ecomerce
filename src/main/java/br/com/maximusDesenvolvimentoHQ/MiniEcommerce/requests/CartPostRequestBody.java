package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class CartPostRequestBody {

    @DecimalMin("0.0")
    private Float total;
    @DecimalMin("1.0")
    private int quantityProductCart;
    @NotEmpty(message = "Carrinho não pode ser nulo ou vazio")
    private List<CartItemPostRequestBody> listItem;

    public CartPostRequestBody(Float total, int quantityProductCart, List<CartItemPostRequestBody> listItem) {
        this.total = total;
        this.quantityProductCart = quantityProductCart;
        this.listItem = listItem;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public int getQuantityProductCart() {
        return quantityProductCart;
    }

    public void setQuantityProductCart(int quantityProductCart) {
        this.quantityProductCart = quantityProductCart;
    }

    public List<CartItemPostRequestBody> getListItem() {
        return listItem;
    }

    public void setListItem(List<CartItemPostRequestBody> listItem) {
        this.listItem = listItem;
    }
}
