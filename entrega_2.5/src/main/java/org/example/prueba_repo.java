package org.example;

import org.example.Persona.Ciudad;
import org.example.Persona.Domicilio;
import org.example.Persona.Localidad;
import org.example.Persona.Pais;
import org.example.PersonaVulnerable.PersonaSituacionVulnerable;
import org.example.Utils.BDutils;

import javax.persistence.EntityManager;

public class prueba_repo {

    public static void main(String[] args) {

        EntityManager em = BDutils.getEntityManager();
        //em.getTransaction().begin();
        BDutils.comenzarTransaccion(em);

        System.out.println("creando objetos");

        Pais pais = new Pais("argentina");
        Ciudad ciudad = new Ciudad("bsas",pais);
        Localidad localidad = new Localidad("almagro",ciudad);
        Domicilio domicilio = new Domicilio("323232","323233","yapeyu",localidad);
        //PersonaSituacionVulnerable persona = new PersonaSituacionVulnerable("juan","corbalan",false,domicilio,4,null);

        em.persist(localidad);

        //em.getTransaction().commit(); // Asegúrate de confirmar la transacción al final
        //em.close();


        BDutils.commit(em);
    }
}
