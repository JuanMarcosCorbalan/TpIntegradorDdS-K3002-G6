package org.example.DAO;

import org.example.Heladeras.EstadoHeladera;
import org.example.Heladeras.Heladera;
import org.example.Heladeras.HeladeraDTO;
import org.example.Ofertas.Oferta;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;
public class HeladeraDAO {
    private EntityManager entityManager;

    public HeladeraDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Heladera heladera) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(heladera);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void update(Heladera heladera) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(heladera);
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
            Heladera heladera = entityManager.find(Heladera.class, id);
            if (heladera != null) {
                entityManager.remove(heladera);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    public Heladera findById(long id) {
        return entityManager.find(Heladera.class, id);
    }

    public List<HeladeraDTO> findAllHeladeras() {
        String query = "SELECT h FROM Heladera h";
        List<Heladera> heladeras = entityManager.createQuery(query, Heladera.class).getResultList();
        List<HeladeraDTO> heladeraDTOs = new ArrayList<HeladeraDTO>();

        for (Heladera heladera : heladeras) {
            String latitud = heladera.getPuntoUbicacion().getLatitud();
            String longitud = heladera.getPuntoUbicacion().getLongitud();
            EstadoHeladera estado = heladera.getEstado();

            HeladeraDTO heladeraDTO = new HeladeraDTO(latitud, longitud, estado);
            heladeraDTOs.add(heladeraDTO);
        }

        return heladeraDTOs;
    }


}
