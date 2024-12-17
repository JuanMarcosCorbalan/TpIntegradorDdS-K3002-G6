package org.example.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalTime;

import java.time.LocalDate;
import java.time.LocalTime;

public class IncidenteDTO {
    private String heladeraId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime hora;

    private String descripcion;
    private String rutaFoto;
    private Long idTecnico;
    private String nombre_ubicacion;

    // Constructor
    public IncidenteDTO(String heladeraId, LocalDate fecha, LocalTime hora, String descripcion, String rutaFoto, Long idTecnico, String nombre_ubicacion) {
        this.heladeraId = heladeraId;
        this.fecha = fecha;
        this.hora = hora;
        this.descripcion = descripcion;
        this.rutaFoto = rutaFoto;
        this.idTecnico = idTecnico;
        this.nombre_ubicacion = nombre_ubicacion;
    }

    // Getters
    public String getHeladeraId() {
        return heladeraId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHora() {
        return hora;
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

    public String getNombre_ubicacion() {
        return nombre_ubicacion;
    }
}

