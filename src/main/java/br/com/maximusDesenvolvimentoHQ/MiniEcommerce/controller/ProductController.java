package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.controller;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.client.GitHubClient;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.Product;
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

@RestController
@RequestMapping("products")
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
    @GetMapping
    public ResponseEntity<Page<Product>> list(Pageable pageable){
        log.info(dataUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(productService.listAll(pageable), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable String id){
        return new ResponseEntity<>(productService.findByIdOrThrowBadRequestException(id),HttpStatus.OK);
    }

    @GetMapping(path = "/find")
    public ResponseEntity<Product> findByIName(@RequestParam String name){
        return new ResponseEntity<>(productService.findByName(name),HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<Product> save(@RequestBody @Valid ProductPostRequestBody productPostRequestBody){
//        return new ResponseEntity<>(productService.save(productPostRequestBody),HttpStatus.CREATED);
//    }

    @PostMapping
    public ResponseEntity<Product> save(@ModelAttribute ProductPostRequestBody productPostRequestBody) throws IOException {
        return new ResponseEntity<>(productService.save(productPostRequestBody),HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) throws IOException {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Product> replace(@PathVariable String id, @ModelAttribute ProductPutRequestBody productPutRequestBody) throws IOException {
        productService.replace(id,productPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
