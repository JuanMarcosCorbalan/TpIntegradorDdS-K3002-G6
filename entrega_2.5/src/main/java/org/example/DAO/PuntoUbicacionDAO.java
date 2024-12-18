package org.example.DAO;

import org.example.Persona.Ciudad;
import org.example.Heladeras.PuntoUbicacion;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

public class PuntoUbicacionDAO {
    private EntityManager entityManager;

    public PuntoUbicacionDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public PuntoUbicacion findOrCreate(String latitud, String longitud) {

        PuntoUbicacion puntoUbicacion = null;
        try {
            puntoUbicacion = entityManager
                    .createQuery("select p from PuntoUbicacion p where p.latitud = ?1 and p.longitud = ?2", PuntoUbicacion.class)
                    .setParameter(1, latitud)
                    .setParameter(2, longitud)
                    .getSingleResult();
        } catch (NoResultException e) {
            puntoUbicacion = new PuntoUbicacion(latitud, longitud);
            return puntoUbicacion;
        }
        return puntoUbicacion;
    }

    public void save(PuntoUbicacion puntoUbicacion) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(puntoUbicacion);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void update(PuntoUbicacion puntoUbicacion) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(puntoUbicacion);
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
            PuntoUbicacion puntoUbicacion= entityManager.find(PuntoUbicacion.class, id);
            if (puntoUbicacion!= null) {
                entityManager.remove(puntoUbicacion);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }
}
