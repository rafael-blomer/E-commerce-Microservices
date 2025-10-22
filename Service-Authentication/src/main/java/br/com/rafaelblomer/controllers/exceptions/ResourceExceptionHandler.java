package br.com.rafaelblomer.controllers.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

    /*    @ExceptionHandler(ObjetoNaoEncontradoException.class)
    public ResponseEntity<StandardError> notFoundException(ObjetoNaoEncontradoException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Objeto não encontrado.", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }*/

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> pegarValidationExceptions(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String mensagens = e.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .reduce((msg1, msg2) -> msg1 + "; " + msg2)
                .orElse("Erro de validação");
        StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Erro de validação nos campos.", mensagens, request.getRequestURI()
        );
        return ResponseEntity.status(status).body(err);
    }
}
