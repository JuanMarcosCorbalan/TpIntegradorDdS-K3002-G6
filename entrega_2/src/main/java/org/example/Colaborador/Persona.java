package org.example.Colaborador;

public class Persona {
    String direccion;
    Medio_contacto[] medios_de_contacto;

    public Persona(String direccion, Medio_contacto[] mediosContacto)
    {
            this.direccion = direccion;
            this.medios_de_contacto = mediosContacto;
    }
}

