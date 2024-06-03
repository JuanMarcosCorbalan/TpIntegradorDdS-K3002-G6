package org.example.Colaborador;

public class Persona {
    String direccion;
    Medio_contacto[] medios_de_contacto;

    public Persona(String direccion, Medio_contacto[] mediosContacto)
    {
            this.direccion = direccion;
            this.medios_de_contacto = mediosContacto;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public void setMediosDeContacto(Medio_contacto[] mediosContacto) {
        this.medios_de_contacto = mediosContacto;
    }
}

