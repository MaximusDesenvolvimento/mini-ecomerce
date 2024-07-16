package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests;


import java.util.List;

public class CartPostRequestBody {

    private Float total;
    private int quantityProductCart;
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
