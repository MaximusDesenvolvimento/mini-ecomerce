package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.controller;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.User;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests.UserPostRequestBody;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
public class UserControll {

    UserService userService;

    public UserControll(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "user")
    public ResponseEntity<User> criateUser(@RequestBody UserPostRequestBody userPostRequestBody){
        return new ResponseEntity<>(userService.createUser(userPostRequestBody), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "users")
    public ResponseEntity<Page<User>> listAllUser(Pageable pageable){
        return new ResponseEntity<>(userService.listAllUser(pageable),HttpStatus.OK);
    }
}
