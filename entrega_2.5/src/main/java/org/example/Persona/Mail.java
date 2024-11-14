package org.example.Persona;

import com.sendgrid.helpers.mail.objects.Email;
import org.example.Funcionalidades.EnvioMail;
import javax.persistence.*;

import java.io.IOException;

@Entity
public class Mail implements Medio_contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String email;

    @ManyToOne
    @JoinColumn(name = "mail_persona", nullable = false)
    public Persona persona;

    public Mail() {}

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
}
