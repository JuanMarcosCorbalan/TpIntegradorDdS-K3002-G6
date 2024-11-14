package org.example.Persona;

import java.util.ArrayList;
import java.util.List;

abstract public class Persona {
    Domicilio domicilio;
    List<Medio_contacto> medios_de_contacto = new ArrayList<>();

    public Persona(Domicilio domicilio , List<Medio_contacto> mediosContacto)
    {
            this.domicilio = domicilio;
            this.medios_de_contacto = mediosContacto;
    }

    public Persona() {
    }

    public void setMediosDeContacto(List<Medio_contacto> mediosContacto) {
        this.medios_de_contacto = mediosContacto;
    }
    public Domicilio getDomicilio() {
        return domicilio;
    }
    public List<Medio_contacto> getMediosContacto(){return medios_de_contacto;}

    //abstract void setNombre(String nombre);
    public void agregarMedioContacto(Medio_contacto medioContacto){
        medios_de_contacto.add(medioContacto);
    }

    @Override
    public boolean equals(Object o){
        return true;
    }

    @Override
    public int hashCode(){
        return 0;
    }
}

