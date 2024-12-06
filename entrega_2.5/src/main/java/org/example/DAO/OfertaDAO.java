package org.example.DAO;
import java.util.List;
import org.example.Persona.Pais;
import org.example.Ofertas.Oferta;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

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

    public List<Oferta> findAll() {


        try {
            return entityManager.createQuery("SELECT o FROM Oferta o", Oferta.class).getResultList();
        } catch (Exception e) {
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
