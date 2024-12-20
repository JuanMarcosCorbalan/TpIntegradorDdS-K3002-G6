package org.example.DAO;
import java.util.List;

import org.example.Colaborador.Colaborador;
import org.example.Persona.Pais;
import org.example.Ofertas.Oferta;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

public class OfertaDAO {

    private EntityManager entityManager;

    public OfertaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Oferta oferta) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(oferta);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }


    public void update(Oferta oferta) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(oferta);
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
            Oferta oferta = entityManager.find(Oferta.class, id);
            if (oferta != null) {
                entityManager.remove(oferta);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    public List<Oferta> findAllActive() {
        try {
            return entityManager.createQuery("SELECT o FROM Oferta o where o.ofertaActiva = 1", Oferta.class).getResultList();
        } catch (Exception e) {
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public List<Oferta> findAll() {
        try {
            return entityManager.createQuery("SELECT o FROM Oferta o", Oferta.class).getResultList();
        } catch (Exception e) {
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    public List<Oferta> findAllByColaborador(Colaborador colaborador) {
        List<Oferta> oferta;
        try {
            oferta = entityManager
                    .createQuery("select d from Oferta d where d.colaborador = :colaborador", Oferta.class)
                    .setParameter("colaborador", colaborador)
                    .getResultList();
        } catch (NoResultException e) {
            oferta = null;
        }
        return oferta;
    }
    public Oferta findById(Long id) {
        return entityManager.find(Oferta.class, id);
    }

    public Oferta findByNombre(String nombre) {
        return entityManager.find(Oferta.class, nombre);
    }


}
