package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests;

import lombok.Data;

@Data
public class ProductPutRequestBody {
    private String id;
    private String name;
    private float price;
    private float oldPrice;
    private String category;
}
