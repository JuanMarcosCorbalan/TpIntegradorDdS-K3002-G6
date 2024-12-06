package org.example.DAO;

import org.example.Persona.Ciudad;
import org.example.Persona.Domicilio;
import org.example.Persona.Localidad;
import org.example.Persona.Pais;
import org.example.Utils.BDutils;
import org.example.Validadores_Sensores.Incidente;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.util.List;

public class LocalidadDAO {
///*
    private EntityManager entityManager;

    public LocalidadDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Localidad findOrCreate(String nombre_localidad, String nombre_ciudad, String nombre_pais) {
        EntityTransaction transaction = entityManager.getTransaction();

        Localidad localidad = null;
        try {
            localidad = entityManager
                    .createQuery("select l from Localidad l join l.ciudad c " +
                            "join c.pais p where " +
                            " l.nombre = ?1 and c.nombre = ?2 and p.nombre = ?3", Localidad.class)
                    .setParameter(1, nombre_localidad)
                    .setParameter(2, nombre_ciudad)
                    .setParameter(3, nombre_pais)
                    .getSingleResult();
        } catch (NoResultException e) {
            CiudadDAO ciudadDAO = new CiudadDAO(entityManager);
            Ciudad ciudad = ciudadDAO.findOrCreate(nombre_ciudad,nombre_pais);
            localidad = new Localidad(nombre_localidad,ciudad);
           // entityManager.persist(localidad);
        }
        return localidad;
    }

    public void save(Localidad localidad) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(localidad);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void update(Localidad localidad) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(localidad);
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
            Localidad localidad= entityManager.find(Localidad.class, id);
            if (localidad!= null) {
                entityManager.remove(localidad);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }


}
