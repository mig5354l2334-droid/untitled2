package org.example;

public class DatoInvalidoExcepcion extends RuntimeException {
    public DatoInvalidoExcepcion(String message) {
        super(message);
    }
}
