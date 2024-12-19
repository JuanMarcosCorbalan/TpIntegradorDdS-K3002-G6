package org.example.Validadores_Sensores;

import org.example.Heladeras.Heladera;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Alerta extends Incidente{
    @Enumerated (EnumType.STRING)
    TipoAlerta tipo;

    public Alerta(Heladera heladera,TipoIncidente tipoIncidente, TipoAlerta tipoAlerta){
        super(heladera, tipoIncidente);
        this.tipo = tipoAlerta;
    }

    public Alerta() {

    }

    public TipoAlerta getTipoAlerta(){return tipo;}
    //public Heladera getHeladera(){return heladera;}
}
