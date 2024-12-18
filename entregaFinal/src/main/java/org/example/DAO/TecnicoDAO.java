package org.example.DAO;

import org.example.Colaborador.Colaborador;
import org.example.DTO.VisitaDTO;
import org.example.Personal.Tecnico;
import org.example.Personal.Visita;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public List<Tecnico> findAlltecnicos() {
        // Query para obtener todos los incidentes que sean de tipo 'FALLA_TECNICA'
        String query = "SELECT i FROM Tecnico i";


        return entityManager.createQuery(query, Tecnico.class)
                .getResultList();
    }

    public Tecnico findById(long id) {
        return entityManager.find(Tecnico.class, id);
    }
}
