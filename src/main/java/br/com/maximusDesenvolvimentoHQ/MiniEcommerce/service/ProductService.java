package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.service;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.Product;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.exception.BadRequestException;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.mapper.ProductMapper;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.repository.ProductRepository;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests.ProductPostRequestBody;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests.ProductPutRequestBody;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.response.GitHubFileResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Log4j2
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final GitHubService gitHubService;

    @Autowired
    ProductService(ProductRepository productRepository, ProductMapper productMapper, GitHubService gitHubService) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.gitHubService = gitHubService;
    }

    public Page<Product> listAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product findByName(String name) {
        return productRepository.findByName(name);
    }

    public Product findByIdOrThrowBadRequestException(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Product not found"));
    }


    public Product save(ProductPostRequestBody productPostRequestBody) throws IOException {
        Product product = productMapper.INSTANCE.toProduct(productPostRequestBody);
//        log.info(productMapper.INSTANCE.toProduct(productPostRequestBody).getName());
        Product savedProduct = productRepository.save(product);
        ResponseEntity<GitHubFileResponse> gitHubFileResponseResponseEntity = gitHubService.uploadImage(savedProduct.getId(), productPostRequestBody.getImage().getBytes());
        product.setUrlImage(gitHubFileResponseResponseEntity.getBody().getContent().getHtmlUrl());
        product.setSha(gitHubFileResponseResponseEntity.getBody().getContent().getSha());
        replaceShaUrlImage(product);
        return product;
    }

    public void delete(String id) throws IOException {
        Product deletedProduct = findByIdOrThrowBadRequestException(id);
        ResponseEntity<String> response = gitHubService.deleteImage(id,deletedProduct.getSha());
        productRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(ProductPutRequestBody productPutRequestBody) throws IOException {
        Product savedProduct = findByIdOrThrowBadRequestException(productPutRequestBody.getId());
        log.info("sha do produto salvo: "+savedProduct.getSha());
        log.info("url do produto salvo: "+savedProduct.getUrlImage());
        Product product = productMapper.INSTANCE.toProduct(productPutRequestBody);
        product.setId(savedProduct.getId());
        product.setSha(savedProduct.getSha());
        product.setUrlImage(savedProduct.getUrlImage());
        ResponseEntity<GitHubFileResponse> gitHubFileResponseResponseEntity = gitHubService.replaceImage(savedProduct.getId(), savedProduct.getSha(),productPutRequestBody.getImage().getBytes());
        product.setUrlImage(gitHubFileResponseResponseEntity.getBody().getContent().getHtmlUrl());
        product.setSha(gitHubFileResponseResponseEntity.getBody().getContent().getSha());
        replaceShaUrlImage(product);
        productRepository.save(product);
    }

    public void replaceShaUrlImage(Product product) {
        Product savedProduct = findByIdOrThrowBadRequestException(product.getId());
        product.setId(savedProduct.getId());
        productRepository.save(product);
    }
}

