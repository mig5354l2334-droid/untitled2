package org.example;

public class ExcepcionEnvio extends RuntimeException {
    public ExcepcionEnvio(String message) {
        super(message);
    }
}
