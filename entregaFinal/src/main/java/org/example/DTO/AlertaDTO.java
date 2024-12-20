package org.example.DTO;

import net.bytebuddy.asm.Advice;
import org.example.Validadores_Sensores.EstadoIncidente;
import org.example.Validadores_Sensores.TipoAlerta;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class AlertaDTO {

    private LocalDate fecha;
    private LocalTime hora;
    private String tipoAlerta;
    private String estadoAlerta;

    public AlertaDTO(LocalDate fecha, LocalTime hora, TipoAlerta tipoAlerta, EstadoIncidente estado) {
        this.fecha = fecha;
        this.hora = hora;
        this.tipoAlerta= deducirAlerta(tipoAlerta);
        this.estadoAlerta = deducirEstadoIncidente(estado);
    }

    public String getFecha() {
        return fecha.toString();}

    public String getHora() {
        return hora.toString();
    }

    public String getTipoAlerta() {
        return tipoAlerta;
    }

    public String getEstadoAlerta() {return estadoAlerta;}

    public void setFecha(LocalDate fecha) {this.fecha= fecha;}
    public void setHora(LocalTime hora) {this.hora= hora;}

    public void setTipoAlerta(String tipoAlerta) {this.tipoAlerta= tipoAlerta;}

    public String deducirAlerta(TipoAlerta tipo) {
        if(tipo.equals(TipoAlerta.ALERT_FRAUDE))
        {
            return "Fraude";
        }else if(tipo.equals(TipoAlerta.ALERT_TEMPERATURA))
        {
            return "Temperatura";
        }
        else{
            return "Falla Conexion";
        }
    }

    public String deducirEstadoIncidente(EstadoIncidente estado) {
        if(estado.equals(EstadoIncidente.REPARADO))
        {
            return "Reparado";
        }
        else{
            return "No reparado";
        }
    }


}
