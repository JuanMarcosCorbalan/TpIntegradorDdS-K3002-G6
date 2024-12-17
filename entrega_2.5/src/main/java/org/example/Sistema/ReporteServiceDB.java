package org.example.Sistema;

import org.example.ReporteSemanal.Reporte;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class ReporteServiceDB {
    private EntityManager entityManager;
    private RegistrarUsuario servicioValidacion;

    public ReporteServiceDB(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.servicioValidacion = new RegistrarUsuario(entityManager);
    }

    public Reporte findLast() {
        try {
            return entityManager.createQuery(
                            "SELECT r FROM Reporte r ORDER BY r.fechaGeneracion DESC", Reporte.class)
                    .setMaxResults(1) // Limita la consulta a solo el reporte más reciente
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // No hay reportes en la base de datos
        }
    }

    public List<Reporte> findUltimosReportes(int cantidad) {
        try {
            // Realizar la consulta para obtener los últimos 'cantidad' reportes ordenados por fechaGeneracion descendente
            return entityManager.createQuery(
                            "SELECT r FROM Reporte r ORDER BY r.fechaGeneracion DESC", Reporte.class)
                    .setMaxResults(cantidad) // Limitar la cantidad de resultados
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    public Reporte findById(Long id) {
        try {
            return entityManager.find(Reporte.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Si ocurre un error, devolvemos null
        }
    }
}

