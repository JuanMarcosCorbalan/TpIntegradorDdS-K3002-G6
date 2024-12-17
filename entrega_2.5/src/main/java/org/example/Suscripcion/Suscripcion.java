package org.example.Suscripcion;

import org.example.Colaborador.Colaborador;
import org.example.Heladeras.Heladera;

import javax.persistence.*;
import java.io.IOException;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Suscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    Colaborador colaborador;

    @ManyToOne
    Heladera heladera;

    @Enumerated(EnumType.STRING)
    TipoSuscripcion tipoSuscripcion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public TipoSuscripcion getTipoSuscripcion() {
        return tipoSuscripcion;
    }

    void darAviso()  {

    }
}
