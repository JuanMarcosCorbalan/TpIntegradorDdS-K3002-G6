package org.example.Colaborador.ControladoresColaborador;

import org.example.Colaborador.Colaborador;
import org.example.Formas_contribucion.HacerseCargoHeladera;
import org.example.Heladeras.Heladera;

import javax.persistence.EntityManager;

public class SolicitarHacerseCargoHeladeraHandler {
    public static void hacerseCargoHeladeraSinApi(HacerseCargoHeladera hacerseCargoHeladera, String nombre_heladera, Integer temMin, Integer temMax, Integer cantViandas, EntityManager em){
        hacerseCargoHeladera.hacerseCargoSinApi(nombre_heladera,temMin,temMax,cantViandas,em);
    }
    public static void hacerseCargoHeladeraConApi(HacerseCargoHeladera hacerseCargoHeladera, String nombre_heladera, Integer temMin, Integer temMax, Integer cantViandas,String coordenadas,EntityManager em){
        hacerseCargoHeladera.hacerseCargoConApi(nombre_heladera,temMin,temMax,cantViandas,coordenadas,em);
    }

}
