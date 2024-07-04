package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.service;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.Product;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.exception.BadRequestException;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.mapper.ProductMapper;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.repository.ProductRepository;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests.ProductPostRequestBody;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests.ProductPutRequestBody;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Log4j2
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<Product> listAll() {
        return productRepository.findAll();
    }

    public Product findByName(String name){
        return productRepository.findByName(name);
    }

    public Product findByIdOrThrowBadRequestException(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Product not found"));
    }

    public Product save(ProductPostRequestBody productPostRequestBody) {
        Product product = productMapper.INSTANCE.toProduct(productPostRequestBody);
        log.info(productMapper.INSTANCE.toProduct(productPostRequestBody).getName());
        productRepository.save(product);
        return product;
    }

    public void delete(String id) {
        productRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(ProductPutRequestBody productPutRequestBody) {
        Product savedProduct = findByIdOrThrowBadRequestException(productPutRequestBody.getId());
        Product product = productMapper.INSTANCE.toProduct(productPutRequestBody);
        product.setId(savedProduct.getId());
        productRepository.save(product);
    }
}

