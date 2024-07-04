package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.controller;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.Product;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests.ProductPostRequestBody;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests.ProductPutRequestBody;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.service.ProductService;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.util.DataUtil;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("products")
@Log4j2
public class ProductController {

    private final DataUtil dataUtil;

    private final ProductService productService;

    public ProductController(DataUtil dataUtil,ProductService productService){
        this.dataUtil = dataUtil;
        this.productService = productService;
    }
    @GetMapping
    public ResponseEntity<List<Product>> list(){
        log.info(dataUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(productService.listAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable String id){
        return new ResponseEntity<>(productService.findByIdOrThrowBadRequestException(id),HttpStatus.OK);
    }

    @GetMapping(path = "/find")
    public ResponseEntity<Product> findByIName(@RequestParam String name){
        return new ResponseEntity<>(productService.findByName(name),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody @Valid ProductPostRequestBody productPostRequestBody){
        return new ResponseEntity<>(productService.save(productPostRequestBody),HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Product> replace(@RequestBody ProductPutRequestBody productPutRequestBody) {
        productService.replace(productPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
