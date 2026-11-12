package org.example;

class PaqueteFragil extends Paquete implements ManejoEspecial {
    public static final double MANEJO_ESPECIAL = 5000;

    private final String contenido;

    public PaqueteFragil(String guia, double peso, Zona zona, String contenido) throws ExcepcionEnvio {
        super(guia, peso, zona);
        if (contenido == null || contenido.trim().isEmpty()) {
            throw new ContenidoRequeridoException(guia);
        }
        this.contenido = contenido.trim();
    }

    public String getContenido() {
        return contenido;
    }

    @Override
    public double calcularCosto() {
        return getCostoBase() + MANEJO_ESPECIAL;
    }

    @Override
    public String getServicio() {
        return "Fragil (" + contenido + ")";
    }

    @Override
    public String getAjuste() {
        return "+" + Formato.dinero(MANEJO_ESPECIAL);
    }

    @Override
    public String getAdvertencia() {
        return getGuia() + " · FRAGIL (" + contenido + ")";
    }
}
