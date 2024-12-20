package org.example.DAO;

import org.example.DTO.IncidenteDTO;
import org.example.DTO.VisitaDTO;
import org.example.DTO.VisitaDTO2;
import org.example.Personal.Visita;
import org.example.Validadores_Sensores.FallaTecnica;
import org.example.Validadores_Sensores.Incidente;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class VisitaDAO {
    private EntityManager entityManager;

    public VisitaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Visita visita) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(visita);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void update(Visita visita) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(visita);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void delete(long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Visita visita = entityManager.find(Visita.class, id);
            if (visita != null) {
                entityManager.remove(visita);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    public Visita findById(long id) {
        return entityManager.find(Visita.class, id);
    }

    public List<VisitaDTO> findVisitas(Long idIncidente) {
        // Query para obtener todos los incidentes que sean de tipo 'FALLA_TECNICA'
        String query = "SELECT i FROM Visita i";

        List<Visita> visitas = entityManager.createQuery(query, Visita.class)
                .getResultList();

        List<VisitaDTO> visitasDTOs = new ArrayList<>();

        for (Visita visita : visitas) {

                // Filtrar por el id del técnico asignado
                if (visita.getIncidente().getId().equals(idIncidente)) {

                    Long idFalla = visita.getIncidente().getId();
                    LocalDate fecha = visita.getFechaVisita();
                    String descripcion = visita.getDescripcion();
                    String imagen = visita.getImagen();
                    Boolean solucionado = visita.getIncidenteSolucionado();

                    VisitaDTO dto = new VisitaDTO(idFalla, fecha, descripcion, imagen, solucionado);

                    visitasDTOs.add(dto);

                }
            }

        return visitasDTOs;
    }

    public List<VisitaDTO2> findVisitasHistorial(Long idTecnico) {
        // Query para obtener todos los incidentes que sean de tipo 'FALLA_TECNICA'
        String query = "SELECT i FROM Visita i";

        List<Visita> visitas = entityManager.createQuery(query, Visita.class)
                .getResultList();

        List<VisitaDTO2> visitasDTOs = new ArrayList<>();

        for (Visita visita : visitas) {

            // Filtrar por el id del técnico asignado
            if (visita.getTecnico().getId().equals(idTecnico)) {

                Long idIncidente = visita.getIncidente().getId();
                LocalDate fecha = visita.getFechaVisita();
                String descripcion = visita.getDescripcion();
                String idHeladera = visita.getHeladera().getIdHeladera();
                Boolean solucionado = visita.getIncidenteSolucionado();

                //fecha, descr, id, estado
                VisitaDTO2 dto = new VisitaDTO2(idIncidente,fecha, descripcion, idHeladera, solucionado);

                visitasDTOs.add(dto);

            }
        }

        return visitasDTOs;
    }

}
