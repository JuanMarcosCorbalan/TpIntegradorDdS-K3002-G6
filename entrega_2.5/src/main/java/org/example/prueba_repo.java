package org.example;

import org.example.Colaborador.Colaborador;
import org.example.Colaborador.Forma_colaborar;
import org.example.Persona.*;
import org.example.PersonaVulnerable.PersonaSituacionVulnerable;
import org.example.Utils.BDutils;

import javax.persistence.EntityManager;

import java.util.List;

import static org.example.Colaborador.Forma_colaborar.*;
import static org.example.Persona.Tipo_documento.DNI;

public class prueba_repo {

    public static void main(String[] args) {

        EntityManager em = BDutils.getEntityManager();
        BDutils.comenzarTransaccion(em);

        /*
        Pais pais = new Pais("Argentina");
        Ciudad ciudad = new Ciudad("Buenos Aires",pais);
        Localidad localidad = new Localidad("Caballito",ciudad);
        Domicilio domicilio = new Domicilio("-87,322","-145,29","Av Rivadavia 5400",localidad);
        PersonaSituacionVulnerable persona = new PersonaSituacionVulnerable("Joaquin","Bergara",false,domicilio,3,null);

        em.persist(persona);

        localidad = new Localidad("Almagro",ciudad);
        domicilio = new Domicilio("-64,322","45,29","Av Medrano 653",localidad);
        persona = new PersonaSituacionVulnerable("Robert","Rodas",false,domicilio,0,null);

        em.persist(persona);

        localidad = new Localidad("Balvanera",ciudad);
        domicilio = new Domicilio("-34,608100","-58,402900","Pasco 1200",localidad);
        persona = new PersonaSituacionVulnerable("Roger","Ramos",false,domicilio,3,null);

        em.persist(persona);

        localidad = new Localidad("Barracas",ciudad);
        domicilio = new Domicilio("-34.637200","-58.384600","Montes de Oca 900",localidad);
        persona = new PersonaSituacionVulnerable("Luis","Quispe",false,domicilio,2,null);

        em.persist(persona);

        localidad = new Localidad("Boedo",ciudad);
        domicilio = new Domicilio("-34.624400","-58.413300","Av Independencia 3800",localidad);
        persona = new PersonaSituacionVulnerable("Tomas","Laurenzano",false,domicilio,2,null);

        em.persist(persona);

        localidad = new Localidad("Belgrano",ciudad);
        domicilio = new Domicilio("-34.561100","-58.448200","Av Cabildo 2700",localidad);
        persona = new PersonaSituacionVulnerable("Juan","Corbalan",true,null,1,null);

        em.persist(persona);*/

        Pais pais = new Pais("Argentina");
        Ciudad ciudad = new Ciudad("Buenos Aires",pais);
        Localidad localidad = new Localidad("Palermo",ciudad);
        Domicilio domicilio = new Domicilio("-34.583200","-58.429600","Honduras 4700",localidad);
        Documento_identidad doc = new Documento_identidad("19723832",DNI);
        Medio_contacto wsp = new Whatsapp();
        wsp.setDetalle("3442654534");
        List<Medio_contacto> medios = List.of(wsp);
        Persona_fisica perColab = new Persona_fisica("Pablo","Diaz","17-04-1969",doc,medios,domicilio);
        wsp.setPersona(perColab);
        List<Forma_colaborar> forma = List.of(DONACION_DINERO,DONACION_VIANDAS,DISTRIBUCION_VIANDAS);
        Colaborador colab = new Colaborador(perColab,forma);

        em.persist(colab);
        BDutils.commit(em);
    }
}
