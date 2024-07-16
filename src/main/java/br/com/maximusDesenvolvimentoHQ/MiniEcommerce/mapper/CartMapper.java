package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.mapper;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.Cart;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests.CartPostRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CartMapper {

    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);
    Cart toCart(CartPostRequestBody cartPostRequestBody);

}
