package org.example.DAO;

import org.example.PersonaVulnerable.PersonaSituacionVulnerable;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class PersonaSituacionVulnerableDAO {
    private EntityManager entityManager;

    public PersonaSituacionVulnerableDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(PersonaSituacionVulnerable personaSituacionVulnerable) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(personaSituacionVulnerable);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void update(PersonaSituacionVulnerable personaSituacionVulnerable) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(personaSituacionVulnerable);
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
            PersonaSituacionVulnerable personaSituacionVulnerable = entityManager.find(PersonaSituacionVulnerable.class, id);
            if (personaSituacionVulnerable != null) {
                entityManager.remove(personaSituacionVulnerable);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    public PersonaSituacionVulnerable findById(long id) {
        return entityManager.find(PersonaSituacionVulnerable.class, id);
    }
}
