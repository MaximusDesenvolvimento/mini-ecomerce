package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.mapper;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.Adress;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests.AdressPostRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AdressMapper {
    AdressMapper INSTANCE= Mappers.getMapper(AdressMapper.class);
    Adress toAdress(AdressPostRequestBody adressPostRequestBody);
}
