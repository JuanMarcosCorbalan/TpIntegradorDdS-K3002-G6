package org.example.DAO;

import org.example.Persona.Ciudad;
import org.example.Persona.Pais;
import javax.persistence.*;

public class PaisDAO {

    private EntityManager entityManager;

    public PaisDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public void save(Pais pais) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(pais);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }


    public void update(Pais pais) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(pais);
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
            Pais usuario = entityManager.find(Pais.class, id);
            if (usuario != null) {
                entityManager.remove(usuario);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }


    public Pais findById(long id) {
        return entityManager.find(Pais.class, id);
    }

    public Pais findOrCreate(String nombre_pais) {
        EntityTransaction transaction = entityManager.getTransaction();

        Pais pais = null;
        try {
            pais = entityManager
                    .createQuery("select p from Pais p where p.nombre = ?1", Pais.class)
                    .setParameter(1, nombre_pais)
                    .getSingleResult();
        } catch (NoResultException e) {
            pais = new Pais(nombre_pais);
            //entityManager.persist(pais);
        }
        return pais;
    }
}
