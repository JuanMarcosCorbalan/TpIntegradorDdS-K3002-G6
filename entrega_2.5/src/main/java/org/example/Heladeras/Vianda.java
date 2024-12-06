package org.example.Heladeras;

import org.example.Formas_contribucion.Distribucion_viandas;
import org.example.Formas_contribucion.RegistrarPersonasSV;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Vianda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String nombreComida;
    LocalDate fechaCaducidad;
    String kiloCalorias;
    String peso;

    @ManyToOne
    private Distribucion_viandas registro;

    @ManyToOne
    private Heladera heladera;


    public Vianda(String nombreComida, LocalDate fechaCaducidad, String kiloCalorias, String peso) {
        this.nombreComida = nombreComida;
        this.fechaCaducidad = fechaCaducidad;
        this.kiloCalorias = kiloCalorias;
        this.peso = peso;
    }

    public Vianda(String nombreComida) {
        this.nombreComida = nombreComida;
    }

    public Vianda() {

    }

    public void setFechaCaducidad(LocalDate fechaCaducidad) {
            this.fechaCaducidad = fechaCaducidad;
    }
    public void setNombreComida(final String nombreComida) {
        this.nombreComida = nombreComida;
    }
    public void setKiloCalorias(final String kiloCalorias) {
        this.kiloCalorias = kiloCalorias;
    }
    public void setPeso(final String peso) {
        this.peso = peso;
    }
    public void setRegistro(final Distribucion_viandas registro) {
        this.registro = registro;
    }
    public void setHeladera(final Heladera heladera) {
        this.heladera = heladera;
    }
}
