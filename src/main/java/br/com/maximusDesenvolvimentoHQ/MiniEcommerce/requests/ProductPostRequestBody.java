package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests;

import lombok.Data;

@Data
public class ProductPostRequestBody {
    private String name;
    private float price;
    private float oldPrice;
    private String category;
}
