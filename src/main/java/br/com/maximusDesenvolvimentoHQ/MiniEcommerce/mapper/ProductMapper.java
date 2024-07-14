package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.mapper;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.Product;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests.ProductPostRequestBody;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests.ProductPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    Product toProduct(ProductPostRequestBody productPostRequestBody);
    Product toProduct(ProductPutRequestBody productPutRequestBody);
}
