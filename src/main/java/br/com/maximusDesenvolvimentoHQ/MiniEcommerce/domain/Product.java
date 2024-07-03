package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain;

import lombok.Data;

@Data
public class Product {

    private Long id;
    private String name;
    private float price;
    private float oldPrice;
    private String category;

    public Product (Long id, String name, float price, String category){
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }
}
