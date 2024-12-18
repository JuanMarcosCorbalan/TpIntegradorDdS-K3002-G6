package org.example.Persona;

import org.example.Suscripcion.MensajeAviso;

import javax.persistence.*;

@Entity
public class Mail extends Medio_contacto {

    public String email;

    public Mail() {}

    public Mail (Persona persona, String email){
        super(persona);
        this.email = email;
    }

    /*
    public void notificar(Medio_contacto[] medios) throws IOException {
        Email email = this.getMail(medios);
        //new EnvioMail().enviarEmail(email);
    }*/
    /*
    public Email getMail(Medio_contacto[] medios) {
        for(Medio_contacto mc : medios) {
            if(mc instanceof Mail) {
                return new Email(((Mail) mc).getMail());
            }
        }
        return null;
    }*/
    public String getMail() {
        return email;
    }

    public void setEmail(String email) {this.email = email;}

    @Override
    public void notificar (MensajeAviso mensaje) {
        System.out.println("Notificando Mail");
    }


}
