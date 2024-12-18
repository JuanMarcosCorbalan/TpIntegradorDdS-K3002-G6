package org.example.DTO;

import net.bytebuddy.asm.Advice;
import org.example.Validadores_Sensores.TipoAlerta;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class AlertaDTO {

    private LocalDate fecha;
    private LocalTime hora;
    private String tipoAlerta;

    public AlertaDTO(LocalDate fecha, LocalTime hora, TipoAlerta tipoAlerta) {
        this.fecha = fecha;
        this.hora = hora;
        this.tipoAlerta= deducirAlerta(tipoAlerta);
    }

    public String getFecha() {
        return fecha.toString();}

    public String getHora() {
        return hora.toString();
    }

    public String getTipoAlerta() {
        return tipoAlerta;
    }

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


}
