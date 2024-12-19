package org.example.Personal;

import java.io.File;
import java.time.LocalDate;
import org.example.Heladeras.Heladera;
import org.example.Validadores_Sensores.FallaTecnica;

import javax.persistence.*;

@Entity
public class Visita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autogenerado e incremental
    public Long id;

    @ManyToOne
    Tecnico tecnico;

    @ManyToOne
    FallaTecnica fallaRevisada;

    @ManyToOne
    Heladera heladera;


    LocalDate fechaVisita;
    String descripcion;
    String imagen;
    Boolean incidenteSolucionado;

    public Visita(FallaTecnica fallaTecnica, Heladera heladera, String descripcion, Boolean incidenteSolucionado,String imagen, Tecnico tecnico,LocalDate fechaVisita) {
        this.fallaRevisada = fallaTecnica;
        this.heladera = heladera;
        this.descripcion = descripcion;
        this.incidenteSolucionado = incidenteSolucionado;
        this.fechaVisita = fechaVisita;
        this.imagen=imagen;
        this.tecnico=tecnico;
        if(incidenteSolucionado) {
            heladera.activar();
        }
    }

    public Visita() {

    }

    public Long getId() {return id;}
    public Tecnico getTecnico() {return tecnico;}
    public FallaTecnica getFallaRevisada() {return fallaRevisada;}
    public Heladera getHeladera() {return heladera;}
    public LocalDate getFechaVisita() {return fechaVisita;}
    public String getDescripcion() {return descripcion;}
    public String getImagen() {return imagen;}
    public Boolean getIncidenteSolucionado() {return incidenteSolucionado;}
}
