package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.controller;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.Cart;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests.CartPostRequestBody;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.service.CartService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class CartControll {

    CartService cartService;

    @Autowired
    public CartControll(CartService cartService){
        this.cartService = cartService;
    }

    @PostMapping(path = "cart/{id}")
    public ResponseEntity<Cart> creationCarrinho(@PathVariable String id, @RequestBody CartPostRequestBody cartPostRequestBody){
//        log.info("Produto no cart: "+cartPostRequestBody.getListItem().get(0).getProductId());
        return new ResponseEntity<>(cartService.criateCart(id, cartPostRequestBody),HttpStatus.CREATED);
    }
}
