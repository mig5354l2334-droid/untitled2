package org.example;

public class PesoInvalidoException extends RuntimeException {
    public PesoInvalidoException(String message) {
        super(message);
    }
}
