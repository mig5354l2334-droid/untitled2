package org.example;


    class PaqueteExpress extends Paquete {
        public static final double RECARGO = 0.5;

        public PaqueteExpress(String guia, double peso, Zona zona) throws ExcepcionEnvio {
            super(guia, peso, zona);
        }

        @Override
        public boolean esExpress() {
            return true;
        }

        @Override
        public double calcularCosto() {
            return getCostoBase() * (1 + RECARGO);
        }

        @Override
        public String getServicio() {
            return "Express";
        }

        @Override
        public String getAjuste() {
            return "+" + (int) (RECARGO * 100) + "%";
        }
    }