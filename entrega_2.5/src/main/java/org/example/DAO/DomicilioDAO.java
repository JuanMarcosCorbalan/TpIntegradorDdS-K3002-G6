package org.example.DAO;

import org.example.Persona.Ciudad;
import org.example.Persona.Domicilio;
import org.example.Persona.Localidad;
import org.example.Persona.Pais;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

public class DomicilioDAO {
    private EntityManager entityManager;

    public DomicilioDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Domicilio domicilio) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(domicilio);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void update(Domicilio domicilio) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(domicilio);
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
            Domicilio domicilio= entityManager.find(Domicilio.class, id);
            if (domicilio!= null) {
                entityManager.remove(domicilio);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }


    public Domicilio findOrCreate(String nombre_domicilio,String nombre_localidad ,String nombre_ciudad, String nombre_pais) {

        Domicilio domicilio = null;
        try {
            domicilio = entityManager
                    .createQuery("select d from Domicilio d join d.localidad l " +
                            "join l.ciudad c join c.pais p where" +
                            " d.direccion = ?1 and l.nombre = ?2" +
                            "and c.nombre = ?3 and p.nombre = ?4 ", Domicilio.class)
                    .setParameter(1, nombre_domicilio)
                    .setParameter(2, nombre_ciudad)
                    .setParameter(3, nombre_ciudad)
                    .setParameter(4, nombre_ciudad)
                    .getSingleResult();
        } catch (NoResultException e) {
            LocalidadDAO lDAO = new LocalidadDAO(entityManager);
            Localidad localidad = lDAO.findOrCreate(nombre_localidad,nombre_ciudad,nombre_pais);
            domicilio = new Domicilio(null,null,nombre_domicilio,localidad);
            //entityManager.persist(domicilio);
        }
        return domicilio;
    }
}
