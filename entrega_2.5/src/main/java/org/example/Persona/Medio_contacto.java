package org.example.Persona;

import javax.persistence.*;
import java.io.IOException;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Medio_contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
   // @JoinColumn(name = "persona_id", nullable = false) /*PARA LA FOREIGN KEY*/
    public Persona persona;

    public void setDetalle(String detalle){}
    //public void setPersona(Persona persona){}

    public void setPersona(Persona persona){
        this.persona = persona;
    }

    void notificar(Medio_contacto[] medios_contacto) throws IOException {

    }
}

