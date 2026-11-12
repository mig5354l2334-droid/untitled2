package org.example;

class PaqueteEstandar extends Paquete {
    public PaqueteEstandar(String guia, double peso, Zona zona) throws ExcepcionEnvio {
        super(guia, peso, zona);
    }

    @Override
    public double calcularCosto() {
        return getCostoBase();
    }

    @Override
    public String getServicio() {
        return "Estandar";
    }

    @Override
    public String getAjuste() {
        return "—";
    }
}
