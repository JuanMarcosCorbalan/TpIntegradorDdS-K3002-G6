package org.example.DAO;

import org.example.Persona.Localidad;
import org.example.Persona.Pais;
import org.example.Utils.BDutils;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PruebasActualizacion {

    public static void main(String[] args){
        // Configuración del EntityManager
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");
        //EntityManager em = BDutils.getEntityManager();

        /*PaisDAO paisDAO = new PaisDAOImpl(em);

        Pais pais = new Pais("Brasil");

        paisDAO.save(pais);

        PaisDAO paisDAO = new PaisDAOImpl(em);

        long id = 2;
        Pais pais = em.find(Pais.class, id); // Busca el país con ID 2
        if (pais != null) {
            System.out.println("Nombre actual: " + pais.getNombre());
        } else {
            System.out.println("El país no existe");

        }

        pais.setNombre("Paraguay");
        paisDAO.update(pais);*/

        //LocalidadDAO localidadDAO = new LocalidadDAO(em);

        //Pais pais = new Pais("Argentina");
        //Localidad localidad = localidadDAO.findOrCreate("Almagro","Buenos Aires",pais);

    }
}
