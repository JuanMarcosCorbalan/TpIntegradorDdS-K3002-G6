package org.example;

import org.example.Colaborador.Colaborador;
import org.example.Colaborador.Forma_colaborar;
import org.example.Colaborador.RepositorioColaboradores;
import org.example.Formas_contribucion.HacerseCargoHeladera;
import org.example.Formas_contribucion.Motivo_distribucion;
import org.example.Heladeras.Heladera;
import org.example.Persona.*;
import org.example.Formas_contribucion.Contribucion;
import org.example.PersonaVulnerable.PersonaSituacionVulnerable;
import org.example.Personal.AreaCobertura;
import org.example.Personal.Tecnico;
import org.example.Colaborador.ControladoresColaborador.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import io.javalin.Javalin;
import org.example.Sistema.MigracionColaboradores;
import org.example.Tarjetas.TarjetaColaborador;

public class Main {

    static List<Colaborador> colaboradores = new ArrayList<Colaborador>();
    static RepositorioColaboradores colaboradoresExistentes ;
    List<PersonaSituacionVulnerable> personasVulnerables = new ArrayList<PersonaSituacionVulnerable>();
    public List<Tecnico> tecnicos = new ArrayList<Tecnico>();

    public static void main(String[] args) {
        List<Colaborador> colaboradoresNuevo = colaboradores;
        colaboradoresExistentes  = new RepositorioColaboradores(colaboradores);
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
                .get("/", ctx -> ctx.render("/paginaWebColaboracionHeladeras/inicioSesion/html/inicioSesion.mustache"))
                .start(8081);

        app.get("/inicioSesion", ctx -> {
            ctx.render("/paginaWebColaboracionHeladeras/inicioSesion/html/inicioSesion.mustache");
        });
        app.get("/distribucionTarjetas", ctx -> {
            ctx.render("/paginaWebColaboracionHeladeras/distribucionTarjetas/html/distribucionTarjetas.mustache");
        });
        app.get("/distribucionViandas", ctx -> {
            ctx.render("/paginaWebColaboracionHeladeras/distribucionViandas/html/distribucionViandas.mustache");
        });

        app.get("/donacionDinero", ctx -> {
            ctx.render("/paginaWebColaboracionHeladeras/donacionDinero/html/donacionDinero.mustache");
        });
        app.get("/donacionVianda", ctx -> {
            ctx.render("/paginaWebColaboracionHeladeras/donacionVianda/html/donacionVianda.mustache");
        });
        app.get("/gestionHeladeras", ctx -> {
            ctx.render("/paginaWebColaboracionHeladeras/gestionHeladeras/html/gestionHeladeras.mustache");
        });//server error

        app.get("/hacerseCargoHeladera", ctx -> {
            ctx.render("/paginaWebColaboracionHeladeras/hacerseCargoHeladera/html/hacerseCargoHeladera.mustache");
        });//server error

        app.get("/inicioAdminitrador", ctx -> {
            ctx.render("/paginaWebColaboracionHeladeras/inicioAdminitrador/html/inicioAdminitrador.mustache");
        }); //server error

        app.get("/puntosYCanjes", ctx -> {
            ctx.render("/paginaWebColaboracionHeladeras/puntosYCanjes/html/puntosYCanjes.mustache");
        }); //server error

        app.get("/registroOpciones", ctx -> {
            ctx.render("/paginaWebColaboracionHeladeras/registroOpciones/html/registroOpciones.mustache");
        }); //server error

        app.get("/registroPersonaFisica", ctx -> {
            ctx.render("/paginaWebColaboracionHeladeras/registroPersonaFisica/html/registroPersonaFisica.mustache");
        }); //server error


        app.get("/registroPersonaJuridica", ctx -> {
            ctx.render("/paginaWebColaboracionHeladeras/registroPersonaJuridica/html/registroPersonaJuridica.mustache");
        });//server error

        app.get("/resultadoMigracionCSV", ctx -> {
            ctx.render("/paginaWebColaboracionHeladeras/resultadoMigracionCSV/html/resultadoMigracionCSV.mustache");
        });//server error

        app.get("/visualizadorReporteSemanal", ctx -> {
            ctx.render("/paginaWebColaboracionHeladeras/visualizadorReporteSemanal/html/visualizadorReporteSemanal.html");
        });//server error


        // login para guardar al colaborador
        app.post("/login", ctx -> {
            // Validar credenciales del colaborador

            Persona_fisica juan = new Persona_fisica("juan", "corbalan");
            Colaborador colaborador = new Colaborador(juan);
            colaborador.setTarjetaColaborador(new TarjetaColaborador("t1", colaborador));
            //ColaboradorController.login(ctx.formParam("email"), ctx.formParam("password"));

            String buttonType = ctx.formParam("buttonType");
            String username = ctx.formParam("nombreUsuario");
            String password = ctx.formParam("contraseniaUsuario");

            if (colaborador != null) {
                // Guardar al colaborador en la sesión
                ctx.sessionAttribute("colaborador", colaborador);
                switch (buttonType) {
                    case "principal":
                        // Lógica para iniciar sesión normal
                        ctx.result("Inicio de sesión principal, RECONOCERIA EL TIPO DE CUENTA Y LLEVA A SU INICIO, POR AHORA INGRESAR DE FORMA MANUAL CON EL BOTON DE CADA TIPO DE CUENTA");
                        break;
                    case "fisica":
                        // Lógica para iniciar sesión como persona física
                        ctx.render("/paginaWebColaboracionHeladeras/inicioPersonaFisica/html/inicioPersonaFisica.mustache");
                        break;
                    case "juridica":
                        // Lógica para iniciar sesión como persona jurídica
                        ctx.render("/paginaWebColaboracionHeladeras/inicioPersonaJuridica/html/inicioPersonaJuridica.mustache");
                        break;
                    default:
                        ctx.status(400).result("Acción no reconocida");
                        break;
                }

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
                Heladera heladera = new Heladera("heladera0Prueba", 10);
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

        app.post("/solicitarTarjetas", ctx -> {
            // Obtener al colaborador desde la sesión
            Colaborador colaborador = ctx.sessionAttribute("colaborador");
            if (colaborador != null) {
                // Recibir los datos del formulario
                Integer cantidadDeTarjetas = Integer.valueOf(ctx.formParam("inputCantidadTarjetas"));
                // Llamar a la lógica de backend
                SolicitarTarjetasParaRepartirHandler.realizarDonacion(colaborador, cantidadDeTarjetas);

                // Enviar una respuesta de confirmación
                ctx.result("Solicitud de tarjetas realizada con éxito.");
            } else {
                // Si el colaborador no está en la sesión, redirigir al login o mostrar error
                ctx.status(401).result("Por favor inicia sesión para realizar un registro.");
            }
        });

        app.post("/registrarPersonaSV", ctx -> {
            // Obtener al colaborador desde la sesión
            Colaborador colaborador = ctx.sessionAttribute("colaborador");
            if (colaborador != null) {
                // Recibir los datos del formulario
                String nombre = ctx.formParam("inputNombre");
                String apellido = ctx.formParam("inputApellido");
                LocalDate fechaNacimiento = LocalDate.parse(ctx.formParam("inputFechaNacimiento"));
                Integer cantidadMenoresACargo = Integer.valueOf(ctx.formParam("inputCantidadMenores"));
                String situacionDeCalleString = ctx.formParam("flexRadioDefault");
                boolean situacionDeCalle = Boolean.parseBoolean(situacionDeCalleString);
                String domicilio = ctx.formParam("inputCalle") + ctx.formParam("inputNumero");
                // Llamar a la lógica de backend
                RegistrarPersonasSvHandler.registrarPersonaSv(colaborador, nombre, apellido, situacionDeCalle, domicilio, cantidadMenoresACargo);

                // Enviar una respuesta de confirmación
                ctx.result("Registro realizado con éxito.");
            } else {
                // Si el colaborador no está en la sesión, redirigir al login o mostrar error
                ctx.status(401).result("Por favor inicia sesión para realizar un registro.");
            }
        });
        app.post("/distribucionViandas", ctx -> {
            // Obtener al colaborador desde la sesión
            Colaborador colaborador = ctx.sessionAttribute("colaborador");
            if (colaborador != null) {
                // Recibir los datos del formulario
                Heladera heladera0 = new Heladera("heladera0Prueba", 10);
                Heladera heladera1 = new Heladera("heladera1Prueba", 10);
                LocalDate fechaDistribucion = LocalDate.parse(ctx.formParam("inputFechaDistribucion"));
                Integer cantidadViandasAMover = Integer.valueOf(ctx.formParam("inputCantidadViandas"));
                String desperfecto = ctx.formParam("opcionDesperfecto");
                if (desperfecto == null) {
                    desperfecto = "false";
                }
                String faltantes = ctx.formParam("opcionFaltantes");
                if (faltantes == null) {
                    faltantes = "false";
                }
                Motivo_distribucion motivoDistribucion = null;
                if (desperfecto.equals("desperfecto") && faltantes.equals("faltantes")) {
                    motivoDistribucion = Motivo_distribucion.AMBOS;
                } else {
                    if (desperfecto.equals("desperfecto")) {
                        motivoDistribucion = Motivo_distribucion.DESPERFECTO_HELADERA;
                    } else {
                        if (faltantes.equals("faltantes")) {
                            motivoDistribucion = Motivo_distribucion.FALTA_VIANDAS_HELADERA_DESTINO;
                        } else {
                            ctx.status(401).result("Seleccione un motivo de distribucion");
                        }
                    }

                    }
                    // Llamar a la lógica de backend
                    SolicitarDistribucionViandasHandler.solicitarDistribucion(colaborador, heladera0, heladera1, cantidadViandasAMover, motivoDistribucion);

                    // Enviar una respuesta de confirmación
                    ctx.result("Solicitud realizada con éxito.");
                } else{
                    // Si el colaborador no está en la sesión, redirigir al login o mostrar error
                    ctx.status(401).result("Por favor inicia sesión para realizar la solicitud.");
                }
            });

            app.post("/hacerseCargoHeladera", ctx -> {
                Colaborador colaborador = ctx.sessionAttribute("colaborador");
                if (colaborador != null) {
                    HacerseCargoHeladera hacerseCargoHeladera = new HacerseCargoHeladera(colaborador);
                    SolicitarHacerseCargoHeladeraHandler.hacerseCargoHeladera(hacerseCargoHeladera);

                    ctx.result("Solicitud realizada con éxito.");
                }else{
                    // Si el colaborador no está en la sesión, redirigir al login o mostrar error
                    ctx.status(401).result("Por favor inicia sesión para realizar la solicitud.");
                }

            });

            app.post("/migrarCsv", ctx -> {
                Colaborador colaborador = ctx.sessionAttribute("colaborador");
                if (colaborador != null) {
                    var archivoCsv = ctx.uploadedFile("archivoCsv");
                    if (archivoCsv != null) {

                        String directorioDestino = "/csvs/";
                        File directorio = new File(directorioDestino);

                        if (!directorio.exists()) {
                            directorio.mkdirs(); // Crea el directorio si no existe
                        }
                        String pathArchivo = directorioDestino  + archivoCsv.filename();
                        try(OutputStream out = new FileOutputStream(pathArchivo)) {
                            archivoCsv.content().transferTo(out);
                        } catch (IOException e) {
                            e.printStackTrace();
                            ctx.status(500).result("Error al guardar el archivo.");
                            return;
                        }
                        // esto podria ir en el handler
                        MigracionColaboradores migracionColaboradores = new MigracionColaboradores(pathArchivo, colaboradoresExistentes);
                        migracionColaboradores.migrarCsv();
                        for (Colaborador colaboradorLista : colaboradoresExistentes.getColaboradores()) {
                            Persona persona = colaboradorLista.getPersona_colaboradora();
                            if (persona instanceof Persona_fisica personaFisicaExistente) {
                                System.out.println("DNI: " + personaFisicaExistente.getDocumento_identidad().getNumeroDocumento());
                                String nombre = personaFisicaExistente.getNombre();
                                List<Contribucion> contribuciones = colaboradorLista.getContribuciones();
                                if (!contribuciones.isEmpty()) System.out.println("Colaboraciones: ");
                                else System.out.println("No tiene colaboraciones realizadas");
                                for (Contribucion contribucion : contribuciones) {
                                    System.out.println(contribucion.getFecha_contribucion());
                                }
                            }

                        }
                    }
                } else {
                    ctx.status(401).result("Por favor inicia sesión para realizar la solicitud.");
                }
            });


            //instanciacion.crearColaboradores(colaboradores);
            //instanciacion.migrarColaboradores(colaboradores);



        }

        public void dar_alta_colaborador_fisico (String nombre, String apellido, String fechaNacimiento, Tipo_documento
        tipoDoc, String numeroDocumento, Medio_contacto[]medios, String latDom, String longDom, String direccion, Ciudad
        ciudad, Pais pais, Forma_colaborar[]formas)
        {
            Documento_identidad nuevo_documento = new Documento_identidad(numeroDocumento, tipoDoc);
            Domicilio nuevo_domicilio = new Domicilio(latDom, longDom, direccion, ciudad, pais);
            Persona_fisica nueva_persona = new Persona_fisica(nombre, apellido, fechaNacimiento, nuevo_documento, medios, nuevo_domicilio);
            Colaborador colaborador = new Colaborador(nueva_persona, formas);
            colaboradores.add(colaborador);
        }
        public void dar_alta_colaborador_juridico (String razonSocial, Tipo_juridico tipo, String rubro, Medio_contacto[]
        medios, String latDom, String longDom, String direccion, Ciudad ciudad, Pais pais, Forma_colaborar[]formas)
        {
            Domicilio nuevo_domicilio = new Domicilio(latDom, longDom, direccion, ciudad, pais);
            Persona_juridica nueva_persona = new Persona_juridica(nuevo_domicilio, medios, razonSocial, tipo, rubro);
            Colaborador colaborador = new Colaborador(nueva_persona, formas);
            colaboradores.add(colaborador);
        }
        void darBajaColaborador (Colaborador colaborador){
            colaboradores.remove(colaborador);
        }


        public void dar_alta_tecnico (String nombre, String apellido, String fechaNacimiento, Tipo_documento
        tipoDoc, String numeroDocumento, Medio_contacto[]medios, String latDom, String longDom, String direccion, Ciudad
        ciudad, Pais pais, Integer latitud, Integer longitud, String radio)
        {
            Documento_identidad nuevo_documento = new Documento_identidad(numeroDocumento, tipoDoc);
            AreaCobertura nueva_area = new AreaCobertura(latitud, longitud, radio);
            Domicilio nuevo_domicilio = new Domicilio(latDom, longDom, direccion, ciudad, pais);
            Tecnico nueva_tecnico = new Tecnico(nombre, apellido, fechaNacimiento, nuevo_documento, medios, nuevo_domicilio, nueva_area);
            tecnicos.add(nueva_tecnico);
        }
        void dar_baja_tecnico (Tecnico tecnico)
        {
            tecnicos.remove(tecnico);
        }


        public List<Colaborador> getColaboradores () {
            return colaboradores;
        }

    }