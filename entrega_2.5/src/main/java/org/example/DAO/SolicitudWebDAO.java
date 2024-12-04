package org.example.DAO;

import org.example.Tarjetas.SolicitudWeb;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class SolicitudWebDAO {
    private EntityManager entityManager;

    public SolicitudWebDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(SolicitudWeb solicitudWeb) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(solicitudWeb);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void update(SolicitudWeb solicitudWeb) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(solicitudWeb);
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
            SolicitudWeb solicitudWeb = entityManager.find(SolicitudWeb.class, id);
            if (solicitudWeb != null) {
                entityManager.remove(solicitudWeb);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    public SolicitudWeb findById(long id) {
        return entityManager.find(SolicitudWeb.class, id);
    }
}
