package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.mapper;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.Product;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests.ProductPostRequestBody;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests.ProductPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {
    public static final ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    public abstract Product toProduct(ProductPostRequestBody productPostRequestBody);
    public abstract Product toProduct(ProductPutRequestBody productPutRequestBody);
}
