package org.example.Persona;

abstract public class Persona {
    Domicilio domicilio;
    Medio_contacto[] medios_de_contacto;

    public Persona(Domicilio domicilio , Medio_contacto[] mediosContacto)
    {
            this.domicilio = domicilio;
            this.medios_de_contacto = mediosContacto;
    }

    public Persona() {
    }

    public void setMediosDeContacto(Medio_contacto[] mediosContacto) {
        this.medios_de_contacto = mediosContacto;
    }
    public Domicilio getDomicilio() {
        return domicilio;
    }
    public Medio_contacto[] getMediosContacto(){return medios_de_contacto;}

    //abstract void setNombre(String nombre);

    @Override
    public boolean equals(Object o){
        return true;
    }

    @Override
    public int hashCode(){
        return 0;
    }
}

