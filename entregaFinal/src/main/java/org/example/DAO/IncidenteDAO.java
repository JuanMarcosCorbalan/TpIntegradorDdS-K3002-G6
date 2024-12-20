package org.example.DAO;

import org.example.DTO.AlertaDTO;
import org.example.DTO.IncidenteDTO;
import org.example.Heladeras.EstadoHeladera;
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

    public List<IncidenteDTO> findIncidentes(Long idTecnico) {
        // Query para obtener todos los incidentes que sean de tipo 'FALLA_TECNICA'
        String query = "SELECT i FROM Incidente i";

        List<Incidente> incidentes = entityManager.createQuery(query, Incidente.class)
                .getResultList();

        List<IncidenteDTO> incidenteDTOs = new ArrayList<>();

        for (Incidente incidente : incidentes) {
            //if (incidente instanceof FallaTecnica fallaTecnica) {
                // Filtrar por el id del técnico asignado
                if (incidente.getTecnicoAsignado() != null &&
                        incidente.getTecnicoAsignado().getId().equals(idTecnico) && !incidente.getEstadoIncidente().equals(EstadoIncidente.REPARADO) &&
                incidente.getHeladera().getEstado().equals(EstadoHeladera.EN_MANTENIMIENTO)) {
                    String descripcion = null;
                    String rutaFoto = null;
                    TipoAlerta tipoAlerta = null;

                    TipoIncidente tipoIncidente = incidente.getTipoIncidente();
                    String heladeraId = incidente.getHeladera().getIdHeladera();
                    LocalDate fecha = incidente.getFecha();
                    LocalTime hora = incidente.getHora();
                    if(incidente instanceof FallaTecnica fallaTecnica) {
                        descripcion = fallaTecnica.getDescripcion(); // Método de la clase hija
                        rutaFoto = fallaTecnica.getFoto();
                    }else if(incidente instanceof Alerta alerta){
                        tipoAlerta = alerta.getTipoAlerta();
                    }
                               // Método de la clase hija
                    Long idTecnicoAsignado = (incidente.getTecnicoAsignado() != null)
                            ? incidente.getTecnicoAsignado().getId() : 0;
                    String nombreUbicacion = incidente.getHeladera().getPuntoUbicacion().getNombre();
                    Long idIncidente = incidente.getId();
                    // Crear el DTO
                    IncidenteDTO dto = new IncidenteDTO(idIncidente,heladeraId, fecha, hora, descripcion, rutaFoto, idTecnicoAsignado,nombreUbicacion,tipoIncidente,tipoAlerta);
                    incidenteDTOs.add(dto);
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
                EstadoIncidente estado = alerta.getEstadoIncidente();

                AlertaDTO dto = new AlertaDTO(fecha,hora,tipo,estado);
                alertas.add(dto);
            }

        }

        return alertas;
    }




}
