package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.controller;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.Carrinho;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarrinhoControll {

    @PostMapping(path = "carrinho")
    public ResponseEntity<Carrinho> creationCarrinho(@RequestBody ){

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
