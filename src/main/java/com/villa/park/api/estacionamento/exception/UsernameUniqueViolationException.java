package com.villa.park.api.estacionamento.exception;

public class UsernameUniqueViolationException extends RuntimeException {

    public UsernameUniqueViolationException(String message){
        super(message);
    }
}
