package org.example.ReporteSemanal;

import org.example.Colaborador.Colaborador;
import org.example.Formas_contribucion.Donacion_viandas;
import org.example.Heladeras.Heladera;
import org.example.Heladeras.Vianda;
import org.example.Persona.Persona_fisica;
import org.example.Validadores_Sensores.Validador;

import java.util.ArrayList;
import java.util.List;

public class ServicioReporte {
    private PlanificadorReporte planificadorReporte;

    public ServicioReporte(){
        List<Heladera> heladeras = new ArrayList<>();
        Heladera heladera1 = new Heladera("H1");
        Heladera heladera2 = new Heladera("H2");
        heladeras.add(heladera1);
        heladeras.add(heladera2);

        List<Colaborador> colaboradores = new ArrayList<>();

        Persona_fisica juan = new Persona_fisica("Juan Roman", "Riquelme");
        Colaborador juanColaborador = new Colaborador(juan);

        Persona_fisica martin = new Persona_fisica("Martin", "Palermo");
        Colaborador martinColaborador = new Colaborador(martin);
        colaboradores.add(juanColaborador);
        colaboradores.add(martinColaborador);

        Vianda vianda1 = new Vianda("Polenta");
        Vianda vianda2 = new Vianda("Hamburguesa");
        Vianda vianda3 = new Vianda("Dona");
        Donacion_viandas donacion1 = new Donacion_viandas(juanColaborador, heladera1, vianda1);
        Donacion_viandas donacion2 = new Donacion_viandas(juanColaborador, heladera1, vianda2);
        donacion1.realizar_contribucion();
        donacion2.realizar_contribucion();
        //juanColaborador.agregarContribucion(donacion2);
        Donacion_viandas donacion3 = new Donacion_viandas(martinColaborador, heladera2, vianda3);
        donacion3.realizar_contribucion();
        //martinColaborador.agregarContribucion(donacion3);

        planificadorReporte = new PlanificadorReporte(heladeras, colaboradores);
        planificadorReporte.start();
    }

    public Reporte getReporteActual(){
        return planificadorReporte.getReporteActual();
    }
    public static void main(String[] args) {
        ServicioReporte servicioReporte = new ServicioReporte();
        // Simulación de obtener el reporte actual
        Reporte currentReport = servicioReporte.getReporteActual();
        if (currentReport != null) {
            System.out.println("Reporte actual: " + currentReport.getContenido());
            System.out.println("Reporte guardado en: " + currentReport.getFilePath());
        } else {
            System.out.println("No hay reporte disponible actualmente.");
        }
    }
}
