package org.example.Persona;

import com.sendgrid.helpers.mail.objects.Email;
import org.example.Funcionalidades.EnvioMail;

import java.io.IOException;

public class Mail implements Medio_contacto {

    String mail;
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
        return mail;
    }

    public Mail(String mail) {
        this.mail = mail;
    }
}
