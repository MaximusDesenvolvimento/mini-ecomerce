package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.service;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.Product;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.exception.BadRequestException;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.mapper.ProductMapper;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.repository.ProductRepository;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests.ProductPostRequestBody;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests.ProductPutRequestBody;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public Page<Product> listAll(Pageable pageable) {
        return productRepository.findAll(pageable);
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

