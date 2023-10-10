package com.villa.park.api.estacionamento.web.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Getter
@ToString
public class ErroMessage {

    private String path;
    private String method;
    private int status;
    private String statusText;
    private String message;

    private Map<String,String>errors;

    public ErroMessage() {
    }

    public ErroMessage(HttpServletRequest request, HttpStatus status, String massage) {

        this.path = request.getRequestURI();
        this.method = request.getMethod();
        this.status=status.value();
        this.statusText=status.getReasonPhrase();
        this.message=massage;

    }

    public ErroMessage(HttpServletRequest request, HttpStatus status, String massage, BindingResult result) {

        this.path = request.getRequestURI();
        this.method = request.getMethod();
        this.status=status.value();
        this.statusText=status.getReasonPhrase();
        this.message=massage;
        addErrors(result);

    }

    private void addErrors(BindingResult result) {
        this.errors =new HashMap<>();
        for (FieldError fieldError: result.getFieldErrors()){
            this.errors.put(fieldError.getField() , fieldError.getDefaultMessage());
        }
    }
}
