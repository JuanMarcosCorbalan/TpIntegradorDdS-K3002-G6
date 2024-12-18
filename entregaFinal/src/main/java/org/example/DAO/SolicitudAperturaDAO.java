package org.example.DAO;

import org.example.Tarjetas.SolicitudApertura;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class SolicitudAperturaDAO {
    private EntityManager entityManager;

    public SolicitudAperturaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(SolicitudApertura solicitudApertura) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(solicitudApertura);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void update(SolicitudApertura solicitudApertura) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(solicitudApertura);
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
            SolicitudApertura solicitudApertura = entityManager.find(SolicitudApertura.class, id);
            if (solicitudApertura != null) {
                entityManager.remove(solicitudApertura);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    public SolicitudApertura findById(long id) {
        return entityManager.find(SolicitudApertura.class, id);
    }
}
