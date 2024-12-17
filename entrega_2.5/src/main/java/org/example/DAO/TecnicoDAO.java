package org.example.DAO;

import org.example.Colaborador.Colaborador;
import org.example.Personal.Tecnico;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class TecnicoDAO {
    private EntityManager entityManager;

    public TecnicoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Tecnico tecnico) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(tecnico);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void update(Tecnico tecnico) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(tecnico);
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
            Tecnico tecnico = entityManager.find(Tecnico.class, id);
            if (tecnico != null) {
                entityManager.remove(tecnico);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    public Tecnico findById(long id) {
        return entityManager.find(Tecnico.class, id);
    }
}
