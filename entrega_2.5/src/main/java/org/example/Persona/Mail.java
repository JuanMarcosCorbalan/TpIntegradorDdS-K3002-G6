package org.example.Persona;

import com.sendgrid.helpers.mail.objects.Email;
import org.example.Funcionalidades.EnvioMail;
import javax.persistence.*;

import java.io.IOException;

@Entity
public class Mail extends Medio_contacto {

    public String email;

    public Mail() {}

    public Mail (Persona persona, String email_nuevo){
        this.persona = persona;
        this.email = email_nuevo;
    }

    public void notificar(Medio_contacto[] medios) throws IOException {
        Email email = this.getMail(medios);
        //new EnvioMail().enviarEmail(email);
    }
    public Email getMail(Medio_contacto[] medios) {
        for(Medio_contacto mc : medios) {
            if(mc instanceof Mail) {
                return new Email(((Mail) mc).getMail());
            }
        }
        return null;
    }
    public String getMail() {
        return email;
    }

    @Override
    public void setDetalle(String mail){
        this.email = mail;
    }

    public Mail(String mail) {
        this.email = mail;
    }
}
