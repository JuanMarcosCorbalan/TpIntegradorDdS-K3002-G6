package org.example.Persona;

import org.example.Suscripcion.MensajeAviso;

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

    public void setNumero (String numero){this.numero = numero;}
    public String getNumero (){return this.numero;}

    @Override
    public void notificar (MensajeAviso mensaje) {
        System.out.println("Notificando Whatsapp");
    }
}
