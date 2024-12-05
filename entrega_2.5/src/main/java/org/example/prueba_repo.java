package org.example;

import com.sendgrid.helpers.mail.objects.Email;
import org.example.Colaborador.Colaborador;
import org.example.Colaborador.Forma_colaborar;
import org.example.DAO.LocalidadDAO;
import org.example.Heladeras.Heladera;
import org.example.Heladeras.PuntoUbicacion;
import org.example.Persona.*;
import org.example.PersonaVulnerable.PersonaSituacionVulnerable;
import org.example.Personal.AreaCobertura;
import org.example.Personal.Tecnico;
import org.example.Personal.Visita;
import org.example.Sistema.Usuario;
import org.example.Utils.BDutils;
import org.example.Validadores_Sensores.Alerta;
import org.example.Validadores_Sensores.FallaTecnica;
import org.example.Validadores_Sensores.TipoAlerta;
import org.example.Validadores_Sensores.TipoIncidente;

import java.time.LocalDate;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.UUID;

import static org.example.Colaborador.Forma_colaborar.*;
import static org.example.Persona.Tipo_documento.DNI;
import static org.example.Persona.Tipo_juridico.EMPRESA;

public class prueba_repo {

    public static void main(String[] args) {


        EntityManager em = BDutils.getEntityManager();
        BDutils.comenzarTransaccion(em);
        LocalidadDAO ldao = new LocalidadDAO(em);

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

        localidad = new Localidad("Parque Patricios",ciudad);
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

        localidad = ldao.findOrCreate("Balvanera","Buenos Aires","Argentina");
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


        localidad = ldao.findOrCreate("Balvanera","Buenos Aires","Argentina");
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

        localidad = ldao.findOrCreate("Almagro","Buenos Aires","Argentina");
        PuntoUbicacion ubi1 = new PuntoUbicacion("-34,583280","-58,429110","Av Medrano 900","UTN Medrano",localidad) ;
        LocalDate fechaEspecifica = LocalDate.of(2024,10,2);
        String codigoUnico = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        Heladera heladera = new Heladera (codigoUnico, ubi1, fechaEspecifica, 7, 2, null);

        Alerta alerta = new Alerta(heladera,TipoIncidente.ALERTA,TipoAlerta.ALERT_FRAUDE);
        heladera.getIncidentes().add(alerta);
        Alerta alerta2 = new Alerta(heladera,TipoIncidente.ALERTA,TipoAlerta.ALERT_FALLA_CONEX);
        heladera.getIncidentes().add(alerta2);

        em.persist(heladera);

        //2da Heladera
        localidad = ldao.findOrCreate("San Telmo","Buenos Aires","Argentina");
        ubi1 = new PuntoUbicacion("-34,614900","-58,373800","Defensa 1000","Plaza Dorrego",localidad) ;
        fechaEspecifica = LocalDate.of(2024,7,15);
        codigoUnico = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        heladera = new Heladera(codigoUnico, ubi1, fechaEspecifica, 8, 3, null);
        em.persist(heladera);


        FallaTecnica falla = new FallaTecnica(colab, "titila el led rojo 5 veces cada 10 segundos", null, heladera);
        em.persist(falla);


        //3era heladera
        localidad = ldao.findOrCreate("Constitucion","Buenos Aires","Argentina");
        ubi1 = new PuntoUbicacion("-34,627700","-58,381400","Av Juan de Garay 1500","Estación Constitución",localidad) ;
        fechaEspecifica = LocalDate.of(2024,4,9);
        codigoUnico = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        heladera = new Heladera(codigoUnico, ubi1, fechaEspecifica, 7, 2, null);
        alerta = new Alerta(heladera,TipoIncidente.ALERTA,TipoAlerta.ALERT_FRAUDE);
        heladera.getIncidentes().add(alerta);

        em.persist(heladera);

        // faltan contibuciones,visitas(hay una creada)),incidentes,tarjetas,solicitudes,mensajes,retirovianda
//


        domicilio = new Domicilio("-34,583280","-58,429110","Azcuenaga 67",localidad);
        doc = new Documento_identidad("251456987",DNI);
        email.setDetalle("elsztain@aol.com");
        empMedios = List.of(email);
        // public AreaCobertura(String latitud, String longitud, String radio)
        AreaCobertura area = new AreaCobertura("-34,627711","-58,381422","25");
        Tecnico tecnico = new Tecnico("Edgardo","Elsztain","25-01-1962",doc, empMedios, domicilio, area);
        em.persist(tecnico);

        localidad = ldao.findOrCreate("Caballito","Buenos Aires","Argentina");
        domicilio = new Domicilio("-34,583250","-58,429140","Bilbao 97",localidad);
        doc = new Documento_identidad("341456777",DNI);
        email.setDetalle("reinoso@gmail.com");
        empMedios = List.of(email);
        // public AreaCobertura(String latitud, String longitud, String radio)
        area = new AreaCobertura("-34,623611", "-58,381142", "8");
        tecnico = new Tecnico("Marcos", "Reinoso", "21-12-1977", doc, empMedios, domicilio, area);
        em.persist(tecnico);
//Visita
        Visita visita = new Visita(falla, heladera, "problema led rojo", false, null);
        em.persist(visita);



        BDutils.commit(em);
    }
}
