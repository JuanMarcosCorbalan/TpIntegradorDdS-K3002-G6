package org.example.Utils;


import org.example.Persona.Ciudad;
import org.example.Persona.Domicilio;
import org.example.Persona.Localidad;
import org.example.Persona.Pais;
import org.example.PersonaVulnerable.PersonaSituacionVulnerable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class BDutils {

    private static final EntityManagerFactory factory;

    static {
        factory = Persistence.createEntityManagerFactory("simple-persistence-unit");
    }

    public static EntityManager getEntityManager() {
        EntityManager em = factory.createEntityManager();
        return em;
    }

    public static void comenzarTransaccion(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        if (!tx.isActive()) {
            tx.begin();
        }
    }

    public static void commit(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        if (tx.isActive()) {
            tx.commit();
        }
    }

    public static void rollback(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        if (tx.isActive()) {
            tx.rollback();
        }
    }

    public static void main(String[] args) {

        EntityManager em = getEntityManager();
        comenzarTransaccion(em);

        Pais pais = new Pais("argentina");
        Ciudad ciudad = new Ciudad("bsas",pais);
        Localidad localidad = new Localidad("almagro",ciudad);
        Domicilio domicilio = new Domicilio("323232","323233","yapeyu",localidad);
        PersonaSituacionVulnerable persona = new PersonaSituacionVulnerable("juan","corbalan",false,domicilio,4,null);

        em.persist(persona);

        commit(em);

        //em.persist(new Profesor());

        //BDUtils.commit(em);
    }
}
