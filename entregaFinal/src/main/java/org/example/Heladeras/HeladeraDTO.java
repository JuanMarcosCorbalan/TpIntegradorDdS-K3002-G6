package org.example.Heladeras;


import javax.persistence.criteria.CriteriaBuilder;

public class HeladeraDTO {
    private String idHeladera;
    private String latitud;
    private String longitud;
    private EstadoHeladera estado;
    private Integer cantidadViandasActual;
    private Integer cantidadViandasPendientesRetiro;
    private Integer cantidadViandasPendientesIngreso;


    public HeladeraDTO(String latitud, String longitud, EstadoHeladera estado, String idHeladera, Integer viandasActuales, Integer viandasPendientesRetiro, Integer viandasPendientesIngreso) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.estado = estado;
        this.idHeladera = idHeladera;
        this.cantidadViandasActual = viandasActuales;
        this.cantidadViandasPendientesRetiro = viandasPendientesRetiro;
        this.cantidadViandasPendientesIngreso = viandasPendientesIngreso;

    }

    // Getters
    public String getLatitud() {
        return latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public EstadoHeladera getEstado() {
        return estado;
    }

    public String getIdHeladera() {return idHeladera;}

    public Integer getCantidadViandasActual() {
        return cantidadViandasActual;
    }

    public Integer getCantidadViandasPendientesRetiro() {
        return cantidadViandasPendientesRetiro;
    }

    public Integer getCantidadViandasPendientesIngreso() {
        return cantidadViandasPendientesIngreso;
    }

    // Setters
    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public void setEstado(EstadoHeladera estado) {
        this.estado = estado;
    }

    public void setIdHeladera(String idHeladera) {this.idHeladera=idHeladera;}

    public void setCantidadViandasActual(Integer cantidadViandasActual) {
        this.cantidadViandasActual = cantidadViandasActual;
    }

    public void setCantidadViandasPendientesRetiro(Integer cantidadViandasPendientesRetiro) {
        this.cantidadViandasPendientesRetiro = cantidadViandasPendientesRetiro;
    }

    public void setCantidadViandasPendientesIngreso(Integer cantidadViandasPendientesIngreso) {
        this.cantidadViandasPendientesIngreso = cantidadViandasPendientesIngreso;
    }
}

