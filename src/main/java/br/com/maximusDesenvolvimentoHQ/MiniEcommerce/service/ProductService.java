package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.service;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.Product;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.repository.ProductRepository;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests.ProductPostRequestBody;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests.ProductPutRequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service

public class ProductService {

    private final ProductRepository productRepository;

    ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> listAll() {
        return productRepository.findAll();
    }

    public Product findByIdOrThrowBadRequestException(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found"));
    }


    public Product save(ProductPostRequestBody productPostRequestBody) {
        Product product = Product.builder().name(productPostRequestBody.getName())
                .price(productPostRequestBody.getPrice())
                .oldPrice(productPostRequestBody.getOldPrice())
                .category(productPostRequestBody.getCategory()).build();
        productRepository.save(product);
        return product;
    }

    public void delete(String id) {
        productRepository.delete(findByIdOrThrowBadRequestException(id));
    }


    public void replace(ProductPutRequestBody productPutRequestBody) {
        Product savedProduct = findByIdOrThrowBadRequestException(productPutRequestBody.getId());
        Product product = Product.builder()
                .id(savedProduct.getId())
                .name(productPutRequestBody.getName())
                .price(productPutRequestBody.getPrice())
                .oldPrice(productPutRequestBody.getOldPrice())
                .category(productPutRequestBody.getCategory()).build();
        productRepository.save(product);
    }
}

