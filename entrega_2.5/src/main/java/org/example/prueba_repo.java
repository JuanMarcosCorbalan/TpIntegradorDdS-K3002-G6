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
        Localidad localidad = new Localidad("hurlingham",ciudad);
        Domicilio domicilio = new Domicilio("-321.322","-199.29","tucuman 100",localidad);
        PersonaSituacionVulnerable persona = new PersonaSituacionVulnerable("tomas","laurenzano",false,domicilio,3,null);

        em.persist(persona);

/*
        pais.setNombre("argentina");
        ciudad.setNombre("bsas");
        ciudad.setPais(pais);
        localidad.setNombre("Samore");
        localidad.setCiudad(ciudad);
        domicilio.setDireccion("por_campus");
        domicilio.setLongitud("321.322");
        domicilio.setLatitud("-199.29");
        persona = new PersonaSituacionVulnerable("robert","rodas",false,domicilio,2,null);

        em.persist(persona);

        pais.setNombre("argentina");
        ciudad.setNombre("bsas");
        ciudad.setPais(pais);
        localidad.setNombre("liniers");
        localidad.setCiudad(ciudad);
        domicilio.setDireccion("el_parque");
        domicilio.setLongitud("151.322");
        domicilio.setLatitud("-199.29");
        persona = new PersonaSituacionVulnerable("luis","quispe",true,domicilio,0,null);

        em.persist(persona);

        //em.getTransaction().commit(); // Asegúrate de confirmar la transacción al final
        //em.close();

*/
        BDutils.commit(em);
    }
}
