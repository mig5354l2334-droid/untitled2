package org.example;

class Despacho {
    public static final double CAPACIDAD_MAXIMA = 30;
    private static int contador = 0;

    private final String codigo;
    private final String mensajero;
    private final List<Paquete> paquetes;
    private double pesoTotal;

    public Despacho(String mensajero) throws DatoInvalidoException {
        if (mensajero == null || mensajero.trim().isEmpty()) {
            throw new DatoInvalidoException("Despacho: el nombre del mensajero es obligatorio");
        }
        contador++;
        this.codigo = "D-" + contador;
        this.mensajero = mensajero.trim();
        this.paquetes = new ArrayList<>();
        this.pesoTotal = 0;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getMensajero() {
        return mensajero;
    }

    public double getPesoTotal() {
        return pesoTotal;
    }

    public List<Paquete> getPaquetes() {
        return Collections.unmodifiableList(paquetes);
    }

    public void agregar(Paquete paquete) throws ExcepcionEnvio {
        if (paquete == null) {
            throw new DatoInvalidoException("Despacho " + codigo + ": no se puede registrar un paquete nulo");
        }
        if (paquetes.contains(paquete)) {
            throw new PaqueteDuplicadoException(paquete.getGuia(), codigo);
        }
        if (pesoTotal + paquete.getPeso() > CAPACIDAD_MAXIMA) {
            throw new CapacidadExcedidaException(paquete.getGuia(), codigo, pesoTotal, paquete.getPeso(), CAPACIDAD_MAXIMA);
        }
        paquetes.add(paquete);
        pesoTotal += paquete.getPeso();
    }

    public List<Paquete> ordenados() {
        List<Paquete> copia = new ArrayList<>(paquetes);
        copia.sort(new ComparadorEntrega());
        return copia;
    }

    public double costoTotal() {
        double total = 0;
        for (Paquete p : paquetes) {
            total += p.calcularCosto();
        }
        return total;
    }

    public String manifiesto() {
        StringBuilder sb = new StringBuilder();
        sb.append("Despacho ").append(codigo).append(" – Mensajero: ").append(mensajero).append("\n");
        sb.append(String.format("%-8s %-24s %-8s %-9s %-11s %-9s %s%n",
                "Guia", "Servicio", "Zona", "Peso", "Costo base", "Ajuste", "Costo envio"));
        List<String> advertencias = new ArrayList<>();
        for (Paquete p : ordenados()) {
            sb.append(String.format("%-8s %-24s %-8s %-9s %-11s %-9s %s%n",
                    p.getGuia(),
                    p.getServicio(),
                    p.getZona().getNombre(),
                    Formato.peso(p.getPeso()) + " kg",
                    Formato.dinero(p.getCostoBase()),
                    p.getAjuste(),
                    Formato.dinero(p.calcularCosto())));
            if (p instanceof ManejoEspecial) {
                ManejoEspecial especial = (ManejoEspecial) p;
                advertencias.add(especial.getAdvertencia());
            }
        }
        sb.append("Peso total: ").append(Formato.peso(pesoTotal)).append(" kg · Costo total del despacho: $")
                .append(Formato.dinero(costoTotal())).append("\n");
        sb.append("Advertencias: ");
        if (advertencias.isEmpty()) {
            sb.append("ninguna");
        } else {
            sb.append(String.join(" | ", advertencias));
        }
        sb.append("\n");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Despacho)) {
            return false;
        }
        return codigo.equals(((Despacho) o).codigo);
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }

    @Override
    public String toString() {
        return "Despacho " + codigo + " (" + mensajero + ", " + paquetes.size() + " paquetes)";
    }
}