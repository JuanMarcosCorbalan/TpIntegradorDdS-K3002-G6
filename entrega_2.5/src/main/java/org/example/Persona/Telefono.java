package org.example.Persona;

import javax.persistence.*;

@Entity
public class Telefono implements Medio_contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public String numero;

    @ManyToOne
    @JoinColumn(name = "telefono_persona", nullable = false) /*PARA LA FOREIGN KEY*/
    public Persona persona;

    public Telefono() {}

    public void notificar(Medio_contacto[] medios) {}
}
