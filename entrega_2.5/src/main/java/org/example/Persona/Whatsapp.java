package org.example.Persona;

import javax.persistence.*;

@Entity
public class Whatsapp implements Medio_contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String numero;

    @ManyToOne
    @JoinColumn(name = "whatsapp_persona", nullable = false) /*PARA LA FOREIGN KEY*/
    public Persona persona;

    public void notificar(Medio_contacto[] medios) {}
}
