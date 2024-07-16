package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.repository;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<Cart, String> {
}
