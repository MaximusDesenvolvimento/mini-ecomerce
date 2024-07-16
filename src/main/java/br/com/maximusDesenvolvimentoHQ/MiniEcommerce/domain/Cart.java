package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Cart {

    @Indexed
    private String id;
    private String userId;
    private String orderDate;
    private Float total;
    private List<CartItem> listItem;

    public Cart(String id, String userId, String orderDate, Float total, List<CartItem> listItem) {
        this.id = id;
        this.userId = userId;
        this.orderDate = orderDate;
        this.total = total;
        this.listItem = listItem;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public List<CartItem> getListItem() {
        return listItem;
    }

    public void setListItem(List<CartItem> listItem) {
        this.listItem = listItem;
    }
}
