package org.example.Tarjetas.PersonaVulnerable;

import org.example.Colaborador.Colaborador;
import org.example.Heladeras.Heladera;
import org.example.PersonaVulnerable.PersonaSituacionVulnerable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Tarjeta {
    String id_tarjeta;
    Integer cantidadUsos;
    Colaborador colaborador;
    PersonaSituacionVulnerable personaSV;
    ArrayList<RetiroVianda> retirosVianda;

    public Tarjeta(String id_tarjeta, Colaborador colaborador, PersonaSituacionVulnerable personaSV) {
        this.id_tarjeta = id_tarjeta;
        this.colaborador = colaborador;
        this.personaSV = personaSV;
        this.cantidadUsos = setCantidadUsos(personaSV);
        this.retirosVianda = new ArrayList<RetiroVianda>();
    }

    private Integer setCantidadUsos(PersonaSituacionVulnerable personaSV) {
        int numero_base = 4; //NUMERO BASE QUE DAN EN LA CONSIGNA
        int menores_cargo = personaSV.cantidadMenoresACargo;

        return numero_base + 2 *menores_cargo; // SE ANIADEN DOS USOS MAS POR CADA MENOR A CARGO
    }

    public Integer getCantidadUsos() {
        return cantidadUsos;
    }

    public void utilizar(Heladera heladera_retiro){
        if(verificarRetiro())
        {
            LocalDate fecha = LocalDate.now();
            LocalTime hora = LocalTime.now();
            RetiroVianda nuevo_retiro = new RetiroVianda(personaSV,fecha,hora,heladera_retiro);
            retirosVianda.add(nuevo_retiro);
        }
    }

    public boolean verificarRetiro(){
        LocalDate actual = LocalDate.now();
        int contador = (int) retirosVianda.stream().filter(retiro -> retiro.fechaRetiro.equals(actual)).count();
        return contador < cantidadUsos;
    }
}
