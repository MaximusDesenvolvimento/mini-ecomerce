package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.service;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.Product;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service

public class ProductService {

    private final ProductRepository productRepository;
    ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> listAll(){
        return productRepository.findAll();
    }

    public Product findById(long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Product not found"));
    }

    /**
    public Product save(Product product) {
        product.setId(ThreadLocalRandom.current().nextLong(3,1000000));
        products.add(product);
        return product;
    }

    public void delete(long id) {
        products.remove(findById(id));
    }


    public void replace(Product product) {
        delete(product.getId());
        products.add(product);
    }
     */
}

