package org.example.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.example.Heladeras.Heladera;
import org.example.Personal.Tecnico;
import org.example.Validadores_Sensores.FallaTecnica;

import javax.persistence.ManyToOne;
import java.time.LocalDate;

public class VisitaDTO {

    private Long idFalla;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaVisita;
    private String descripcion;
    private String imagen;
    private Boolean incidenteSolucionado;

    public VisitaDTO(Long idFalla, LocalDate fechaVisita, String descripcion, String imagen, Boolean incidenteSolucionado) {
        this.idFalla = idFalla;
        this.fechaVisita = fechaVisita;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.incidenteSolucionado = incidenteSolucionado;
    }

    public Long getIdFalla() {return this.idFalla;}
    public LocalDate getFechaVisita() {return this.fechaVisita;}
    public String getDescripcion() {return this.descripcion;}
    public String getImagen() {return this.imagen;}
    public Boolean getIncidenteSolucionado() {return this.incidenteSolucionado;}

}
