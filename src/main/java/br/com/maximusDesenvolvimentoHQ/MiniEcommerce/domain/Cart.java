package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain;

import java.util.List;

public class Carrinho {
    private String id;
    private String userId;
    private String dataPedido;
    private List<ItemCarrinho> listItem;

    public Carrinho(String id, String userId, String dataPedido, List<ItemCarrinho> listItem) {
        this.id = id;
        this.userId = userId;
        this.dataPedido = dataPedido;
        this.listItem = listItem;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<ItemCarrinho> getListItem() {
        return listItem;
    }

    public void setListItem(List<ItemCarrinho> listItem) {
        this.listItem = listItem;
    }
}
