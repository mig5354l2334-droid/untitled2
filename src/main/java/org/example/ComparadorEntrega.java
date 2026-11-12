package org.example;

class ComparadorEntrega implements Comparator<Paquete> {
    @Override
    public int compare(Paquete a, Paquete b) {
        int porServicio = Boolean.compare(b.esExpress(), a.esExpress());
        if (porServicio != 0) {
            return porServicio;
        }
        int porZona = a.getZona().compareTo(b.getZona());
        if (porZona != 0) {
            return porZona;
        }
        return a.getGuia().compareTo(b.getGuia());
    }
}
