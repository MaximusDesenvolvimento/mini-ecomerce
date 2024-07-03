package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Product {

    @Id
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

    public Product(){
    }
}
