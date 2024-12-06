package org.example.Broker;

import org.example.Heladeras.EstadoHeladera;
import org.example.Heladeras.Heladera;
import org.example.Validadores_Sensores.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBroker {


    //PRUEBO CON UNA HELADERA, MANDARLE UNA UNICA TEMPERATURA
    @Test
    void comprobar_cambio_temperatura_heladera_unica()
    {
        Heladera heladera = new Heladera("a18j",5.0);
        BrokerTemperatura broker = new BrokerTemperatura();
        broker.registrarHeladera(heladera);

        broker.enviarMensaje(heladera,5.5);

        broker.procesarMensajes(heladera);

        Double temperaturaActualizada = heladera.getTemperaturaActual();

        assertEquals(5.5,temperaturaActualizada);

    }

    //PRUEBO MANDAR VARIAS TEMPERATURAS, DEBE QUEDAR CON LA ULTIMA
    @Test
    void comprobar_actualizacion_ultima_temperatura()
    {
        Heladera heladera = new Heladera("a18j",5.0);
        BrokerTemperatura broker = new BrokerTemperatura();
        broker.registrarHeladera(heladera);

        broker.enviarMensaje(heladera,5.5);
        broker.enviarMensaje(heladera,4.0);
        broker.enviarMensaje(heladera,3.5);

        broker.procesarMensajes(heladera);

        Double temperaturaActualizada = heladera.getTemperaturaActual();

        assertEquals(3.5,temperaturaActualizada);

    }

    //PROBAR PROCESAR COLA VACIA
    @Test
    void prueba_procesamiento_cola_vacia()
    {
        Heladera heladera = new Heladera("a18j",5.0);
        BrokerTemperatura broker = new BrokerTemperatura();
        broker.registrarHeladera(heladera);

        broker.procesarMensajes(heladera);

        Double temperaturaActualizada = heladera.getTemperaturaActual();

        assertEquals(5.0,temperaturaActualizada);

    }

    //PROBAR PROCESAR TEMPERATURA FUERA DE RANGOS ACEPTABLES
    //FALTA DEFINIR COMO MANEJAR ESTA SITUACION: SE PODRIA GENERAR UNA ALERTA EN NUESTRO DOMINIO, LA HELADERA
    //FISICA O NO DETECTO LA TEMPERATURA O ES UN MENSAJE ERRONEO
    @Test
    void prueba_temperatura_fuera_rango_positivo()
    {
        Heladera heladera = new Heladera("a18j",5.0);
        BrokerTemperatura broker = new BrokerTemperatura();
        broker.registrarHeladera(heladera);
        broker.enviarMensaje(heladera,500.5);

        broker.procesarMensajes(heladera);

        Double temperaturaActualizada = heladera.getTemperaturaActual();

        assertEquals(500.5,temperaturaActualizada);

    }

    @Test
    void prueba_temperatura_fuera_rango_negativo()
    {
        Heladera heladera = new Heladera("a18j",5.0);
        BrokerTemperatura broker = new BrokerTemperatura();
        broker.registrarHeladera(heladera);
        broker.enviarMensaje(heladera,-500.5);

        broker.procesarMensajes(heladera);

        Double temperaturaActualizada = heladera.getTemperaturaActual();

        assertEquals(-500.5,temperaturaActualizada);

    }

    //PRUEBO CON 2 HELADERAS Y EL MISMO BROKER MANDAR TEMPERATURA DISTINTAS Y PROBAR QUE PROCESE TODAS
    @Test
    void comprobar_cambio_temperatura_dos_heladeras()
    {
        Heladera heladera1 = new Heladera("a18j",5.0);
        Heladera heladera2 = new Heladera("a18b",7.0);
        BrokerTemperatura broker = new BrokerTemperatura();
        broker.registrarHeladera(heladera1);
        broker.registrarHeladera(heladera2);

        broker.enviarMensaje(heladera1,5.5);
        broker.enviarMensaje(heladera2,2.5);

        broker.procesarMensajes(heladera1);
        broker.procesarMensajes(heladera2);

        Double temperaturaActualizada1 = heladera1.getTemperaturaActual();
        Double temperaturaActualizada2 = heladera2.getTemperaturaActual();

        assertEquals(5.5,temperaturaActualizada1);
        assertEquals(2.5,temperaturaActualizada2);

    }

}