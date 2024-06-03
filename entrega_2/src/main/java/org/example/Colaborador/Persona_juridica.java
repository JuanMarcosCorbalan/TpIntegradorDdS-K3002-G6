package org.example.Colaborador;

public class Persona_juridica extends Persona{

    String razon_social;
    Tipo_juridico tipo;
    String rubro;

    public Persona_juridica(String direccion, Medio_contacto[] mediosContacto, String razon_social, Tipo_juridico tipo, String rubro)
    {
        super(direccion,mediosContacto);
        this.razon_social = razon_social;
        this.tipo = tipo;
        this.rubro = rubro;
    }

}
