package org.example;

import com.sendgrid.helpers.mail.objects.Email;
import org.example.Colaborador.Colaborador;
import org.example.Colaborador.Forma_colaborar;
import org.example.DAO.LocalidadDAO;
import org.example.Heladeras.Heladera;
import org.example.Heladeras.PuntoUbicacion;
import org.example.Persona.*;
import org.example.PersonaVulnerable.PersonaSituacionVulnerable;
import org.example.Sistema.Usuario;
import org.example.Utils.BDutils;
import org.example.Validadores_Sensores.Alerta;
import org.example.Validadores_Sensores.TipoAlerta;
import org.example.Validadores_Sensores.TipoIncidente;

import java.time.LocalDate;

import javax.persistence.EntityManager;

import java.util.List;

import static org.example.Colaborador.Forma_colaborar.*;
import static org.example.Persona.Tipo_documento.DNI;
import static org.example.Persona.Tipo_juridico.EMPRESA;

public class prueba_repo {

    public static void main(String[] args) {

        EntityManager em = BDutils.getEntityManager();
        BDutils.comenzarTransaccion(em);

        //5 personas vulnerables
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
        domicilio = new Domicilio("-34,637200","-58,384600","Montes de Oca 900",localidad);
        persona = new PersonaSituacionVulnerable("Luis","Quispe",false,domicilio,2,null);

        em.persist(persona);

        localidad = new Localidad("Boedo",ciudad);
        domicilio = new Domicilio("-34,624400","-58,413300","Av Independencia 3800",localidad);
        persona = new PersonaSituacionVulnerable("Tomas","Laurenzano",false,domicilio,2,null);

        em.persist(persona);

        localidad = new Localidad("Belgrano",ciudad);
        domicilio = new Domicilio("-34,561100","-58,448200","Av Cabildo 2700",localidad);
        persona = new PersonaSituacionVulnerable("Juan","Corbalan",true,null,1,null);


        em.persist(persona);

        //5 colaboradores
        localidad = new Localidad("Palermo",ciudad);
        domicilio = new Domicilio("-34,583200","-58,429600","Honduras 4700",localidad);
        Documento_identidad doc = new Documento_identidad("19723832",DNI);
        Medio_contacto wsp = new Whatsapp();
        wsp.setDetalle("3442654534");
        List<Medio_contacto> medios = List.of(wsp);
        Persona_fisica perColab = new Persona_fisica("Pablo","Diaz","17-04-1969",doc,medios,domicilio);
        wsp.setPersona(perColab);
        List<Forma_colaborar> forma = List.of(DONACION_DINERO,DONACION_VIANDAS,DISTRIBUCION_VIANDAS);
        Colaborador colab = new Colaborador(perColab,forma);
        Usuario user = new Usuario(perColab,"usuario1","pass123");
        em.persist(user);
        em.persist(colab);

        localidad = new Localidad("Parque patricios",ciudad);
        domicilio = new Domicilio("-34,639604","-58,401318","Zavaleta 200",localidad);
        doc = new Documento_identidad("447896578", DNI);
        wsp = new Whatsapp();
        wsp.setDetalle("1198744123");
        medios = List.of(wsp);
        perColab = new Persona_fisica("Juan","Meza","18-01-1986",doc,medios,domicilio);
        wsp.setPersona(perColab);
        forma = List.of(DONACION_VIANDAS);
        colab = new Colaborador(perColab,forma);
        user = new Usuario(perColab,"usuario2","123456");
        em.persist(user);
        em.persist(colab);

        localidad = new Localidad("Balvanera",ciudad);
        domicilio = new Domicilio("-34,583280","-58,429110","Azcuenaga 67",localidad);
        doc = new Documento_identidad("321456987",DNI);
        wsp = new Whatsapp();
        wsp.setDetalle("1151236987");
        medios = List.of(wsp);
        perColab = new Persona_fisica("Esteban", "Karagounis", "25-11-1988", doc, medios, domicilio);
        wsp.setPersona(perColab);
        forma = List.of(DISTRIBUCION_VIANDAS);
        colab = new Colaborador(perColab,forma);
        user = new Usuario(perColab,"usuario3","abc123");
        em.persist(user);
        em.persist(colab);
        //Domicilio domicilio, List<Medio_contacto> mediosContacto, String razon_social, Tipo_juridico tipo, String rubro


        localidad = new Localidad("Balvanera",ciudad);
        domicilio = new Domicilio("-34,609074","-58,401657","Larrea 88",localidad);
        Medio_contacto email = new Medio_contacto();
        email.setDetalle("autos@yahoo.com");
        List<Medio_contacto> empMedios = List.of(email);
        Persona_juridica empColab = new Persona_juridica(domicilio,empMedios,"Autos S.A",EMPRESA,"Automotriz");
        email.setPersona(empColab);
        List<Forma_colaborar> empForma = List.of(DONACION_DINERO,DONACION_VIANDAS);
        Colaborador emp = new Colaborador(empColab,empForma);
        user = new Usuario(empColab,"usuario4","qwerty");
        em.persist(user);
        em.persist(emp);

        // 3 heladeras
//1er heladera
        //long id = 2;
        //localidad = em.find(Localidad.class, id);
        //localidad = new Localidad("Balvanera",ciudad);
        LocalidadDAO ldao = new LocalidadDAO(em);
        localidad = ldao.findOrCreate("Almagro","Buenos Aires",pais);

        PuntoUbicacion ubi1 = new PuntoUbicacion("-34,583280","-58,429110","Av Medrano 900","UTN Medrano",localidad) ;

        LocalDate fechaEspecifica = LocalDate.of(2024,10,2);
        Heladera heladera = new Heladera ("H3L4D3R4M3DR4N0A1", ubi1, fechaEspecifica, 7, 2, null);

        Alerta alerta = new Alerta(heladera,TipoIncidente.ALERTA,TipoAlerta.ALERT_FRAUDE);
        heladera.getIncidentes().add(alerta);
        Alerta alerta2 = new Alerta(heladera,TipoIncidente.ALERTA,TipoAlerta.ALERT_FALLA_CONEX);
        heladera.getIncidentes().add(alerta2);

        em.persist(heladera);
        /*
        //Alerta(Heladera heladera, TipoIncidente tipoIncidente, TipoAlerta tipoAlerta)
//2da Heladera

      //  id = 2;
       // localidad = em.find(Localidad.class, id);


       // ubi1 = new PuntoUbicacion("-34,583281","-58,429111","Av Medrano 900","UTN Medrano",localidad) ;

        //fechaEspecifica = LocalDate.of(2024,10,2);
        //heladera = new Heladera("H3L4D3R4M3DR4N0A2", ubi1, fechaEspecifica, 7, 2, null);


       // em.persist(heladera);

//3era heladera

        id = 3;
        localidad = em.find(Localidad.class, id);


        ubi1 = new PuntoUbicacion("-34,583271","-58,429122","Balvanera 70","Calle",localidad) ;

        fechaEspecifica = LocalDate.of(2024,10,2);
        heladera = new Heladera("H3L4D3R4B4LV4N3R4", ubi1, fechaEspecifica, 7, 2, null);


        em.persist(heladera);
        */

        // 9 usuarios





        BDutils.commit(em);
    }
}
