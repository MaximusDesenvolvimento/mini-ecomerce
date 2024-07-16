package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.repository;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String>  {

    Page<Product> findByName(String name, Pageable pageable);
    Product findByName(String name);
    Page<Product> findByCategory(String category, Pageable pageable);
}

