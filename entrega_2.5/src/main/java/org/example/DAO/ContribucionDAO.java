package org.example.DAO;

import org.example.Formas_contribucion.Contribucion;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class ContribucionDAO {

    private EntityManager entityManager;

    public ContribucionDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Contribucion contribucion) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(contribucion);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void update(Contribucion Contribucion) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(Contribucion);
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
            Contribucion contribucion = entityManager.find(Contribucion.class, id);
            if (contribucion != null) {
                entityManager.remove(contribucion);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    public Contribucion findById(long id) {
        return entityManager.find(Contribucion.class, id);
    }
}
