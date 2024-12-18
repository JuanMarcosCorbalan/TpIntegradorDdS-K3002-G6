package org.example.DAO;

import org.example.Colaborador.Colaborador;
import org.example.Heladeras.Heladera;
import org.example.Heladeras.HeladeraDTO2;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

public class ColaboradorDAO {
    private EntityManager entityManager;

    public ColaboradorDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Colaborador colaborador) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(colaborador);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void update(Colaborador colaborador) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(colaborador);
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
            Colaborador colaborador = entityManager.find(Colaborador.class, id);
            if (colaborador != null) {
                entityManager.remove(colaborador);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }


    }

    public Colaborador findById(long id) {
        return entityManager.find(Colaborador.class, id);
    }

}
