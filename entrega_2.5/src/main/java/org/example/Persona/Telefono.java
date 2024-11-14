package org.example.Persona;

public class Telefono implements Medio_contacto {
    String numeroTelefono;
    public void notificar(Medio_contacto[] medios) {}

    public Telefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }
}
