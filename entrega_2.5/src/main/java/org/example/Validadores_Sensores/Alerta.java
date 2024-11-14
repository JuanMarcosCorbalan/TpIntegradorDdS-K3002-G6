package org.example.Validadores_Sensores;

import org.example.Heladeras.Heladera;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Alerta extends Incidente{
    TipoAlerta tipo;

    public Alerta(Heladera heladera,TipoIncidente tipoIncidente, TipoAlerta tipoAlerta){
        super(heladera, tipoIncidente);
        this.tipo = tipoAlerta;
    }
    public TipoAlerta getTipo(){return tipo;}
    public Heladera getHeladera(){return heladera;}
}
