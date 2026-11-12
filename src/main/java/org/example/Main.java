package org.example;

public class Main {
    interface Creador {
        Paquete crear() throws ExcepcionEnvio;
    }

    static void registrar(Despacho despacho, Creador creador) {
        try {
            Paquete p = creador.crear();
            despacho.agregar(p);
            System.out.println("OK: " + p + " registrado en " + despacho.getCodigo());
        } catch (ExcepcionEnvio e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    static void crearSuelto(Creador creador) {
        try {
            Paquete p = creador.crear();
            System.out.println("OK: " + p + " creado");
        } catch (ExcepcionEnvio e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    static Despacho nuevoDespacho(String mensajero) {
        try {
            return new Despacho(mensajero);
        } catch (ExcepcionEnvio e) {
            System.out.println("ERROR: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println("===== CASO 1 =====");
        Despacho andres = nuevoDespacho("Andres");
        registrar(andres, () -> new PaqueteEstandar("EY2001", 5, Zona.ORIENTE));
        registrar(andres, () -> new PaqueteExpress("EY2002", 1.5, Zona.NORTE));
        registrar(andres, () -> new PaqueteFragil("EY2003", 2, Zona.CENTRO, "vajilla"));
        registrar(andres, () -> new PaqueteEstandar("EY2004", 8, Zona.CENTRO));
        registrar(andres, () -> new PaqueteExpress("EY2005", 6, Zona.ORIENTE));
        registrar(andres, () -> new PaqueteFragil("EY2006", 3.5, Zona.SUR, "televisor"));
        System.out.println();
        System.out.println(andres.manifiesto());

        System.out.println("===== CASO 2 =====");
        crearSuelto(() -> new PaqueteEstandar("EY3001", -2, Zona.CENTRO));
        crearSuelto(() -> new PaqueteFragil("EY3002", 2, Zona.NORTE, ""));
        crearSuelto(() -> new PaqueteEstandar("EY12A4", 3, Zona.SUR));
        crearSuelto(() -> new PaqueteExpress("EY3003", 22, Zona.NORTE));
        System.out.println();

        System.out.println("===== CASO 3 =====");
        Despacho camila = nuevoDespacho("Camila");
        registrar(camila, () -> new PaqueteEstandar("EY4001", 12, Zona.CENTRO));
        registrar(camila, () -> new PaqueteExpress("EY4002", 10, Zona.SUR));
        registrar(camila, () -> new PaqueteFragil("EY4003", 9, Zona.NORTE, "espejo"));
        registrar(camila, () -> new PaqueteEstandar("EY4004", 8, Zona.ORIENTE));
        System.out.println();
        System.out.println(camila.manifiesto());

        System.out.println("===== CASO 4 =====");
        Despacho mateo = nuevoDespacho("Mateo");
        registrar(mateo, () -> new PaqueteFragil("EY5001", 3, Zona.SUR, "lampara"));
        registrar(mateo, () -> new PaqueteEstandar("EY5001", 1, Zona.CENTRO));
        registrar(mateo, () -> new PaqueteExpress("EY5002", 2, Zona.CENTRO));
        System.out.println();
        System.out.println(mateo.manifiesto());
    }
}
