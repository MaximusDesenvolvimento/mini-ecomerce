package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.repository;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends MongoRepository<Cart, String>,CustomCartRepository {

    List<Cart> findByuserId(String userId);
    Page<Cart> findByuserId(String userId, Pageable pageable);
}
