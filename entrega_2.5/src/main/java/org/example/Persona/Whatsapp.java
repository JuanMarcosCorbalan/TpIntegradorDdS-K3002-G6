package org.example.Persona;

import javax.persistence.*;

@Entity
public class Whatsapp extends Medio_contacto {

    public String numero;

    public Whatsapp (){

    }

    public Whatsapp (Persona persona, String numero_nuevo){
        this.persona = persona;
        this.numero = numero_nuevo;
    }

    @Override
    public void setDetalle(String numero){
        this.numero = numero;
    }

    public void notificar(Medio_contacto[] medios) {}
}
