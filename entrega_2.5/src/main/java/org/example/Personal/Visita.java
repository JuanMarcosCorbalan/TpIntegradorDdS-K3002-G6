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
    private Long id;

    @ManyToOne
    Tecnico tecnico;

    FallaTecnica fallaRevisada;
    Heladera heladera;
    LocalDate fechaVisita;
    String descripcion;
    File imagen;
    Boolean incidenteSolucionado;

    public Visita(FallaTecnica fallaTecnica, Heladera heladera, String descripcion, Boolean incidenteSolucionado,File imagen) {
        this.fallaRevisada = fallaTecnica;
        this.heladera = heladera;
        this.descripcion = descripcion;
        this.incidenteSolucionado = incidenteSolucionado;
        fechaVisita = LocalDate.now();
        this.imagen=imagen;
        if(incidenteSolucionado) {
            heladera.activar();
        }
    }
}
