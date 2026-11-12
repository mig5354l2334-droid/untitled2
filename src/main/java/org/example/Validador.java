package org.example;

final class Validador {
    private Validador() {
    }

    public static void validarGuia(String guia) throws GuiaInvalidaException {
        if (guia == null || !guia.matches("EY\\d{4}")) {
            throw new GuiaInvalidaException(guia);
        }
    }

    public static void validarPeso(String guia, double peso) throws ExcepcionEnvio {
        if (Double.isNaN(peso) || peso <= 0) {
            throw new PesoInvalidoException(guia, peso);
        }
        if (peso > Paquete.PESO_MAXIMO_MOTO) {
            throw new PesoExcesivoException(guia, peso);
        }
    }

    public static void validarZona(String guia, Zona zona) throws ZonaInvalidaException {
        if (zona == null) {
            throw new ZonaInvalidaException(guia);
        }
    }
}
