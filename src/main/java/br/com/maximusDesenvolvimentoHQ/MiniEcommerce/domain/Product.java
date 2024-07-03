package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@Builder
public class Product {

    @Indexed
    private String id;
    private String name;
    private float price;
    private float oldPrice;
    private String category;


}
