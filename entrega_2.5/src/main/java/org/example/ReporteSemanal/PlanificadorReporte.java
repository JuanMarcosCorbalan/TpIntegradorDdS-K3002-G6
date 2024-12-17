package org.example.ReporteSemanal;

import org.example.Colaborador.Colaborador;
import org.example.DAO.ReporteDAO;
import org.example.Heladeras.Heladera;
import org.example.ReporteSemanal.GeneradorReporte;
import org.example.ReporteSemanal.Reporte;
import org.example.Utils.BDutils;

import javax.persistence.EntityManager;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.List;

public class PlanificadorReporte {
    private Timer timer;
    private GeneradorReporte generadorReporte;
    private Reporte reporteActual;

    public PlanificadorReporte(List<Heladera> heladeras, List<Colaborador> colaboradores) {
        timer = new Timer();
        generadorReporte = new GeneradorReporte(heladeras, colaboradores);
        generateAndStoreReport();
    }

    public void start() {
        scheduleWeeklyReport();
    }

    private void scheduleWeeklyReport() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date firstExecution = calendar.getTime();
        if (firstExecution.before(new Date())) {
            calendar.add(Calendar.WEEK_OF_YEAR, 1);
            firstExecution = calendar.getTime();
        }

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                generateAndStoreReport();
            }
        }, firstExecution, 7 * 24 * 60 * 60 * 1000); // Repetir cada semana
    }

    private void generateAndStoreReport() {
        EntityManager em = BDutils.getEntityManager();
        ReporteDAO reporteDAO = new ReporteDAO(em);

        reporteActual = generadorReporte.generarReporte();

        // Aquí puedes guardar el reporte en una base de datos o en un archivo si es necesario
        System.out.println("Reporte generado: " + reporteActual.getContenido());
        System.out.println("Reporte guardado en: " + reporteActual.getFilePath());
        reporteDAO.save(reporteActual);
    }

    public Reporte getReporteActual() {
        return reporteActual;
    }
}