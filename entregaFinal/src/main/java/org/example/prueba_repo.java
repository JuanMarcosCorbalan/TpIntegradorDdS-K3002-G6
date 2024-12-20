package org.example;

import com.sendgrid.helpers.mail.objects.Email;
import org.example.Colaborador.Colaborador;
import org.example.Colaborador.Forma_colaborar;
import org.example.DAO.LocalidadDAO;
import org.example.Heladeras.Heladera;
import org.example.Heladeras.PuntoUbicacion;
import org.example.Ofertas.Oferta;
import org.example.Persona.*;
import org.example.PersonaVulnerable.PersonaSituacionVulnerable;
import org.example.Personal.AreaCobertura;
import org.example.Personal.Tecnico;
import org.example.Personal.Visita;
import org.example.Sistema.Usuario;
import org.example.Suscripcion.AdministradorSuscripciones;
import org.example.Tarjetas.TarjetaColaborador;
import org.example.Utils.BDutils;
import org.example.Validadores_Sensores.Alerta;
import org.example.Validadores_Sensores.FallaTecnica;
import org.example.Validadores_Sensores.TipoAlerta;
import org.example.Validadores_Sensores.TipoIncidente;
import org.mindrot.jbcrypt.BCrypt;

import java.time.LocalDate;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.example.Colaborador.Forma_colaborar.*;
import static org.example.Persona.Tipo_documento.DNI;
import static org.example.Persona.Tipo_juridico.EMPRESA;

public class prueba_repo {

    public static void ejecutar() {


        EntityManager em = BDutils.getEntityManager();
        BDutils.comenzarTransaccion(em);
        LocalidadDAO ldao = new LocalidadDAO(em);

        //5 personas vulnerables
        Pais pais = new Pais("Argentina");
        Ciudad ciudad = new Ciudad("Cdad. Autónoma de Buenos Aires",pais);
        Localidad localidad = new Localidad("Caballito",ciudad);
        Domicilio domicilio = new Domicilio("-87,322","-145,29","Av. Rivadavia 5400",localidad);
        PersonaSituacionVulnerable persona = new PersonaSituacionVulnerable("Joaquin","Bergara",false,domicilio,3,null);

        em.persist(persona);

        localidad = new Localidad("Almagro",ciudad);
        domicilio = new Domicilio("-64,322","45,29","Av. Medrano 653",localidad);
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
        domicilio = new Domicilio("-34,624400","-58,413300","Av. Independencia 3800",localidad);
        persona = new PersonaSituacionVulnerable("Tomas","Laurenzano",false,domicilio,2,null);

        em.persist(persona);

        localidad = new Localidad("Belgrano",ciudad);
        domicilio = new Domicilio("-34,561100","-58,448200","Av. Cabildo 2700",localidad);
        persona = new PersonaSituacionVulnerable("Juan","Corbalan",true,null,1,null);


        em.persist(persona);

        //5 colaboradores
        localidad = new Localidad("Palermo",ciudad);
        domicilio = new Domicilio("-34,583200","-58,429600","Honduras 4700",localidad);
        Documento_identidad doc = new Documento_identidad("19723832",DNI);
        Whatsapp wsp = new Whatsapp();
        wsp.setNumero("3442654534");
        List<Medio_contacto> medios = List.of(wsp);
        Persona_fisica perColab = new Persona_fisica("Pablo","Diaz","17-04-1969",doc,medios,domicilio);
        wsp.setPersona(perColab);
        List<Forma_colaborar> forma = List.of(DONACION_DINERO,DONACION_VIANDAS,DISTRIBUCION_VIANDAS);
        Colaborador colab = new Colaborador(perColab,forma);
        String contra = "pass123";
        Usuario user = new Usuario(perColab,"usuario1",BCrypt.hashpw(contra, BCrypt.gensalt()));
        TarjetaColaborador tarj = new TarjetaColaborador(colab);
        em.persist(user);
        em.persist(colab);
        em.persist(tarj);

        localidad = new Localidad("Parque Patricios",ciudad);
        domicilio = new Domicilio("-34,639604","-58,401318","Zavaleta 200",localidad);
        doc = new Documento_identidad("447896578", DNI);
        wsp = new Whatsapp();
        wsp.setNumero("1198744123");
        medios = List.of(wsp);
        perColab = new Persona_fisica("Juan","Meza","18-01-1986",doc,medios,domicilio);
        wsp.setPersona(perColab);
        forma = List.of(DONACION_VIANDAS);
        colab = new Colaborador(perColab,forma);
        contra = "123456";
        user = new Usuario(perColab,"usuario2",BCrypt.hashpw(contra, BCrypt.gensalt()));
        tarj = new TarjetaColaborador(colab);
        em.persist(user);
        em.persist(colab);
        em.persist(tarj);

        localidad = ldao.findOrCreate("Balvanera","Cdad. Autónoma de Buenos Aires","Argentina");
        domicilio = new Domicilio("-34,583280","-58,429110","Azcuenaga 67",localidad);
        doc = new Documento_identidad("321456987",DNI);
        wsp = new Whatsapp();
        wsp.setNumero("1151236987");
        medios = List.of(wsp);
        perColab = new Persona_fisica("Esteban", "Karagounis", "25-11-1988", doc, medios, domicilio);
        wsp.setPersona(perColab);
        forma = List.of(DISTRIBUCION_VIANDAS);
        colab = new Colaborador(perColab,forma);
        contra = "abc123";
        user = new Usuario(perColab,"usuario3",BCrypt.hashpw(contra, BCrypt.gensalt()));
        tarj = new TarjetaColaborador(colab);
        em.persist(user);
        em.persist(colab);
        em.persist(tarj);
        //Domicilio domicilio, List<Medio_contacto> mediosContacto, String razon_social, Tipo_juridico tipo, String rubro


        localidad = ldao.findOrCreate("Balvanera","Cdad. Autónoma de Buenos Aires","Argentina");
        domicilio = new Domicilio("-34,609074","-58,401657","Larrea 88",localidad);
        Mail email = new Mail();
        email.setEmail("autos@yahoo.com");
        List<Medio_contacto> empMedios = List.of(email);
        Persona_juridica empColab = new Persona_juridica(domicilio,empMedios,"Autos S.A",EMPRESA,"Automotriz");
        email.setPersona(empColab);
        List<Forma_colaborar> empForma = List.of(DONACION_DINERO,DONACION_VIANDAS);
        Colaborador emp = new Colaborador(empColab,empForma);
        contra = "qwerty";
        user = new Usuario(empColab,"usuario4",BCrypt.hashpw(contra, BCrypt.gensalt()));
        em.persist(user);
        em.persist(emp);

        // 3 heladeras
        //1er heladera chequear maximo cantidades

        localidad = ldao.findOrCreate("Almagro","Cdad. Autónoma de Buenos Aires","Argentina");
        PuntoUbicacion ubi1 = new PuntoUbicacion("-34.59949215153663","-58.420667586506816","Av. Medrano 900","UTN Medrano",localidad) ;
        LocalDate fechaEspecifica = LocalDate.of(2024,10,2);
        Heladera heladera = new Heladera (ubi1, fechaEspecifica, 7, 2, colab,150);
        heladera.activar();


        Alerta alerta = new Alerta(heladera,TipoIncidente.ALERTA,TipoAlerta.ALERT_FRAUDE);
        heladera.getIncidentes().add(alerta);
        Alerta alerta2 = new Alerta(heladera,TipoIncidente.ALERTA,TipoAlerta.ALERT_FALLA_CONEX);
        heladera.getIncidentes().add(alerta2);

        heladera.setPuntoUbicacion(ubi1);
        ubi1.aniadirHeladera(heladera);
        em.persist(ubi1);

        //2da Heladera
        localidad = ldao.findOrCreate("San Telmo","Cdad. Autónoma de Buenos Aires","Argentina");
        ubi1 = new PuntoUbicacion("-34.61931090520509","-58.37143721859889","Defensa 1000","Plaza Dorrego",localidad) ;
        fechaEspecifica = LocalDate.of(2024,7,15);
        heladera = new Heladera( ubi1, fechaEspecifica, 8, 3, emp,150);
        heladera.activar();
        heladera.setPuntoUbicacion(ubi1);
        ubi1.aniadirHeladera(heladera);
        em.persist(ubi1);





        //3era heladera
        localidad = ldao.findOrCreate("Constitucion","Cdad. Autónoma de Buenos Aires","Argentina");
        ubi1 = new PuntoUbicacion("-34.62681084639811","-58.38598781674918","Av. Juan de Garay 1500","Estación Constitución",localidad) ;
        fechaEspecifica = LocalDate.of(2024,4,9);
        heladera = new Heladera(ubi1, fechaEspecifica, 7, 2, null,150);
        alerta = new Alerta(heladera,TipoIncidente.ALERTA,TipoAlerta.ALERT_FRAUDE);
        heladera.getIncidentes().add(alerta);
        heladera.activar();
        heladera.setPuntoUbicacion(ubi1);
        ubi1.aniadirHeladera(heladera);
        em.persist(ubi1);

        // faltan contibuciones,visitas(hay una creada)),tarjetas,solicitudes,mensajes,retirovianda
//


        domicilio = new Domicilio("-34,583280","-58,429110","Azcuenaga 67",localidad);
        doc = new Documento_identidad("251456987",DNI);
        email.setEmail("elsztain@aol.com");
        empMedios = List.of(email);
        // public AreaCobertura(String latitud, String longitud, String radio)
        AreaCobertura area = new AreaCobertura("-34.627711","-58.381422","25");
        Tecnico tecnico = new Tecnico("Edgardo","Elsztain","25-01-1962",doc, empMedios, domicilio, area);
        contra = "tec123";
        Usuario usuario_tecnico1 = new Usuario(tecnico.getPersona(),"tecnico1",BCrypt.hashpw(contra, BCrypt.gensalt()));
        em.persist(usuario_tecnico1);
        em.persist(tecnico);

        localidad = ldao.findOrCreate("Caballito","Cdad. Autónoma de Buenos Aires","Argentina");
        FallaTecnica falla = new FallaTecnica(colab, "titila el led rojo 5 veces cada 10 segundos", null, heladera);
        List<Tecnico> tecnicos = new ArrayList<>();
        tecnicos.add(tecnico);
        falla.asignarTecnico(heladera.getPuntoUbicacion(),tecnicos);
        em.persist(falla);

        localidad = ldao.findOrCreate("Caballito","Buenos Aires","Argentina");
        domicilio = new Domicilio("-34.583250","-58.429140","Bilbao 97",localidad);
        doc = new Documento_identidad("341456777",DNI);
        email.setEmail("reinoso@gmail.com");
        empMedios = List.of(email);
        // public AreaCobertura(String latitud, String longitud, String radio)
        area = new AreaCobertura("-34.623611", "-58.381142", "8");
        tecnico = new Tecnico("Marcos", "Reinoso", "21-12-1977", doc, empMedios, domicilio, area);
        contra = "tect123";
        Usuario usuario_tecnico2 = new Usuario(tecnico.getPersona(),"tecnico2",BCrypt.hashpw(contra, BCrypt.gensalt()));
        em.persist(usuario_tecnico2);
        em.persist(tecnico);


//Visita
        Visita visita = new Visita(falla, heladera, "problema led rojo", false, null,falla.getTecnicoAsignado(),LocalDate.now());
        em.persist(visita);
//oferta public Oferta(String nombre, Integer puntosNecesarios, Integer cantInstancias)
        Oferta oferta = new Oferta("Descuento 20% Jumbo", 25000, 80,emp, "/uploads/Easy-Logo.svg.png");
        em.persist(oferta);
        oferta = new Oferta("Descuento 5% YPF", 25000, 45,emp, "/uploads/1310.png");
        em.persist(oferta);
        oferta = new Oferta("Descuento 15% easy", 8000, 140,emp, "/uploads/Logo_Jumbo_Cencosud.png");
        em.persist(oferta);

        // Tarjetas
        



        BDutils.commit(em);
        em.close();
    }
}
