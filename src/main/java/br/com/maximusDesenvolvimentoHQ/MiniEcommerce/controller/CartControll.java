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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Objects;

@RestController
@Log4j2
public class CartControll {

    CartService cartService;

    @Autowired
    public CartControll(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping(path = "cart")
    public ResponseEntity<Cart> creationCart(@RequestBody @Valid CartPostRequestBody cartPostRequestBody) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        return new ResponseEntity<>(cartService.criateCart(userId, cartPostRequestBody), HttpStatus.CREATED);
    }

    @GetMapping(path = "cart")
    public ResponseEntity<Page<Cart>> listCartByUser(Pageable pageable){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        log.info("o nome da autenticação é: "+userId);
        return new ResponseEntity<>(cartService.findByUserId(userId,pageable),HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "carts")
    public ResponseEntity<Page<Cart>> carts(Pageable pageable){
        return new ResponseEntity<>(cartService.findAll(pageable),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "totalValue/{userId}")
    public ResponseEntity<String> getTotalValueByUserId(@PathVariable String userId){
        String totalValue = cartService.getTotalValueByUserId(userId);
        return new ResponseEntity<>(totalValue,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "search-month")
    public ResponseEntity<Page<Cart>> findAllByMonth(@RequestParam String orderDate,Pageable pageable){
        return new ResponseEntity<>(cartService.findAllByMonth(orderDate,pageable),HttpStatus.OK);
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
