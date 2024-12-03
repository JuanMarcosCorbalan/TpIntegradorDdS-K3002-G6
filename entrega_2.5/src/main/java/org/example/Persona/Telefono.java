package org.example.Persona;

import javax.persistence.*;

@Entity
public class Telefono extends Medio_contacto {

    public String numero;

    public Telefono() {}

    public Telefono (Persona persona, String numero_nuevo){
        this.persona = persona;
        this.numero = numero_nuevo;
    }

    @Override
    public void setDetalle(String numero){
        this.numero = numero;
    }

    public void notificar(Medio_contacto[] medios) {}
}
