package org.example.Broker;

import org.example.Heladeras.Heladera;
import org.example.Validadores_Sensores.Alerta;

public class BrokerAlertas extends BrokerHeladera<Alerta> {

    @Override
    protected void procesarMensaje(Alerta alerta, Heladera heladera) {
        // Lógica para procesar una alerta
        heladera.procesar_Alerta(alerta);
        // Puedes procesar la alerta dependiendo del tipo (alta temperatura, movimiento, etc.)
    }
}