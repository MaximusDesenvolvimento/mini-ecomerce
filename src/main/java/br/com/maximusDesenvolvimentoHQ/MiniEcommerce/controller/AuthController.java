package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.controller;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.User;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests.AuthPostRequest;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests.UserPostRequestBody;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
//@CrossOrigin
public class AuthController {


    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/signin")
    public ResponseEntity<?> signin(@RequestBody AuthPostRequest authPostRequest){
        return ResponseEntity.ok(authService.login(authPostRequest));
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<User> signup(@RequestBody UserPostRequestBody userPostRequestBody){
        return new ResponseEntity<>(authService.createUser(userPostRequestBody), HttpStatus.CREATED);
    }
}
