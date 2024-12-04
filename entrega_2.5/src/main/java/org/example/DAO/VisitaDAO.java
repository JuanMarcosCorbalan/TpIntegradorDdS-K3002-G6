package org.example.DAO;

import org.example.Personal.Visita;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

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
}
