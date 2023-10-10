package com.villa.park.api.estacionamento.web.exception;


import com.villa.park.api.estacionamento.exception.EntityNotFoundException;
import com.villa.park.api.estacionamento.exception.UsernameUniqueViolationException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {



    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErroMessage> entityNotFoundException(RuntimeException ex,HttpServletRequest request){

        log.error("Api Error - ",ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErroMessage(request,HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(UsernameUniqueViolationException.class)
    public ResponseEntity<ErroMessage> uniqueValidException(RuntimeException ex,HttpServletRequest request){

        log.error("Api Error - ",ex);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErroMessage(request,HttpStatus.CONFLICT, ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroMessage> MethodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                       HttpServletRequest request,
                                                                       BindingResult result){

        log.error("Api Error - ",ex);
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErroMessage(request,HttpStatus.UNPROCESSABLE_ENTITY,"Campo(s) invalido(s)",result));
    }

}
