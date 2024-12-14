package org.example.DAO;

import org.example.Colaborador.Colaborador;
import org.example.Formas_contribucion.Donacion_dinero;
import org.example.Persona.Ciudad;
import org.example.Persona.Localidad;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.util.List;

public class DonacionDineroDAO {
    private EntityManager entityManager;

    public DonacionDineroDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Donacion_dinero donacion_dinero) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(donacion_dinero);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void update(Donacion_dinero donacion_dinero) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(donacion_dinero);
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
            Donacion_dinero donacion_dinero = entityManager.find(Donacion_dinero.class, id);
            if (donacion_dinero != null) {
                entityManager.remove(donacion_dinero);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    public Donacion_dinero findById(long id) {
        return entityManager.find(Donacion_dinero.class, id);
    }

    public List<Donacion_dinero> findAllByColaborador(Colaborador colaborador) {
        List<Donacion_dinero> donacion_dineros;

        try{
        donacion_dineros = entityManager
                .createQuery("select d from Donacion_dinero d where d.colaborador = :colaborador", Donacion_dinero.class)
                .setParameter("colaborador", colaborador)
                .getResultList();
        } catch (NoResultException e) {
            donacion_dineros = null;
        }
        return donacion_dineros;
    }

}
