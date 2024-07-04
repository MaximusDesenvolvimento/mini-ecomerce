package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.repository;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ProductRepository extends MongoRepository<Product, String>  {

    Product findByName(String name);

}

