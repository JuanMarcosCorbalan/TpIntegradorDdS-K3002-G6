package org.example.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class VisitaDTO2 {


    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaVisita;
    private String descripcion;
    private String idHeladera;
    private String estadoVisita;
    private Long idIncidente;

    public VisitaDTO2(Long idIncidente,LocalDate fecha,String descripcion,String heladera,Boolean incidenteSolucionado)
    {
        this.idIncidente=idIncidente;
        this.fechaVisita = fecha;
        this.descripcion = descripcion;
        this.idHeladera = heladera;
        this.estadoVisita = obtenerEstado(incidenteSolucionado);
    }

    public String obtenerEstado(Boolean incidenteSolucionado)
    {
        System.out.print(incidenteSolucionado);
        if(incidenteSolucionado)
        {
            return "REPARADO";
        }
        else{
            return "NO REPARADO";
        }
    }

    public String getFechaVisita() {
        return fechaVisita.toString();
    }

    public String getDescripcion() {return descripcion;}

    public String getIdHeladera() {return idHeladera;}

    public String getEstadoVisita() {return estadoVisita;}

    public String getIdIncidente() {return idIncidente.toString();}
}
