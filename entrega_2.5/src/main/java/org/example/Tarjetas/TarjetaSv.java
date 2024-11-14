package org.example.Tarjetas;

import org.example.Colaborador.Colaborador;
import org.example.Heladeras.Heladera;
import org.example.PersonaVulnerable.PersonaSituacionVulnerable;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TarjetaSv extends Tarjeta {
    //String id_tarjeta;
    Integer cantidadUsos;
    //Colaborador colaborador;

    @OneToOne
    PersonaSituacionVulnerable personaSV;

    @OneToMany (mappedBy = "tarjetasv")
    List<RetiroVianda> retirosVianda;

    public TarjetaSv(Colaborador colaborador, PersonaSituacionVulnerable personaSV) {
        super(colaborador);
        this.personaSV = personaSV;
        this.cantidadUsos = setCantidadUsos(personaSV);
        this.retirosVianda = new ArrayList<RetiroVianda>();
    }

    //public TarjetaSv() {}

    private Integer setCantidadUsos(PersonaSituacionVulnerable personaSV) {
        int numero_base = 4; //NUMERO BASE QUE DAN EN LA CONSIGNA
        int menores_cargo = personaSV.getCantidadMenoresACargo();

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
