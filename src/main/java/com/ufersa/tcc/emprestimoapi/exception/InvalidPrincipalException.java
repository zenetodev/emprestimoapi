package com.ufersa.tcc.emprestimoapi.exception;

public class InvalidPrincipalException extends RuntimeException{
    public InvalidPrincipalException() {
        super("Principal inválido.");
    }

    public InvalidPrincipalException(String message) {
        super(message);
    }

    public InvalidPrincipalException(String message, Throwable cause) {
        super(message, cause);
    }
}
