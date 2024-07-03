package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.service;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    //private final ProductRepository productRepository;
    public List<Product> listAll(){
        return List.of(new Product(2L,"iphone",5000,"tecnologia"));
    }
}

