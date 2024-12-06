package org.example.DAO;

import org.example.Persona.Ciudad;
import org.example.Persona.Localidad;
import org.example.Persona.Pais;
import org.example.Persona.Persona;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

public class CiudadDAO {

    private EntityManager entityManager;

    public CiudadDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Ciudad ciudad) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(ciudad);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void update(Ciudad ciudad) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(ciudad);
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
            Ciudad ciudad = entityManager.find(Ciudad.class, id);
            if (ciudad!= null) {
                entityManager.remove(ciudad);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    public Ciudad findOrCreate(String nombre_ciudad,String nombre_pais) {
        EntityTransaction transaction = entityManager.getTransaction();

        Ciudad ciudad = null;
        try {
            ciudad = entityManager
                    .createQuery("select c from Ciudad c join c.pais p" +
                            " where c.nombre = ?1 and p.nombre = ?2", Ciudad.class)
                    .setParameter(1, nombre_ciudad)
                    .setParameter(2, nombre_pais)
                    .getSingleResult();
        } catch (NoResultException e) {
            PaisDAO paisDAO = new PaisDAO(entityManager);
            Pais pais = paisDAO.findOrCreate(nombre_pais);
            ciudad = new Ciudad(nombre_ciudad, pais);
            //entityManager.persist(ciudad);
        }
        return ciudad;
    }


}
