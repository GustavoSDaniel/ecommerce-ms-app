package org.gustavosdaniel.ecommer.handler;

import org.gustavosdaniel.ecommer.exception.CustomerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Quando uma CustomerNotFoundException é lançada em qualquer controller
    // Status: 404 Not Found
    // Corpo: A mensagem da exceçã
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handle(CustomerNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND) // Status HTTP 404
                .body(exception.getMessage());
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
