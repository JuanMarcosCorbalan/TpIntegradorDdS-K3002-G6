package org.example.DAO;

import org.example.Persona.Ciudad;
import org.example.Persona.Localidad;
import org.example.Persona.Pais;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

public class CiudadDAO {

    private EntityManager entityManager;

    public CiudadDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Ciudad findOrCreate(String nombre_ciudad,String nombre_pais) {
        EntityTransaction transaction = entityManager.getTransaction();

        Ciudad ciudad = null;
        try {
            ciudad = entityManager
                    .createQuery("select c from Ciudad c join c.pais p" +
                            " where c.nombre = ?1 and p.nombre = ?2", Ciudad.class)
                    .setParameter(1, nombre_ciudad)
                    .setParameter(2, nombre_pais)
                    .getSingleResult();
        } catch (NoResultException e) {
            PaisDAO paisDAO = new PaisDAO(entityManager);
            Pais pais = paisDAO.findOrCreate(nombre_pais);
            ciudad = new Ciudad(nombre_ciudad, pais);
            //entityManager.persist(ciudad);
        }
        return ciudad;
    }


}
