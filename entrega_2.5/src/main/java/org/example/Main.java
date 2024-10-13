package org.example;

import org.example.Colaborador.Colaborador;
import org.example.Colaborador.Forma_colaborar;
import org.example.Heladeras.Heladera;
import org.example.Persona.*;
import org.example.Formas_contribucion.Contribucion;
import org.example.PersonaVulnerable.PersonaSituacionVulnerable;
import org.example.Personal.AreaCobertura;
import org.example.Personal.Tecnico;
import org.example.Colaborador.ControladoresColaborador.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinMustache;

public class Main {

    static List<Colaborador> colaboradores = new ArrayList<Colaborador>();
    List<PersonaSituacionVulnerable> personasVulnerables = new ArrayList<PersonaSituacionVulnerable>();
    public List<Tecnico> tecnicos = new ArrayList<Tecnico>();

    public static void main(String[] args) {
        List<Colaborador> colaboradoresNuevo = colaboradores;
        InstanciacionClases instanciacion = new InstanciacionClases();
        System.out.println("Hello world!");


        Javalin app = Javalin.create(javalinConfig -> {
                            javalinConfig.plugins.enableCors(cors -> {
                                cors.add(it -> it.anyHost());
                            }); // para poder hacer requests de un dominio a otro

                            javalinConfig.staticFiles.add("/paginaWebColaboracionHeladeras"); //recursos estaticos (HTML, CSS, JS, IMG)
                            //JavalinMustache.init();  // Configurar Mustache
                        }

                )
                .get("/", ctx -> ctx.result("Hello World"))
                .start(8081);

        app.get("/donacionVianda", ctx->{
            ctx.render("/paginaWebColaboracionHeladeras/donacionVianda/html/donacionVianda.mustache");
        });
        // login para guardar al colaborador
        app.post("/login", ctx -> {
            // Validar credenciales del colaborador
            Colaborador colaborador = ColaboradorController.login(ctx.formParam("email"), ctx.formParam("password"));

            if (colaborador != null) {
                // Guardar al colaborador en la sesión
                ctx.sessionAttribute("colaborador", colaborador);
                ctx.result("Login exitoso");
            } else {
                ctx.result("Credenciales incorrectas");
            }
        });
        // Ruta para manejar la solicitud POST de realizar donación
        app.post("/solicitarDonacionVianda", ctx -> {
            // Obtener al colaborador desde la sesión
            Colaborador colaborador = ctx.sessionAttribute("colaborador");
            if (colaborador != null) {
                // Recibir los datos del formulario
                String nombre = ctx.formParam("inputComida");
                LocalDate cantidad = LocalDate.parse(ctx.formParam("inputFechaVencimiento"));
                Heladera heladera = new Heladera("heladera0Prueba");
                String calorias = ctx.formParam("inputCalorias");
                String pesoGramos = ctx.formParam("inputPeso");
                // Llamar a la lógica de backend
                SolicitarDonacionViandaHandler.realizarDonacion(colaborador, nombre, cantidad, heladera, calorias, pesoGramos);

                // Enviar una respuesta de confirmación
                ctx.result("Donación realizada con éxito.");
            } else {
                // Si el colaborador no está en la sesión, redirigir al login o mostrar error
                ctx.status(401).result("Por favor inicia sesión para realizar una donación.");
            }
        });


        instanciacion.crearColaboradores(colaboradores);
        instanciacion.migrarColaboradores(colaboradores);

        for (Colaborador colaborador : colaboradores) {
            Persona persona = colaborador.getPersona_colaboradora();
            if(persona instanceof Persona_fisica personaFisicaExistente){
                System.out.println("DNI: " + personaFisicaExistente.getDocumento_identidad().getNumeroDocumento());
                String nombre = personaFisicaExistente.getNombre();
                List<Contribucion> contribuciones = colaborador.getContribuciones();
                if (!contribuciones.isEmpty()) System.out.println("Colaboraciones: ");
                else System.out.println("No tiene colaboraciones realizadas");
                for(Contribucion contribucion : contribuciones) {
                    System.out.println(contribucion.getFecha_contribucion());
                }
            }

        }

    }

    public void dar_alta_colaborador_fisico(String nombre, String apellido, String fechaNacimiento, Tipo_documento tipoDoc, String numeroDocumento, Medio_contacto[] medios,String latDom,String longDom,String direccion, Ciudad ciudad,Pais pais , Forma_colaborar[] formas)
    {
        Documento_identidad nuevo_documento = new Documento_identidad(numeroDocumento,tipoDoc);
        Domicilio nuevo_domicilio = new Domicilio(latDom,longDom,direccion,ciudad,pais);
        Persona_fisica nueva_persona = new Persona_fisica(nombre,apellido,fechaNacimiento,nuevo_documento,medios,nuevo_domicilio);
        Colaborador colaborador = new Colaborador(nueva_persona,formas);
        colaboradores.add(colaborador);
    }
    public void dar_alta_colaborador_juridico(String razonSocial, Tipo_juridico tipo, String rubro, Medio_contacto[] medios,String latDom,String longDom,String direccion, Ciudad ciudad,Pais pais , Forma_colaborar[] formas)
    {
        Domicilio nuevo_domicilio = new Domicilio(latDom,longDom,direccion,ciudad,pais);
        Persona_juridica nueva_persona = new Persona_juridica(nuevo_domicilio,medios,razonSocial,tipo,rubro);
        Colaborador colaborador = new Colaborador(nueva_persona,formas);
        colaboradores.add(colaborador);
    }
    void darBajaColaborador(Colaborador colaborador){
        colaboradores.remove(colaborador);
    }


    public void dar_alta_tecnico(String nombre, String apellido, String fechaNacimiento, Tipo_documento tipoDoc, String numeroDocumento, Medio_contacto[] medios,String latDom,String longDom,String direccion, Ciudad ciudad,Pais pais ,Integer latitud, Integer longitud , String radio)
    {
        Documento_identidad nuevo_documento = new Documento_identidad(numeroDocumento,tipoDoc);
        AreaCobertura nueva_area = new AreaCobertura(latitud,longitud,radio);
        Domicilio nuevo_domicilio = new Domicilio(latDom,longDom,direccion,ciudad,pais);
        Tecnico nueva_tecnico = new Tecnico(nombre,apellido,fechaNacimiento,nuevo_documento,medios,nuevo_domicilio,nueva_area);
        tecnicos.add(nueva_tecnico);
    }
    void dar_baja_tecnico(Tecnico tecnico)
    {
        tecnicos.remove(tecnico);
    }


    public List<Colaborador> getColaboradores() {
        return colaboradores;
    }

}