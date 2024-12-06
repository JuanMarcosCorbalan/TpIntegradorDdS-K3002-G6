package org.example.DAO;

import org.example.Persona.Persona;
import org.example.Validadores_Sensores.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

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

    public Incidente findById(long id) {
        return entityManager.find(Incidente.class, id);
    }
}
