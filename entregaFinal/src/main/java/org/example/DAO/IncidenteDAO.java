package org.example.DAO;

import org.example.DTO.AlertaDTO;
import org.example.DTO.IncidenteDTO;
import org.example.Heladeras.Heladera;
import org.example.Heladeras.HeladeraDTO2;
import org.example.Persona.Persona;
import org.example.Validadores_Sensores.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class IncidenteDAO {

    private EntityManager entityManager;

    public IncidenteDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Incidente incidente) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(incidente);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void update(Incidente incidente) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(incidente);
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
            Incidente incidente = entityManager.find(Incidente.class, id);
            if (incidente != null) {
                entityManager.remove(incidente);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    public Incidente findById(Long id) {
        return entityManager.find(Incidente.class, id);
    }

    public List<IncidenteDTO> findFallasTecnicas(Long idTecnico) {
        // Query para obtener todos los incidentes que sean de tipo 'FALLA_TECNICA'
        String query = "SELECT i FROM Incidente i WHERE TYPE(i) = FallaTecnica";

        List<Incidente> incidentes = entityManager.createQuery(query, Incidente.class)
                .getResultList();

        List<IncidenteDTO> incidenteDTOs = new ArrayList<>();

        for (Incidente incidente : incidentes) {
            if (incidente instanceof FallaTecnica fallaTecnica) {
                // Filtrar por el id del técnico asignado
                if (fallaTecnica.getTecnicoAsignado() != null
                        && fallaTecnica.getTecnicoAsignado().getId().equals(idTecnico)) {

                    String heladeraId = fallaTecnica.getHeladera().getIdHeladera();
                    LocalDate fecha = fallaTecnica.getFecha();
                    LocalTime hora = fallaTecnica.getHora();
                    String descripcion = fallaTecnica.getDescripcion(); // Método de la clase hija
                    String rutaFoto = fallaTecnica.getFoto();           // Método de la clase hija
                    Long idTecnicoAsignado = (fallaTecnica.getTecnicoAsignado() != null)
                            ? fallaTecnica.getTecnicoAsignado().getId() : 0;
                    String nombreUbicacion = fallaTecnica.getHeladera().getPuntoUbicacion().getNombre();
                    Long idFalla = fallaTecnica.getId();
                    // Crear el DTO
                    IncidenteDTO dto = new IncidenteDTO(idFalla,heladeraId, fecha, hora, descripcion, rutaFoto, idTecnicoAsignado,nombreUbicacion);
                    incidenteDTOs.add(dto);
                }
            }
        }
        return incidenteDTOs;
    }

    public List<AlertaDTO> findAlertas(String idHeladeraBuscado)
    {
        String query = "SELECT i FROM Incidente i WHERE TYPE(i) = Alerta";

        List<Incidente> incidentes = entityManager.createQuery(query,Incidente.class)
                .getResultList();

        List<AlertaDTO> alertas = new ArrayList<>();

        for(Incidente incidente : incidentes){


            Alerta alerta = (Alerta) incidente;
            String idHeladera = alerta.getHeladera().getIdHeladera();
            if(idHeladera.equals(idHeladeraBuscado)){
                LocalDate fecha = alerta.getFecha();
                LocalTime hora = alerta.getHora();
                TipoAlerta tipo = alerta.getTipoAlerta();

                AlertaDTO dto = new AlertaDTO(fecha,hora,tipo);
                alertas.add(dto);
            }

        }

        return alertas;
    }




}
