package org.example.Broker;

import org.example.Heladeras.Heladera;

public class BrokerAutorizaciones extends BrokerHeladera<String> {

    @Override
    protected void procesarMensaje(String autorizacion, Heladera heladera) {
        // Lógica para procesar un mensaje de autorización
        System.out.println("Procesando autorización: " + autorizacion);
        // Aquí puedes procesar las autorizaciones (como acceso de usuarios)
    }
}
