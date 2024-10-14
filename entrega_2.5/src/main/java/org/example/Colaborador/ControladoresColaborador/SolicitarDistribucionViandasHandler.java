package org.example.Colaborador.ControladoresColaborador;

import org.example.Colaborador.Colaborador;
import org.example.Formas_contribucion.Motivo_distribucion;
import org.example.Heladeras.Heladera;

public class SolicitarDistribucionViandasHandler {
    public static void solicitarDistribucion(Colaborador colaborador, Heladera heladera0, Heladera heladera1, Integer cantidadViandasAMover, Motivo_distribucion motivoDistribucion) {
        colaborador.solicitarDistribucionViandas(heladera0, heladera1, cantidadViandasAMover, motivoDistribucion);

    }
}
