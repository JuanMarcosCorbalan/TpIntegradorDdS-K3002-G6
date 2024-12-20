package org.example.MigracionCsv;

import com.sendgrid.helpers.mail.objects.Email;
import org.example.Colaborador.Colaborador;
import org.example.DAO.ColaboradorDAO;
import org.example.DAO.UsuarioDAO;
import org.example.Formas_contribucion.*;
import org.example.Funcionalidades.EnvioMail;
import org.example.GeneradorContrasenia;
import org.example.GeneradorUsuario;
import org.example.Persona.Documento_identidad;
import org.example.Persona.Persona;
import org.example.Persona.Persona_fisica;
import org.example.Persona.Tipo_documento;
import org.example.Sistema.LoggerToFile;
import org.example.Sistema.Usuario;
import org.example.Utils.BDutils;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CargaDatosCsv {

    public void cargarDatos(List<Colaborador> colaboradoresExistentes, Map<String, Colaborador> colaboradoresExistentesMap, Map<String, Persona> personasFisicasExistentesMap, String nombre, String apellido, String numeroDocumentoString, Tipo_documento tipoDocumento, String mail, String formaColaboracion, Integer cantidad, LocalDate fechaColaboracion) throws IOException {

        // aca verifico si ya existe un colaborador con esa persona, me fijo en el documento
        // si existe lo obtengo de colaboradores existentes, si no existe lo creo y lo agrego a la lista de colaboradores del main
        Colaborador colaborador = this.obtenerColaborador(colaboradoresExistentes, colaboradoresExistentesMap, personasFisicasExistentesMap, nombre, apellido, numeroDocumentoString, tipoDocumento, mail);
        // POR AHORA TOMO QUE DONAR DINERO SEA UNA DONACION UNICA PORQ EN LA MIGRACION SE PIERDE LA FRECUENCIA
        // paso formaColaboracion a colaboracion
        this.identificarYCrearColaboracion(colaborador, formaColaboracion, cantidad, fechaColaboracion);

    }

    public void identificarYCrearColaboracion(Colaborador colaborador, String formaColaboracion, Integer cantidad, LocalDate fechaColaboracion){
        EntityManager em = BDutils.getEntityManager();
        ColaboradorDAO colaboradorDAO = new ColaboradorDAO(em);
        LoggerToFile.logInfo("Identificando Colaboración");
        switch (formaColaboracion) {
            case "DINERO" -> {
                Donacion_dinero donacionDinero = new Donacion_dinero(cantidad, Tipos_frecuencia.DONACION_UNICA, fechaColaboracion);
                donacionDinero.setEstado(EstadoContribucion.EXITOSA);
                colaborador.agregarContribucion(donacionDinero);
                LoggerToFile.logInfo("Colaboracion identificada como donacion de dinero, con una cantidad de " + cantidad + " pesos, en la fecha: " + fechaColaboracion);
            }
            case "REDISTRIBUCION_VIANDAS" -> {
                Distribucion_viandas distribucionViandas = new Distribucion_viandas(colaborador, cantidad, fechaColaboracion);
                distribucionViandas.setEstado(EstadoContribucion.EXITOSA);
                colaborador.agregarContribucion(distribucionViandas);
                LoggerToFile.logInfo("Colaboracion identificada como distrubucion de dinero, con una cantidad de " + cantidad + "  viandas distribuidas, en la fecha: " + fechaColaboracion);

            }
            case "ENTREGA_TARJETAS" -> {
                RegistrarPersonasSV entregaTarjetas = new RegistrarPersonasSV(cantidad,0,fechaColaboracion, colaborador);
                entregaTarjetas.setEstado(EstadoContribucion.EXITOSA);
                colaborador.agregarContribucion(entregaTarjetas);
                LoggerToFile.logInfo("Colaboracion identificada como registro de personas en situacion vulnerable, con una cantidad de " + cantidad + " tarjetas repartidas, en la fecha: " + fechaColaboracion);

            }
            case "DONACION_VIANDAS" -> {
                // no seteo la cantidad de viandas porque deberia agregar la vianda a la lista de viandas pero seria una vianda vacia
                Donacion_viandas donacionViandas = new Donacion_viandas(colaborador, fechaColaboracion, cantidad);
                donacionViandas.setEstado(EstadoContribucion.EXITOSA);
                colaborador.agregarContribucion(donacionViandas);
                LoggerToFile.logInfo("Colaboracion identificada como donacion de viandas, con una cantidad de " + cantidad + " viandas, en la fecha: " + fechaColaboracion);
            }
            default -> throw new Error("FORMA DE COLABORACION INVALIDA");
        }
        colaboradorDAO.update(colaborador);
    }

    public Documento_identidad crearDocumento(String numeroDocumentoString, Tipo_documento tipoDocumento){
        return new Documento_identidad(numeroDocumentoString,tipoDocumento);
    }

    public Persona_fisica crearPersonaFisica(String nombre, String apellido, Documento_identidad documento){
        return new Persona_fisica(nombre, apellido, documento);
    }

    public Colaborador crearColaborador(Persona_fisica nuevaPersonaFisica) {
        return new Colaborador(nuevaPersonaFisica);
    }

    public Boolean validarExistencia(Map<String, Persona> personasFisicasExistentesMap, String numeroDocumentoString){
        return personasFisicasExistentesMap.containsKey(numeroDocumentoString);

    }

    public Colaborador obtenerColaborador(List<Colaborador> colaboradoresExistentes, Map<String, Colaborador> colaboradoresExistentesMap, Map<String, Persona> personasFisicasExistentesMap,String nombre, String apellido, String numeroDocumentoString, Tipo_documento tipoDocumento, String mail) throws IOException {
        Boolean existeColaborador = this.validarExistencia(personasFisicasExistentesMap, numeroDocumentoString);
        LoggerToFile.logInfo("Verificando existencia de colaborador: " + nombre + " " + apellido + " - " + numeroDocumentoString);
        if (!existeColaborador){
            LoggerToFile.logInfo("Colaborador no encontrado, creando nuevo Colaborador");
            // creo los objetos con cada uno de los parametros recibidos
            // para crear a la persona necesito el objeto documento
            Documento_identidad documento = this.crearDocumento(numeroDocumentoString, tipoDocumento);

            // para crear al colaborador necesito a la persona
            Persona_fisica nuevaPersonaFisica = this.crearPersonaFisica(nombre, apellido, documento);
            // creo un colaborador con la persona, todavia no tiene la contribucion
            Colaborador colaborador = this.crearColaborador(nuevaPersonaFisica);

            this.agregarColaboradorNuevoAExistentes(colaborador, nuevaPersonaFisica, colaboradoresExistentes, colaboradoresExistentesMap, personasFisicasExistentesMap, numeroDocumentoString);
            Email emailPersona = new Email(mail);
            EntityManager em = BDutils.getEntityManager();
            UsuarioDAO usuarioDAO = new UsuarioDAO(em);
            String usuarioGenerado = GeneradorUsuario.generarNombreUsuario(nuevaPersonaFisica.getNombre(), nuevaPersonaFisica.getApellido());
            String contraseniaGenerada = GeneradorContrasenia.generarContrasenia(10);
            Usuario usuarioNuevo = new Usuario(nuevaPersonaFisica, usuarioGenerado, BCrypt.hashpw(contraseniaGenerada, BCrypt.gensalt()));
            usuarioDAO.save(usuarioNuevo);
            EnvioMail envio = new EnvioMail();
            envio.enviarEmail(emailPersona,"Carga realizada correctamente","Tu usuario fue cargado correctamente, sus credenciales son las siguientes: " +
                    "\nNOMBRE DE USUARIO: " + usuarioGenerado + "\nCONTRASEÑA: " + contraseniaGenerada ,"hola");
            LoggerToFile.logInfo("Colaborador creado! " + contraseniaGenerada);
            return colaborador;
        } else {
            LoggerToFile.logInfo("Colaborador ya existente!");
            return colaboradoresExistentesMap.get(numeroDocumentoString);
        }
    }

    public void agregarColaboradorNuevoAExistentes(Colaborador nuevoColaborador, Persona_fisica nuevaPersonaFisica, List<Colaborador> colaboradoresExistentes, Map<String, Colaborador> colaboradoresExistentesMap, Map<String, Persona> personasFisicasExistentesMap, String numeroDocumentoString){
        EntityManager em = BDutils.getEntityManager();
        ColaboradorDAO colaboradorDAO = new ColaboradorDAO(em);
        LoggerToFile.logInfo("Agregando colaborador a existentes");
        colaboradoresExistentes.add(nuevoColaborador);
        personasFisicasExistentesMap.put(numeroDocumentoString, nuevaPersonaFisica);
        colaboradoresExistentesMap.put(numeroDocumentoString, nuevoColaborador);
        colaboradorDAO.save(nuevoColaborador);
        LoggerToFile.logInfo("Colaborador persistido con exito");
    }
}
