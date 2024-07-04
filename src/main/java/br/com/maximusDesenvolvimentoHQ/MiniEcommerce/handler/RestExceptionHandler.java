package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.handler;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.exception.BadRequestException;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.exception.BadRequestExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> badRequestExceptionDetailsResponseEntity(BadRequestException badRequestException){
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception, check the documentation")
                        .details(badRequestException.getMessage())
                        .developerMessage(badRequestException.getClass().getName())
                        .build(),HttpStatus.BAD_REQUEST);
    }
}
