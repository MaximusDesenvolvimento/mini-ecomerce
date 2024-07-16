package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.service;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.Cart;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.CartItem;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.Product;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.mapper.CartItemMapper;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.mapper.CartMapper;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.mapper.ProductMapper;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests.CartItemPostRequestBody;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests.CartPostRequestBody;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.util.DataUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class CartService {

    ProductService productService;

    @Autowired
    public CartService(ProductService productService) {
        this.productService = productService;
    }

    public Cart criateCart(String id, CartPostRequestBody cartPostRequestBody){
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

//        Cart cart = CartMapper.INSTANCE.toCart(cartPostRequestBody);
        return null;
    }
}
