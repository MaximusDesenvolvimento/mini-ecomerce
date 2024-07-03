package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.service;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.Product;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService {

    List<Product> products = List.of(new Product(2L,"iphone",5000,"tecnologia"),new Product(1L,"sansung",5000,"tecnologia"));

    //private final ProductRepository productRepository;
    public List<Product> listAll(){
        return products;
    }

    public Product findById(long id){
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Product not found"));
    }
}

