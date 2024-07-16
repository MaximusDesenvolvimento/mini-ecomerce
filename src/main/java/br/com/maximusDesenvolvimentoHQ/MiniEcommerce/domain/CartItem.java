package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain;

public class ItemCarrinho {
    private String id;
    private String productId;
    private Float quantity;
    private Float subTotal;
    private String carrinhoId;

    public ItemCarrinho(String id, String product, Float quantity, Float subTotal, String carrinho) {
        this.id = id;
        this.productId = product;
        this.quantity = quantity;
        this.subTotal = subTotal;
        this.carrinhoId = carrinho;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct() {
        return productId;
    }

    public void setProduct(String product) {
        this.productId = product;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public Float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Float subTotal) {
        this.subTotal = subTotal;
    }

    public String getCarrinho() {
        return carrinhoId;
    }

    public void setCarrinho(String carrinho) {
        this.carrinhoId = carrinho;
    }
}
