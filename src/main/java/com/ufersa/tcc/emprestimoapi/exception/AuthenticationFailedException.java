package com.ufersa.tcc.emprestimoapi.exception;

public class AuthenticationFailedException extends RuntimeException{
    public AuthenticationFailedException() {
        super("Autenticação falhou");
    }

    public AuthenticationFailedException(String message) {
        super(message);
    }

    public AuthenticationFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
