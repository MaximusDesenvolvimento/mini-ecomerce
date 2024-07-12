package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.controller;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.client.GitHubClient;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.Product;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.exception.BadRequestException;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.exception.ImageNotFoundException;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests.ProductPostRequestBody;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests.ProductPutRequestBody;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.service.ProductService;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.util.DataUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

@RestController
@Log4j2
public class ProductController {

    private final DataUtil dataUtil;

    private final ProductService productService;

    private final GitHubClient githubClient;

    public ProductController(DataUtil dataUtil, ProductService productService, GitHubClient githubClient){
        this.dataUtil = dataUtil;
        this.productService = productService;
        this.githubClient = githubClient;
    }

    @GetMapping("products")
    public ResponseEntity<Page<Product>> list(Pageable pageable){
        log.info(dataUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(productService.listAll(pageable), HttpStatus.OK);
    }

    @GetMapping(path = "products/{id}")
    public ResponseEntity<Product> findById(@PathVariable String id){
        return new ResponseEntity<>(productService.findByIdOrThrowBadRequestException(id),HttpStatus.OK);
    }

    @GetMapping(path = "products/search")
    public ResponseEntity<Page<Product>> findByIName(@RequestParam(required = false) String q,
                                               @RequestParam(required = false) String category,
                                                     Pageable pageable){
        if (Objects.nonNull(q)){
            return new ResponseEntity<>(productService.findByName(q,pageable),HttpStatus.OK);
        } else if (Objects.nonNull(category)) {
            return new ResponseEntity<>(productService.findByCategory(category,pageable),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @PostMapping
//    public ResponseEntity<Product> save(@RequestBody @Valid ProductPostRequestBody productPostRequestBody){
//        return new ResponseEntity<>(productService.save(productPostRequestBody),HttpStatus.CREATED);
//    }

    @PostMapping(path = "product")
    public ResponseEntity<Product> save(@ModelAttribute ProductPostRequestBody productPostRequestBody) throws IOException {
        return new ResponseEntity<>(productService.save(productPostRequestBody),HttpStatus.CREATED);
    }

    @DeleteMapping(path = "product/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) throws IOException {
        try {
            productService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (ImageNotFoundException e){
            // Produto deletado, mas não há imagem na base de dados para o produto com ID:
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (BadRequestException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "product/{id}")
    public ResponseEntity<Product> replace(@PathVariable String id, @ModelAttribute ProductPutRequestBody productPutRequestBody) throws IOException {
        productService.replace(id,productPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
