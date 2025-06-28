package org.gustavosdaniel.ecommerce.handler;

import jakarta.persistence.EntityNotFoundException;
import org.gustavosdaniel.ecommerce.exception.ProductPurchaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

import static sun.security.timestamp.TSResponse.BAD_REQUEST;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Quando uma CustomerNotFoundException é lançada em qualquer controller
    @ExceptionHandler(ProductPurchaseException.class)
    public ResponseEntity<String> handle(ProductPurchaseException exception) {
        return ResponseEntity
                .status(BAD_REQUEST) // Status HTTP 404
                .body(exception.getMessage()); // Corpo: A mensagem da exceção
    }


    // Quando uma CustomerNotFoundException é lançada em qualquer controller
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handle(EntityNotFoundException exception) {
        return ResponseEntity
                .status(BAD_REQUEST) // Status HTTP 404
                .body(exception.getMessage()); // Corpo: A mensagem da exceção
    }


    // Acionado quando falha a validação com @Valid, Coleta todos os erros de validação
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException exception) {

        var erros = new HashMap<String, String>();  // Mapa para erros de validação

        exception.getBindingResult().getAllErrors() // Obtém todos os erros
                .forEach((error) -> {
                    var fieldName = ((FieldError)error).getField(); // Nome do campo
                    var errorMessage = error.getDefaultMessage(); // Mensagem de erro
                    erros.put(fieldName,errorMessage );
                });
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(erros)); // Resposta estruturada
    }
}
