package org.example.Persona;

public class Whatsapp implements Medio_contacto {
    String numeroWhatsapp;

    public void notificar(Medio_contacto[] medios) {}

    public Whatsapp(String numeroWhatsapp) {
        this.numeroWhatsapp = numeroWhatsapp;
    }
}
