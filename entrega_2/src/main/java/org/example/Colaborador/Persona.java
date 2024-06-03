package org.example.Colaborador;

abstract public class Persona {
    Domicilio domicilio;
    Medio_contacto[] medios_de_contacto;

    public Persona(Domicilio domicilio , Medio_contacto[] mediosContacto)
    {
            this.domicilio = domicilio;
            this.medios_de_contacto = mediosContacto;
    }


    public void setMediosDeContacto(Medio_contacto[] mediosContacto) {
        this.medios_de_contacto = mediosContacto;
    }
    public Domicilio getDomicilio() {
        return domicilio;
    }
    //abstract void setNombre(String nombre);
}

