package org.example.Colaborador.ControladoresColaborador;

import org.example.Colaborador.Colaborador;
import org.example.Formas_contribucion.HacerseCargoHeladera;

import javax.persistence.EntityManager;

public class SolicitarHacerseCargoHeladeraHandler {
    public static void hacerseCargoHeladeraSinApi(HacerseCargoHeladera hacerseCargoHeladera, String nombre_heladera, int temMin, int temMax, int cantViandas, EntityManager em){
        hacerseCargoHeladera.hacerseCargoSinApi(nombre_heladera,temMin,temMax,cantViandas,em);
    }
    public static void hacerseCargoHeladera(HacerseCargoHeladera hacerseCargoHeladera){
        hacerseCargoHeladera.hacerseCargo();
    }

}
