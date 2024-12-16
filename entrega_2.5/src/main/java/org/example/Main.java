package org.example;

import org.example.Colaborador.Colaborador;
import org.example.Colaborador.Forma_colaborar;
import org.example.Colaborador.RepositorioColaboradores;
import org.example.DAO.*;
import org.example.Formas_contribucion.HacerseCargoHeladera;
import org.example.Formas_contribucion.Motivo_distribucion;
import org.example.Heladeras.Heladera;
import org.example.Heladeras.HeladeraDTO;
import org.example.Heladeras.HeladeraDTO2;
import org.example.MigracionCsv.DatosColaboracion;
import org.example.Ofertas.Oferta;
import org.example.Persona.*;
import org.example.Formas_contribucion.Contribucion;
import org.example.PersonaVulnerable.PersonaSituacionVulnerable;
import org.example.Personal.AreaCobertura;
import org.example.Personal.Tecnico;
import org.example.Colaborador.ControladoresColaborador.*;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

import io.javalin.Javalin;
import org.example.Sistema.MigracionColaboradores;
import org.example.Sistema.Usuario;
import org.example.Sistema.UsuarioService;
import org.example.Suscripcion.TipoSuscripcion;
import org.example.Tarjetas.TarjetaColaborador;
import org.example.Utils.BDutils;
import org.example.ValidarContrasenia.ValidarContrasenia;

import javax.persistence.EntityManager;

public class Main {

    static List<Colaborador> colaboradores = new ArrayList<Colaborador>();
    //static List<Oferta> ofertasDisponibles = new ArrayList<>();
    static RepositorioColaboradores colaboradoresExistentes;
    List<PersonaSituacionVulnerable> personasVulnerables = new ArrayList<PersonaSituacionVulnerable>();
    public List<Tecnico> tecnicos = new ArrayList<Tecnico>();

    public static void main(String[] args) {
        List<Colaborador> colaboradoresNuevo = colaboradores;
        colaboradoresExistentes = new RepositorioColaboradores(colaboradores);
        InstanciacionClases instanciacion = new InstanciacionClases();
        System.out.println("Hello world!");

        EntityManager em = BDutils.getEntityManager();

        Javalin app = Javalin.create(javalinConfig -> {
                            javalinConfig.plugins.enableCors(cors -> {
                                cors.add(it -> it.anyHost());
                            }); // para poder hacer requests de un dominio a otro

                            javalinConfig.staticFiles.add("/paginaWebColaboracionHeladeras"); //recursos estaticos (HTML, CSS, JS, IMG)
                            //JavalinMustache.init();  // Configurar Mustache
                        }

                )
                .get("/", ctx -> ctx.render("/paginaWebColaboracionHeladeras/SALVACIONDDS/login.mustache"))
                .start(8081);

        app.get("/inicioSesion", ctx -> {
            ctx.render("/paginaWebColaboracionHeladeras/SALVACIONDDS/login.mustache");
        });
        app.get("/distribucionTarjetas", ctx -> {
            ctx.render("/paginaWebColaboracionHeladeras/distribucionTarjetas/html/distribucionTarjetas.mustache");
        });
        /*
        app.get("/distribucionViandas", ctx -> {
            ctx.render("/paginaWebColaboracionHeladeras/distribucionViandas/html/distribucionViandas.mustache");
        });*/

        app.get("/distribucionViandas", ctx -> {
            ctx.render("/paginaWebColaboracionHeladeras/SALVACIONDDS/distribucionViandas.mustache");
        });

/*
        app.get("/donacionDinero", ctx -> {
            ctx.render("/paginaWebColaboracionHeladeras/donacionDinero/html/donacionDineroFisica.mustache");
        });

        app.get("/donacionVianda", ctx -> {
            ctx.render("/paginaWebColaboracionHeladeras/donacionVianda/html/donacionVianda.mustache");
        }); */
        app.get("/donacionDineroFisica", ctx -> {
            ctx.render("/paginaWebColaboracionHeladeras/SALVACIONDDS/donacionDineroFisica.mustache");
        });
        app.get("/donacionDineroJuridica", ctx -> {
            ctx.render("/paginaWebColaboracionHeladeras/SALVACIONDDS/donacionDineroJuridica.mustache");
        });

        app.get("/donacionVianda", ctx -> {
            ctx.render("/paginaWebColaboracionHeladeras/SALVACIONDDS/donacionVianda.mustache");
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
            UsuarioService us = new UsuarioService(em);

            /*
            Persona_fisica juan = new Persona_fisica("juan", "corbalan");
            Colaborador colaborador = new Colaborador(juan);
            colaborador.setTarjetaColaborador(new TarjetaColaborador("t1", colaborador));
            */

            //ColaboradorController.login(ctx.formParam("email"), ctx.formParam("password"));

            String buttonType = ctx.formParam("buttonType");
            String username = ctx.formParam("nombreUsuario");
            String password = ctx.formParam("contraseniaUsuario");

            Map<String, Object> model = new HashMap<>();

            switch (buttonType) {
                case "principal":
                    Usuario usuario;
                    // Lógica para iniciar sesión normal
                    try {
                        usuario = us.verificarInicioSesion(username, password);
                        Colaborador colaborador = us.obtenerColaborador(usuario);
                        // Guardar al colaborador en la sesión
                        ctx.sessionAttribute("colaborador", colaborador);
                        if (colaborador.persona instanceof Persona_fisica personaFisica) {
                            ctx.render("/paginaWebColaboracionHeladeras/SALVACIONDDS/inicioPersonaFisica.mustache");
                            return;
                        } else if (colaborador.persona instanceof Persona_juridica personaJuridica) {
                            ctx.render("/paginaWebColaboracionHeladeras/SALVACIONDDS/inicioPersonaJuridica.mustache");
                            return;
                        }
                    } catch (RuntimeException e) {
                        model.put("error", e.getMessage());
                    }
                    break;
                case "administrador":
                    ctx.render("/paginaWebColaboracionHeladeras/inicioAdministrador/html/inicioAdministrador.mustache");
                    return;
                default:
                    ctx.status(400).result("Acción no reconocida");
                    return;
            }
            // Si hubo un error o no se reconoció el tipo de usuario, renderizar la misma página de login con el error
            ctx.render("/paginaWebColaboracionHeladeras/SALVACIONDDS/login.mustache", model);
        });
        // Ruta para manejar la solicitud POST de realizar donación
        app.post("/solicitarDonacionVianda", ctx -> {
            // Obtener al colaborador desde la sesión
            Colaborador colaborador = ctx.sessionAttribute("colaborador");
            ColaboradorDAO colaboradorDAO = new ColaboradorDAO(em);
            TarjetaDAO tarjetaDAO = new TarjetaDAO(em);
            HeladeraDAO heladeraDAO = new HeladeraDAO(em);
            if (colaborador != null) {
                // Recibir los datos del formulario
                String nombre = ctx.formParam("inputComida");
                LocalDate fechaVencimiento = LocalDate.parse(ctx.formParam("inputFechaVencimiento"));
                String idHeladera = ctx.formParam("selectedHeladeraId");
                Heladera heladera = heladeraDAO.findHeladeraString(idHeladera);
                //Heladera heladera = new Heladera("heladera0Prueba", 10);
                String calorias = ctx.formParam("inputCalorias");
                String pesoGramos = ctx.formParam("inputPeso");
                // Llamar a la lógica de backend
                SolicitarDonacionViandaHandler.realizarDonacion(colaborador, nombre, fechaVencimiento, heladera, calorias, pesoGramos);
                colaboradorDAO.update(colaborador);
                tarjetaDAO.update(colaborador.getTarjetaColaborador());
                // Enviar una respuesta de confirmación
                ctx.render("/paginaWebColaboracionHeladeras/resultados/html/confirmacionFisica.mustache");
            } else {
                // Si el colaborador no está en la sesión, redirigir al login o mostrar error
                ctx.redirect("/inicioSesion");
            }
        });

        app.post("/solicitarTarjetas", ctx -> {
            // Obtener al colaborador desde la sesión
            Colaborador colaborador = ctx.sessionAttribute("colaborador");
            ColaboradorDAO colaboradorDAO = new ColaboradorDAO(em);
            if (colaborador != null) {
                // Recibir los datos del formulario
                Integer cantidadDeTarjetas = Integer.valueOf(ctx.formParam("inputCantidadTarjetas"));
                // Llamar a la lógica de backend
                SolicitarTarjetasParaRepartirHandler.realizarDonacion(colaborador, cantidadDeTarjetas);
                colaboradorDAO.update(colaborador);

                ctx.sessionAttribute("colaborador", colaborador);
                // Enviar una respuesta de confirmación
                ctx.redirect("/registroPersonasSv");
            } else {
                // Si el colaborador no está en la sesión, redirigir al login o mostrar error
                ctx.redirect("/inicioSesion");
            }
        });

        app.post("/registrarPersonaSV", ctx -> {
            // Obtener al colaborador desde la sesión
            Colaborador colaborador = ctx.sessionAttribute("colaborador");
            ColaboradorDAO colaboradorDAO = new ColaboradorDAO(em);
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
                    colaboradorDAO.update(colaborador);
                    // Enviar una respuesta de confirmación
                    ctx.render("/paginaWebColaboracionHeladeras/resultados/html/confirmacionFisica.mustache");
                } else {
                // Si el colaborador no está en la sesión, redirigir al login o mostrar error
                ctx.redirect("/inicioSesion");
            }
        });

        app.get("/registroPersonasSv", ctx -> {
            // Obtener al colaborador desde la sesión
            Colaborador colaborador = ctx.sessionAttribute("colaborador");
            ColaboradorDAO colaboradorDAO = new ColaboradorDAO(em);
            if (colaborador != null) {

                Integer tarjetasARepartir = colaborador.getTarjetasARepartir();
                // Crear un modelo con la lista de ofertas y los puntos
                Map<String, Object> model = new HashMap<>();

                if (tarjetasARepartir != null) {
                    // mostrar la cantidad de tarjetas restantes
                    model.put("tarjetasRestantes", tarjetasARepartir);

                    ctx.render("/paginaWebColaboracionHeladeras/distribucionTarjetas/html/distribucionTarjetas.mustache",model);

                } else {
                    model.put("tarjetasRestantes", "0 tarjetas solicitadas, solicite tarjetas ahora!");
                    ctx.render("/paginaWebColaboracionHeladeras/distribucionTarjetas/html/distribucionTarjetas.mustache", model);
                }
            } else {
                ctx.redirect("/inicioSesion");
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
                ctx.render("/paginaWebColaboracionHeladeras/resultados/html/confirmacionFisica.mustache");
            } else {
                // Si el colaborador no está en la sesión, redirigir al login o mostrar error
                ctx.redirect("/inicioSesion");
            }
        });

        app.post("/hacerseCargoHeladera", ctx -> {
            Colaborador colaborador = ctx.sessionAttribute("colaborador");
            if (colaborador != null) {
                HacerseCargoHeladera hacerseCargoHeladera = new HacerseCargoHeladera(colaborador);
                SolicitarHacerseCargoHeladeraHandler.hacerseCargoHeladera(hacerseCargoHeladera);

                ctx.render("/paginaWebColaboracionHeladeras/resultados/html/confirmacionJuridica.mustache");
            } else {
                // Si el colaborador no está en la sesión, redirigir al login o mostrar error
                ctx.redirect("/inicioSesion");
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
                    String pathArchivo = directorioDestino + archivoCsv.filename();
                    try (OutputStream out = new FileOutputStream(pathArchivo)) {
                        archivoCsv.content().transferTo(out);
                    } catch (IOException e) {
                        e.printStackTrace();
                        ctx.status(500).result("Error al guardar el archivo.");
                        return;
                    }
                    // esto podria ir en el handler
                    MigracionColaboradores migracionColaboradores = new MigracionColaboradores(pathArchivo, colaboradoresExistentes);
                    List<DatosColaboracion> datosAImprimir = migracionColaboradores.migrarCsv();
                    System.out.println(datosAImprimir);
                    Map<String, Object> model = new HashMap<>();
                    model.put("datosColaboraciones", datosAImprimir);
                    // Pasa la lista al contexto y renderiza la plantilla
                    ctx.attribute("datosColaboracion", datosAImprimir);
                    ctx.render("/paginaWebColaboracionHeladeras/resultadoMigracionCSV/html/resultadoMigracionCSV.mustache", model);
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
                ctx.redirect("/inicioSesion");
            }
        });
        //instanciacion.crearColaboradores(colaboradores);
        //instanciacion.migrarColaboradores(colaboradores);

        app.post("/registarPersonaFisica", ctx -> {
            LocalidadDAO localidadDAO = new LocalidadDAO(em);
            ColaboradorDAO colaboradorDAO = new ColaboradorDAO(em);
            UsuarioDAO usuarioDAO = new UsuarioDAO(em);

            String usuarioNombre = ctx.formParam("usuario");
            String contrasenia = ctx.formParam("contraseña");
            ValidarContrasenia validadorContra = new ValidarContrasenia();
            if(!validadorContra.validar(contrasenia)){
                ctx.status(400).result("Contraseña poco segura");
            }

            Tipo_documento tipoDocumento = null;
            List<Medio_contacto> mediosContacto = new ArrayList<>();

            String nombres = ctx.formParam("inputNombre");
            String apellidos = ctx.formParam("inputApellidos");
            String fechaNacimiento = ctx.formParam("inputFechaNacimiento");
            String numeroDocumento = ctx.formParam("inputNumeroDocumento");
            String tipoDocumentoString = ctx.formParam("inputTipoDocumento");
            switch (tipoDocumentoString) {
                case "1":
                    tipoDocumento = Tipo_documento.DNI;
                    break;
                case "2":
                    tipoDocumento = Tipo_documento.LIBRETA_ENROLAMIENTO;
                    break;
                case "3":
                    tipoDocumento = Tipo_documento.LIBRETA_CIVICA;
                    break;
                default:
                    ctx.status(400).result("por favor ingrese un tipo de documento");
            }
            Documento_identidad documento = new Documento_identidad(numeroDocumento, tipoDocumento);
            String domicilio = ctx.formParam("inputCalle") + " " + ctx.formParam("inputAltura");
            String ciudadString = ctx.formParam("ciudad");
            String paisString = ctx.formParam("pais");
            String localidadString = ctx.formParam("inputLocalidad");
            Localidad localidad = localidadDAO.findOrCreate(localidadString, ciudadString, paisString);


            Domicilio domicilioNuevo = new Domicilio(localidad, domicilio);
            Persona_fisica persona = new Persona_fisica(nombres, apellidos, fechaNacimiento, documento, mediosContacto,
                    domicilioNuevo);

            Usuario usuario = new Usuario(persona, usuarioNombre, contrasenia);

            String correo = ctx.formParam("inputCorreo");
            String numeroTelefono = ctx.formParam("inputTelefono");
            String numeroWhatsapp = ctx.formParam("inputWhatsapp");

            if (correo != null) {
                Medio_contacto nuevoCorreo = new Mail(persona, correo);
                persona.agregarMedioContacto(nuevoCorreo);
            }

            if (numeroTelefono != null) {
                Medio_contacto nuevoTelefono = new Telefono(persona, numeroTelefono);
                persona.agregarMedioContacto(nuevoTelefono);
            }

            if (numeroWhatsapp != null) {
                Medio_contacto nuevoWhatsapp = new Whatsapp(persona, numeroWhatsapp);
                persona.agregarMedioContacto(nuevoWhatsapp);
            }

            Colaborador colaborador = new Colaborador(persona);
            colaboradorDAO.save(colaborador);
            usuarioDAO.save(usuario);
            colaboradoresExistentes.agregarColaborador(colaborador);
            ctx.render("/paginaWebColaboracionHeladeras/resultados/html/confirmacionRegistroUsuario.mustache");
        });

        app.post("/registarPersonaJuridica", ctx -> {
            LocalidadDAO localidadDAO = new LocalidadDAO(em);
            ColaboradorDAO colaboradorDAO = new ColaboradorDAO(em);
            UsuarioDAO usuarioDAO = new UsuarioDAO(em);

            String usuarioNombre = ctx.formParam("usuario");
            String contrasenia = ctx.formParam("contraseña");
            ValidarContrasenia validadorContra = new ValidarContrasenia();
            if(!validadorContra.validar(contrasenia)){
                ctx.status(400).result("Contraseña poco segura");
            }

            Tipo_juridico tipoJuridico = null;
            List<Medio_contacto> mediosContacto = new ArrayList<>();

            String razonSocial = ctx.formParam("inputRazonSocial");
            String tipoOrganizacionString = ctx.formParam("inputTipoOrganizacion");
            switch (tipoOrganizacionString) {
                case "1":
                    tipoJuridico = Tipo_juridico.GUBERNAMENTAL;
                    break;
                case "2":
                    tipoJuridico = Tipo_juridico.ONG;
                    break;
                case "3":
                    tipoJuridico = Tipo_juridico.EMPRESA;
                    break;
                case "4":
                    tipoJuridico = Tipo_juridico.INSTITUCION;
                    break;
                default:
                    ctx.status(400).result("por favor ingrese un tipo de organización");
            }
            String domicilio = ctx.formParam("inputCalle") + ctx.formParam("inputAltura");
            String ciudadString = ctx.formParam("ciudad");
            String paisString = ctx.formParam("pais");
            String localidadString = ctx.formParam("inputLocalidad");

            Localidad localidad = localidadDAO.findOrCreate(localidadString, ciudadString, paisString);
            Domicilio domicilioNuevo = new Domicilio(localidad, domicilio);
            String localALaCalle = ctx.formParam("LocalCalle");
            switch (localALaCalle) {
                case "1":
                    domicilioNuevo.setDaALaCalle(true);
                    break;
                case "0":
                    domicilioNuevo.setDaALaCalle(false);
                    break;
            }

            Persona_juridica persona = new Persona_juridica(domicilioNuevo, mediosContacto, razonSocial, tipoJuridico);
            Usuario usuario = new Usuario(persona, usuarioNombre, contrasenia);

            String correo = ctx.formParam("inputCorreo");
            String numeroTelefono = ctx.formParam("inputTelefono");
            String numeroWhatsapp = ctx.formParam("inputWhatsapp");

            if (correo != null) {
                Medio_contacto nuevoCorreo = new Mail(persona, correo);
                persona.agregarMedioContacto(nuevoCorreo);
            }

            if (numeroTelefono != null) {
                Medio_contacto nuevoTelefono = new Telefono(persona, numeroTelefono);
                persona.agregarMedioContacto(nuevoTelefono);
            }

            if (numeroWhatsapp != null) {
                Medio_contacto nuevoWhatsapp = new Whatsapp(persona, numeroWhatsapp);
                persona.agregarMedioContacto(nuevoWhatsapp);
            }
            Colaborador colaborador = new Colaborador(persona);
            colaboradorDAO.save(colaborador);
            usuarioDAO.save(usuario);
            colaboradoresExistentes.agregarColaborador(colaborador);
            ctx.render("/paginaWebColaboracionHeladeras/resultados/html/confirmacionRegistroUsuario.mustache");
        });

        app.get("/puntosYCanjes", ctx -> {
            Colaborador colaborador = ctx.sessionAttribute("colaborador");
            if (colaborador != null) {
                // Obtener las ofertas desde tu dominio o base de datos

                //List<Oferta> ofertas = ofertasDisponibles; // Aquí iría la lógica para obtener las ofertas

                OfertaDAO ofertaDAO = new OfertaDAO(em);
                List<Oferta> ofertas = ofertaDAO.findAll();

                double puntosColaborador = colaborador.obtenerPuntos(); // Obtener los puntos del colaborador

                // Crear un modelo con la lista de ofertas y los puntos
                Map<String, Object> model = new HashMap<>();
                model.put("ofertas", ofertas); // Pasa la lista de ofertas al modelo
                model.put("puntos", puntosColaborador); // Pasa los puntos del colaborador al modelo

                // Renderizar la plantilla Mustache y pasar el modelo
                ctx.render("/paginaWebColaboracionHeladeras/SALVACIONDDS/puntosYCanjes.mustache", model);
            } else {
                ctx.status(401).result("Por favor inicia sesión para ver las ofertas.");
            }
        });
        app.post("/canjearOferta", ctx -> {
            Colaborador colaborador = ctx.sessionAttribute("colaborador");
            ColaboradorDAO colaboradorDAO = new ColaboradorDAO(em);
            if (colaborador != null) {
                // Obtener el ID de la oferta a canjear (por ejemplo, a través de un formulario)
                String nombreOferta = ctx.formParam("nombreOferta");

                OfertaDAO ofertaDAO = new OfertaDAO(em);
                List<Oferta> ofertas = ofertaDAO.findAll();

                // Buscar la oferta seleccionada en la lista de ofertas disponibles
                Oferta ofertaSeleccionada = ofertas.stream()
                        .filter(oferta -> Objects.equals(oferta.getNombre(), nombreOferta))
                        .findFirst()
                        .orElse(null);

                if (ofertaSeleccionada != null) {
                    // Verificar si el colaborador tiene suficientes puntos
                    if (colaborador.obtenerPuntos() >= ofertaSeleccionada.getPuntosNecesarios()) {
                        // Restar los puntos del colaborador
                        colaborador.canjearOferta(ofertaSeleccionada);
                        ctx.sessionAttribute("colaborador", colaborador);
                        colaboradorDAO.update(colaborador);
                        // Actualizar la base de datos o la lista de colaboradores si es necesario
                        // (Aquí iría la lógica para persistir los cambios)
                        ofertaDAO.update(ofertaSeleccionada);
                        ctx.render("/paginaWebColaboracionHeladeras/SALVACIONDDS/inicioPersonaFisica.mustache");
                    } else {
                        ctx.render("/paginaWebColaboracionHeladeras/resultados/html/rechazadoCanjeOferta.mustache");
                    }
                } else {
                    ctx.status(404).result("La oferta seleccionada no existe.");
                }
            } else {
                ctx.redirect("/inicioSesion");
            }
        });

        app.post("/reportarFallaTecnica", ctx -> {
            Colaborador colaborador = ctx.sessionAttribute("colaborador");
            if (colaborador != null) {
                Heladera heladeraprueba = new Heladera("heladeraPrueba");
                String descripcion = ctx.formParam("inputDescripcion");
                //UploadedFile foto = ctx.uploadedFile("inputFoto");
                colaborador.reportarFallaTenica(heladeraprueba, descripcion, null);
                ctx.result("Reporte de Falla generado con exito");
            } else {
                ctx.status(401).result("Por favor inicia sesión para reportar el fallo.");
            }
        });

        app.post("/donarDineroFisica", ctx -> {
            Colaborador colaborador = ctx.sessionAttribute("colaborador");
            ColaboradorDAO colaboradorDAO = new ColaboradorDAO(em);
            if (colaborador != null) {
                Integer monto = Integer.parseInt(ctx.formParam("inputMonto"));
                colaborador.donarMonto(monto);
                colaboradorDAO.update(colaborador);
                ctx.render("/paginaWebColaboracionHeladeras/resultados/html/confirmacionFisica.mustache");
            } else {
                ctx.redirect("/inicioSesion");
            }
        });
        app.post("/donarDineroJuridica", ctx -> {
            Colaborador colaborador = ctx.sessionAttribute("colaborador");
            ColaboradorDAO colaboradorDAO = new ColaboradorDAO(em);
            if (colaborador != null) {
                Integer monto = Integer.parseInt(ctx.formParam("inputMonto"));
                colaborador.donarMonto(monto);
                colaboradorDAO.update(colaborador);
                ctx.render("/paginaWebColaboracionHeladeras/resultados/html/confirmacionJuridica.mustache");
            } else {
                ctx.redirect("/inicioSesion");
            }
        });

        app.post("/suscribirse", ctx -> {
            Colaborador colaborador = ctx.sessionAttribute("colaborador");
            if (colaborador != null) {
                String heladeraString = ctx.formParam("selectedHeladera");

                // ACA DEBERIA BUSCAR LA HELADERA EN LA BD, PERO POR AHORA CREO UNA NUEVA
                Heladera heladera = new Heladera(heladeraString);
                String tipoSuscripcionString = ctx.formParam("tipoSuscripcion");
                TipoSuscripcion tipoSuscripcion = null;
                if (Objects.equals(tipoSuscripcionString, "quedanNViandas")) {
                    tipoSuscripcion = TipoSuscripcion.QUEDANNVIANDAS;
                } else {
                    if (Objects.equals(tipoSuscripcionString, "faltanNViandas")) {
                        tipoSuscripcion = TipoSuscripcion.FALTANNVIANDAS;
                    } else {
                        if (Objects.equals(tipoSuscripcionString, "avisoPorDesperfecto")) {
                            tipoSuscripcion = TipoSuscripcion.AVISOPORDESPERFECTO;
                        } else {
                            ctx.status(404).result("tipo de suscripcion no reconocido");
                        }
                    }
                }
                Integer cantViandas = 0;
                cantViandas = Integer.parseInt(ctx.formParam("cantidadViandas"));

                colaborador.suscribirseAHeladera(heladera, tipoSuscripcion, cantViandas);
                // aca deberia armar una interfaz para confirmacion de suscripcion, estoy usando la de colaboraciones
                ctx.render("/paginaWebColaboracionHeladeras/resultados/html/confirmacionSuscripcion.mustache");
            } else {
                ctx.redirect("/inicioSesion");
            }
        });

        app.post("/cargarOferta", ctx-> {
            Colaborador colaborador = ctx.sessionAttribute("colaborador");
            if (colaborador != null) {
                String nombreOferta = ctx.formParam("inputNombre");
                Integer cantidadPuntos = Integer.parseInt(ctx.formParam("inputPuntos"));
                Integer cantidadInstancias = Integer.parseInt(ctx.formParam("inputInstancias"));

                Oferta nuevaOferta = new Oferta(nombreOferta, cantidadPuntos, cantidadInstancias);
                //SE PERSISTE LA OFERTA
                OfertaDAO ofertaDAO = new OfertaDAO (em);
                ofertaDAO.save(nuevaOferta);
                //NO HARIA FALTA ESTO:
                //ofertasDisponibles.add(nuevaOferta);
                // aca deberia armar una interfaz para confirmacion de suscripcion, estoy usando la de colaboraciones
                ctx.render("/paginaWebColaboracionHeladeras/resultados/html/confirmacionOfertaCargada.mustache");
            } else {
                ctx.status(401).result("Por favor inicia sesión para donar dinero");
            }
        });


        app.get("/api/heladerasDTO", ctx -> {
            // Obtener las heladeras desde el DAO
            HeladeraDAO heladeraDAO = new HeladeraDAO(em);
            List<HeladeraDTO> heladeras = heladeraDAO.findAllHeladerasDTO();

            // Devolver las heladeras en formato JSON
            ctx.json(heladeras);
        });

        app.get("/api/heladeras", ctx -> {
            // Obtener las heladeras desde el DAO
            HeladeraDAO heladeraDAO = new HeladeraDAO(em);
            List<HeladeraDTO2> heladeras = heladeraDAO.findAllHeladeras();

            // Devolver las heladeras en formato JSON
            ctx.json(heladeras);
        });


    }

        public void dar_alta_colaborador_fisico (String nombre, String apellido, String fechaNacimiento, Tipo_documento
        tipoDoc, String numeroDocumento, List<Medio_contacto> medios, String latDom, String longDom, String direccion, Localidad localidad, List<Forma_colaborar> formas)
        {
            Documento_identidad nuevo_documento = new Documento_identidad(numeroDocumento, tipoDoc);
            Domicilio nuevo_domicilio = new Domicilio(latDom, longDom, direccion, localidad);
            Persona_fisica nueva_persona = new Persona_fisica(nombre, apellido, fechaNacimiento, nuevo_documento, medios, nuevo_domicilio);
            Colaborador colaborador = new Colaborador(nueva_persona, formas);
            colaboradores.add(colaborador);
        }
        public void dar_alta_colaborador_juridico (String razonSocial, Tipo_juridico tipo, String rubro, List<Medio_contacto>
        medios, String latDom, String longDom, String direccion, Localidad localidad, List<Forma_colaborar>formas)
        {
            Domicilio nuevo_domicilio = new Domicilio(latDom, longDom, direccion, localidad);
            Persona_juridica nueva_persona = new Persona_juridica(nuevo_domicilio, medios, razonSocial, tipo, rubro);
            Colaborador colaborador = new Colaborador(nueva_persona, formas);
            colaboradores.add(colaborador);
        }
        void darBajaColaborador (Colaborador colaborador){
            colaboradores.remove(colaborador);
        }


        public void dar_alta_tecnico (String nombre, String apellido, String fechaNacimiento, Tipo_documento
        tipoDoc, String numeroDocumento, List<Medio_contacto>medios, String latDom, String longDom, String direccion,Localidad localidad,
                                      String latitud, String longitud, String radio)
        {
            Documento_identidad nuevo_documento = new Documento_identidad(numeroDocumento, tipoDoc);
            AreaCobertura nueva_area = new AreaCobertura(latitud, longitud, radio);
            Domicilio nuevo_domicilio = new Domicilio(latDom, longDom, direccion, localidad);
            Tecnico nueva_tecnico = new Tecnico(nombre, apellido, fechaNacimiento, nuevo_documento, medios, nuevo_domicilio, nueva_area);
            tecnicos.add(nueva_tecnico);
        }
        void dar_baja_tecnico (Tecnico tecnico)
        {
            tecnicos.remove(tecnico);
        }


    public List<Colaborador> getColaboradores() {
        return colaboradores;
    }

}