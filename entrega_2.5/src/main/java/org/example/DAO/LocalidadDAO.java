package org.example.DAO;

import org.example.Persona.Ciudad;
import org.example.Persona.Localidad;
import org.example.Persona.Pais;
import org.example.Utils.BDutils;
import org.example.Validadores_Sensores.Incidente;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class LocalidadDAO {
///*
    private EntityManager entityManager;

    public LocalidadDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Localidad findOrCreate(String nombre_localidad, String nombre_ciudad, Pais pais) {
        EntityTransaction transaction = entityManager.getTransaction();

        Localidad localidad = entityManager
                    .createQuery("select l from Localidad l join l.ciudad c" +
                            " where l.nombre = ?1 and c.nombre = ?2", Localidad.class)
                    .setParameter(1, nombre_localidad)
                    .setParameter(2, nombre_ciudad)
                    .getSingleResult();
        if(localidad != null){
          //  System.out.println("Localidad: " + localidad.getNombre());
            return localidad;
        }
        Ciudad ciudad = new Ciudad(nombre_ciudad,pais);
        localidad = new Localidad(nombre_localidad,ciudad);
        entityManager.persist(localidad);
        //System.out.println("Localidad: no existia");
        return localidad;

    }
    public static void main(String[] args){
        EntityManager em = BDutils.getEntityManager();
        LocalidadDAO localidadDAO = new LocalidadDAO(em);

        Pais pais = new Pais("Argentina");
        //Localidad localidad = findOrCreate("Almagro","Buenos Aires",pais);
    }
//*/
}
