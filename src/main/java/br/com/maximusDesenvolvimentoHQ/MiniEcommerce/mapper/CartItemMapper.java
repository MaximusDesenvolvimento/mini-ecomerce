package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.mapper;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.CartItem;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.Product;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests.CartItemPostRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

    CartItemMapper INSTANCE = Mappers.getMapper(CartItemMapper.class);
    CartItem toCartItem(CartItemPostRequestBody cartItemPostRequestBody);
    List<CartItem> toListCartItem(List<CartItemPostRequestBody> listCartItemPostRequestBody);

    @Mapping(source = "id",target = "productId")
    CartItem toCartItemFromProduct(Product product);
}
