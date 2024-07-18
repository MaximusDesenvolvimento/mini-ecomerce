package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.service;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.Cart;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.CartItem;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.Product;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.mapper.CartItemMapper;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.mapper.CartMapper;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.repository.CartRepository;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.repository.CustomCartRepositoryImpl;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests.CartPostRequestBody;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.util.DataUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class CartService {

    ProductService productService;
    CartRepository cartRepository;


    @Autowired
    public CartService(ProductService productService, CartRepository cartRepository,CustomCartRepositoryImpl customCartRepository) {
        this.productService = productService;
        this.cartRepository = cartRepository;
    }

    public Cart criateCart(String id, CartPostRequestBody cartPostRequestBody) throws IOException {
        List<CartItem> listItem = new ArrayList<>();
        Cart cart = CartMapper.INSTANCE.toCart(cartPostRequestBody);
        cart.setUserId(id);
        cart.setOrderDate(DataUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));

        for(CartItem cartItem : cart.getListItem()){

            Product product = productService.findByIdOrThrowBadRequestException(cartItem.getProductId());
            CartItem item = CartItemMapper.INSTANCE.toCartItemFromProduct(product);
            //item.setDateCriation(DataUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
            item.setProductQuantity(cartItem.getProductQuantity());
            listItem.add(item);
        }

        cart.setListItem(listItem);
        log.info("Carrinho");
        for (CartItem cartItem: cart.getListItem()){
            log.info("nome do iten: "+cartItem.getName());

        }
            log.info("Total: "+cart.getTotal());
            log.info("Data do pedido: "+cart.getOrderDate());
            log.info("Id do usuario: "+cart.getUserId());

            cartRepository.save(cart);

//        Cart cart = CartMapper.INSTANCE.toCart(cartPostRequestBody);
        return null;
    }

    public Page<Cart> findAll(Pageable pageable){
        return cartRepository.findAll(pageable);
    }

    public Page<Cart> findByUserByOrderDate(String userId, String orderDate, Pageable pageable) {
        log.info("ignorand time");
        return cartRepository.findByOrderDateAndUserIdIgnoringTime(orderDate, userId, pageable);
    }

    public Page<Cart> findByOrderDate(String orderDate, Pageable pageable) {
        return cartRepository.findByOrderDateIgnoringTime(orderDate,pageable);
    }
    public Page<Cart> findByUserId(String userId, Pageable pageable) {

        return cartRepository.findByuserId(userId,pageable);
    }
}
