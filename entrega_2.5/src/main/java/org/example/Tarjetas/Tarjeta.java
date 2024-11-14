package org.example.Tarjetas;

import org.example.Colaborador.Colaborador;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Tarjeta {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn (name = "tarjeta_colaborador")
    public Colaborador colaborador;


    public Tarjeta(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public Tarjeta() {

    }
}
