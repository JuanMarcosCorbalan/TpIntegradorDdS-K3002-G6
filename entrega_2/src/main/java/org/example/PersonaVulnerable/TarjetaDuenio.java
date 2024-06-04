package org.example.PersonaVulnerable;

import org.example.Colaborador.Colaborador;

import java.util.ArrayList;
import java.util.List;

public class TarjetaDuenio {
    Tarjeta tarjeta;
    Colaborador colaborador;
    PersonaSituacionVulnerable personaSV;
    List<RetiroVianda> retiros = new ArrayList<RetiroVianda>();

    public TarjetaDuenio(Tarjeta tarjeta, Colaborador colaborador,PersonaSituacionVulnerable personaSV) {
        this.tarjeta = tarjeta;
        this.colaborador = colaborador;
        this.personaSV = personaSV;
    }
    public void utilizar(PersonaSituacionVulnerable personaQueRetira){
        verificarRetiro(personaQueRetira);
    }
    public void verificarRetiro(PersonaSituacionVulnerable personaQueRetira){
        if(!personaQueRetira.equals(personaSV)){
            throw new Error("No le pertenece esta tarjeta, no puede retirar");
        }
    }

}
