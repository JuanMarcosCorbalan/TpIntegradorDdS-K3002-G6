package org.example.DAO;

import org.example.Formas_contribucion.Contribucion;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.example.Persona.*;

public class PersonaDAO {

    private EntityManager entityManager;

    public PersonaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Persona persona) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(persona);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void update(Persona persona) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(persona);
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
            Persona persona = entityManager.find(Persona.class, id);
            if (persona != null) {
                entityManager.remove(persona);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    public Persona findById(long id) {
        return entityManager.find(Persona.class, id);
    }
}
