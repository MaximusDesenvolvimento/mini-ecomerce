package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.controller;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.Cart;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests.CartPostRequestBody;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.service.CartService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@Log4j2
public class CartControll {

    CartService cartService;

    @Autowired
    public CartControll(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping(path = "cart/{userId}")
    public ResponseEntity<Cart> creationCart(@PathVariable(value = "userId") String userId,
                                             @RequestBody @Valid CartPostRequestBody cartPostRequestBody) {
        return new ResponseEntity<>(cartService.criateCart(userId, cartPostRequestBody), HttpStatus.CREATED);
    }

    @GetMapping(path = "carts")
    public ResponseEntity<Page<Cart>> carts(Pageable pageable){
        return new ResponseEntity<>(cartService.findAll(pageable),HttpStatus.OK);
    }

    @GetMapping(path = "cart/search")
    public ResponseEntity<Page<Cart>> cartsByUserByDate(@RequestParam(required = false) String userId,
                                                        @RequestParam(required = false) String orderDate,
                                                        Pageable pageable) {

        if (Objects.nonNull(userId) && Objects.nonNull(orderDate)) {
            return new ResponseEntity<>(cartService.findByUserByOrderDate(userId, orderDate, pageable), HttpStatus.OK);
        }
        if (Objects.nonNull(userId)) {
            return new ResponseEntity<>(cartService.findByUserId(userId, pageable), HttpStatus.OK);
        }
        if (Objects.nonNull(orderDate)) {
            return new ResponseEntity<>(cartService.findByOrderDate(orderDate, pageable), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
