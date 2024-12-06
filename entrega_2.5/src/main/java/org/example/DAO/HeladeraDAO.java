package org.example.DAO;

import org.example.Heladeras.Heladera;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

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
}
