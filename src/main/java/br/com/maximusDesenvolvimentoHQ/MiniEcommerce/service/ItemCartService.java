package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.service;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.Cart;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.Product;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests.CartItemPostRequestBody;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ItemCartService {

    ProductService productService;

    @Autowired
    public ItemCartService(ProductService productService) {
        this.productService = productService;
    }

    public Cart criationCart(String id, CartItemPostRequestBody cartItemPostRequestBody){
        Product product = productService.findByIdOrThrowBadRequestException(id);

//        log.info("service cart: "+cartItemPostRequestBody);
        return null;
    }
}
