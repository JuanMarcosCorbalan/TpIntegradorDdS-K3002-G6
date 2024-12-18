package org.example.Persona;

import org.example.Suscripcion.MensajeAviso;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Medio_contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
   // @JoinColumn(name = "persona_id", nullable = false) /*PARA LA FOREIGN KEY*/
    public Persona persona;

    //public void setDetalle(String detalle){}
    //public void setPersona(Persona persona){}
    public Medio_contacto(Persona persona) {this.persona = persona;}

    public Medio_contacto() {}

    public void setPersona(Persona persona){
        this.persona = persona;
    }

    void notificar(MensajeAviso mensaje)  {

    }
}

