package org.example.Persona;

import org.example.Suscripcion.MensajeAviso;

import javax.persistence.*;

@Entity
public class Telefono extends Medio_contacto {

    public String numero;

    public Telefono() {}

    public Telefono (Persona persona, String numero_nuevo){
        super(persona);
        this.numero = numero_nuevo;
    }

    public void setNumero(String numero) {this.numero = numero;}
    @Override
    public void notificar (MensajeAviso mensaje) {
        System.out.println("Notificando Telefono");
    }
}
