package org.example.DAO;

import org.example.Tarjetas.Tarjeta;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class TarjetaDAO {
    private EntityManager entityManager;

    public TarjetaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Tarjeta tarjeta) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(tarjeta);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void update(Tarjeta tarjeta) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(tarjeta);
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
            Tarjeta tarjeta = entityManager.find(Tarjeta.class, id);
            if (tarjeta != null) {
                entityManager.remove(tarjeta);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    public Tarjeta findById(long id) {
        return entityManager.find(Tarjeta.class, id);
    }
}
