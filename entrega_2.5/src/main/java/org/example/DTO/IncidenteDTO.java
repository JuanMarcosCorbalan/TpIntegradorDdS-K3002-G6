package org.example.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public class IncidenteDTO {
    private Long idFalla;
    private String heladeraId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime hora;

    private String descripcion;
    private String rutaFoto;
    private Long idTecnico;
    private String nombreUbicacion;

    // Constructor
    public IncidenteDTO(Long id,String heladeraId, LocalDate fecha, LocalTime hora, String descripcion, String rutaFoto, Long idTecnico, String nombreUbicacion) {
        this.idFalla = id;
        this.heladeraId = heladeraId;
        this.fecha = fecha;
        this.hora = hora;
        this.descripcion = descripcion;
        this.rutaFoto = rutaFoto;
        this.idTecnico = idTecnico;
        this.nombreUbicacion = nombreUbicacion;
    }

    // Getters
    public String getHeladeraId() {
        return heladeraId;
    }

    public String getFecha() {
        return fecha != null ? fecha.toString() : "No disponible";
    }

    public String getHora() {
        return hora != null ? hora.toString() : "No disponible";
    }

    public String id(){
        return idFalla!= null ? idFalla.toString() : "No disponible";
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getRutaFoto() {
        return rutaFoto;
    }

    public long getIdTecnico() {
        return idTecnico;
    }

    public String getNombreUbicacion() {
        return nombreUbicacion;
    }

    public Long getIdFalla(){return idFalla;}
}

