package org.example.Broker;
import org.example.Heladeras.Heladera;
import org.example.Validadores_Sensores.Alerta;

import java.util.HashMap;
import java.util.Map;

public abstract class BrokerHeladera<T> {
    // Mapa que asocia heladeras con sus respectivas colas de mensajes
    protected Map<Heladera, ColaMensajes<T>> colas;

    public BrokerHeladera() {
        this.colas = new HashMap<>();
    }

    // Registrar una heladera y asignarle una cola de mensajes
    public void registrarHeladera(Heladera heladera) {
        colas.put(heladera, new ColaMensajes<>());
    }

    // Enviar un mensaje a la cola de una heladera
    public void enviarMensaje(Heladera heladera, T mensaje) {
        ColaMensajes<T> cola = colas.get(heladera);
        if (cola != null) {
            cola.agregar_mensaje(mensaje);
        } else {
            System.out.println("La heladera no está registrada.");
        }
    }

    // Procesar todos los mensajes de la heladera
    public void procesarMensajes(Heladera heladera) {
        ColaMensajes<T> cola = colas.get(heladera);
        if (cola != null) {
            while (!cola.estaVacia()) {
                T mensaje = cola.obtener_mensaje();
                procesarMensaje(mensaje,heladera);
            }
        } else {
            System.out.println("La heladera no está registrada.");
        }
    }

    // Método para procesar cada mensaje, será implementado por las subclases
    protected void procesarMensaje(T mensaje, Heladera heladera) {
        // Implementación específica en subclases
    }

}
