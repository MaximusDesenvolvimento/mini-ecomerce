package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.service;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.Product;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.exception.BadRequestException;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.exception.ImageNotFoundException;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.mapper.ProductMapper;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.repository.ProductRepository;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests.ProductPostRequestBody;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests.ProductPutRequestBody;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.response.GitHubFileResponse;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.response.ProductGetResponseBody;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.util.DataUtil;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.util.ImageCompressor;
import lombok.extern.log4j.Log4j2;
import org.bson.codecs.jsr310.LocalDateTimeCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

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

    public Page<Product> findByName(String name,Pageable pageable) {
        return productRepository.findByName(name,pageable);
    }

    public Product findByName(String name) {
        return productRepository.findByName(name);

    }

    public Page<Product> findByCategory(String category, Pageable pageable){
        return productRepository.findByCategory(category, pageable);
    }

    public Product findByIdOrThrowBadRequestException(String id) throws IOException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Produto não encontrado."));

        return product;
    }

    public ProductGetResponseBody findByIdOrThrowBadRequestExceptionImage(String id) throws IOException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Produto não encontrado."));
        ProductGetResponseBody productGetResponseBody;
        byte[] base64Image = gitHubService.downloadImage(product.getId());
        productGetResponseBody = new ProductGetResponseBody(product.getId(),product.getName(),product.getPrice(),product.getOldPrice(),product.getCategory(),product.getDateCriation(),base64Image);
        return productGetResponseBody;
    }

    public Product save(ProductPostRequestBody productPostRequestBody) throws IOException {
        Optional<Product> productOptional = Optional.ofNullable(findByName(productPostRequestBody.getName()));
        productPostRequestBody.setImage(ImageCompressor.compressAndResizeImage(productPostRequestBody.getImage(),0.5,0.75));
        if (productOptional.isEmpty()){
            Product product = productMapper.INSTANCE.toProduct(productPostRequestBody);
            product.setDateCriation(DataUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
            log.info("Data de criação do produto: "+product.getDateCriation());
            Product savedProduct = productRepository.save(product);

            ResponseEntity<GitHubFileResponse> gitHubFileResponseResponseEntity = gitHubService.uploadImage(savedProduct.getId(), productPostRequestBody.getImage());

            product.setUrlImage(gitHubFileResponseResponseEntity.getBody().getContent().getHtmlUrl());
            product.setSha(gitHubFileResponseResponseEntity.getBody().getContent().getSha());
            replaceShaUrlImage(product);

            return product;
        }else {
            throw new BadRequestException("Produto "+productPostRequestBody.getName()+" já existe.");
        }
    }

    public void delete(String id) throws IOException {
        Product deletedProduct = findByIdOrThrowBadRequestException(id);
        boolean deletedImage = true;
        try {
            gitHubService.deleteImage(id,deletedProduct.getSha());

        }catch (HttpClientErrorException e){
            if (e.getStatusCode().value() == 422){
                deletedImage = false;
            } else if (e.getStatusCode().value() == 404) {
                deletedImage = false;
            } else{
                throw e;
            }
        }
        productRepository.delete(deletedProduct);
        if (!deletedImage){
            throw new ImageNotFoundException("Produto deletado, mas não há imagem na base de dados para o produto com ID: " + id);
        }
    }

    public void replace(String id, ProductPutRequestBody productPutRequestBody) throws IOException {
        ResponseEntity<GitHubFileResponse> gitHubFileResponseResponseEntity = null;
        Product savedProduct = findByIdOrThrowBadRequestException(id);
        log.info("Erro no replace service");
        boolean replacedImage = false;

        // mapemaneto dos valores não nulos para savedProduct.
        updateNonNullAndNonBlankFields(productPutRequestBody,savedProduct);
        if (Objects.nonNull(productPutRequestBody.getImage())){
            try {
                    gitHubFileResponseResponseEntity =
                            gitHubService.replaceImage(savedProduct.getId(), savedProduct.getSha(),
                                    productPutRequestBody.getImage());
                replacedImage = true;
                }catch (HttpClientErrorException e){


            }
        }

        if (replacedImage) {
            savedProduct.setUrlImage(Objects.requireNonNull(gitHubFileResponseResponseEntity.getBody().getContent().getHtmlUrl()));
            savedProduct.setSha(Objects.requireNonNull(gitHubFileResponseResponseEntity.getBody().getContent().getSha()));
        }
        productRepository.save(savedProduct);
    }

    public void replaceShaUrlImage(Product product) throws IOException {
        Product savedProduct = findByIdOrThrowBadRequestException(product.getId());
        product.setId(savedProduct.getId());
        productRepository.save(product);
    }

    public void updateNonNullAndNonBlankFields(ProductPutRequestBody productPutRequestBody, Product product){

        if (productPutRequestBody.getName() != null){
            product.setName(productPutRequestBody.getName());
        }
        if (productPutRequestBody.getPrice() != null) {
            product.setPrice(productPutRequestBody.getPrice());
        }
        if (productPutRequestBody.getOldPrice() != null) {
            product.setOldPrice(productPutRequestBody.getOldPrice());
        }
        if (productPutRequestBody.getCategory() != null) {
            product.setCategory(productPutRequestBody.getCategory());
        }
}
}

