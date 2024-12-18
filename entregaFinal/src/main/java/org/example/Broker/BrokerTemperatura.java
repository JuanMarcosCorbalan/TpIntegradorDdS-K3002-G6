package org.example.Broker;

import org.example.Heladeras.Heladera;

public class BrokerTemperatura extends BrokerHeladera<Double> {

    @Override
    protected void procesarMensaje(Double temperatura, Heladera heladera) {
        // Lógica para procesar un mensaje de temperatura
        heladera.procesar_temperatura(temperatura);
        // Aquí puedes añadir la lógica de control o notificación por temperatura alta/baja
    }
}
