package org.example.Formas_contribucion;

public enum ConstCalculo {
    PESOS_DONADOS(0.5),
    VIANDAS_DISTRIBUIDAS(1.0),
    VIANDAS_DONADAS(1.5),
    TARJETAS_REPARTIDAS(2.0);

    private final double valor;

    ConstCalculo(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }
}
