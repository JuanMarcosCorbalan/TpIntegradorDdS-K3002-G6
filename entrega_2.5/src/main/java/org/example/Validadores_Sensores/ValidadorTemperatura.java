package org.example.Validadores_Sensores;

import org.example.Heladeras.Heladera;

public class ValidadorTemperatura extends Validador {

    public ValidadorTemperatura(Heladera h){super(h);}
    public void procesarTemperatura(Double temperatura)
    {
        int temp_maxima = heladera.getTemperaturaMaxima();
        int temp_minima = heladera.getTemperaturaMinima();
        if(temperatura < temp_maxima && temperatura > temp_minima){
            darAviso(TipoAlerta.ALERT_TEMPERATURA);
        }
    }





}
