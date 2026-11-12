package org.example;
abstract class Paquete {
    public static final double PESO_MAXIMO_MOTO = 20;
    public static final double COSTO_POR_KILO = 1000;

    private final String guia;
    private final double peso;
    private final Zona zona;

    protected Paquete(String guia, double peso, Zona zona) throws ExcepcionEnvio {
        Validador.validarGuia(guia);
        Validador.validarPeso(guia, peso);
        Validador.validarZona(guia, zona);
        this.guia = guia;
        this.peso = peso;
        this.zona = zona;
    }

    public String getGuia() {
        return guia;
    }

    public double getPeso() {
        return peso;
    }

    public Zona getZona() {
        return zona;
    }

    public double getCostoBase() {
        return zona.getTarifa() + COSTO_POR_KILO * peso;
    }

    public boolean esExpress() {
        return false;
    }

    public abstract double calcularCosto();

    public abstract String getServicio();

    public abstract String getAjuste();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass().getSuperclass() != Paquete.class && !(o instanceof Paquete)) {
            return false;
        }
        if (!(o instanceof Paquete)) {
            return false;
        }
        Paquete otro = (Paquete) o;
        return guia.equals(otro.guia);
    }

    @Override
    public int hashCode() {
        return guia.hashCode();
    }

    @Override
    public String toString() {
        return guia + " " + getServicio() + " " + zona.getNombre() + " " + Formato.peso(peso) + " kg";
    }
}
