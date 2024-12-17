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

    public ServicioReporte(List<Colaborador> colaboradores, List<Heladera> heladeras){

        planificadorReporte = new PlanificadorReporte(heladeras, colaboradores);
        planificadorReporte.start();
    }

    public Reporte getReporteActual(){
        return planificadorReporte.getReporteActual();
    }
    /*
    public static void main(String[] args) {
        //ServicioReporte servicioReporte = new ServicioReporte();
        // Simulación de obtener el reporte actual
        Reporte currentReport = servicioReporte.getReporteActual();
        if (currentReport != null) {
            System.out.println("Reporte actual: " + currentReport.getContenido());
            System.out.println("Reporte guardado en: " + currentReport.getFilePath());
        } else {
            System.out.println("No hay reporte disponible actualmente.");
        }
    }
    */

}
