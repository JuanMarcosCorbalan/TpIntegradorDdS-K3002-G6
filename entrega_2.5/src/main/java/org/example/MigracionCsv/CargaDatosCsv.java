package org.example.MigracionCsv;

import com.sendgrid.helpers.mail.objects.Email;
import org.example.Colaborador.Colaborador;
import org.example.Formas_contribucion.*;
import org.example.Funcionalidades.EnvioMail;
import org.example.Persona.Documento_identidad;
import org.example.Persona.Persona;
import org.example.Persona.Persona_fisica;
import org.example.Persona.Tipo_documento;

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
        switch (formaColaboracion) {
            case "DINERO" -> {
                Donacion_dinero donacionDinero = new Donacion_dinero(cantidad, Tipos_frecuencia.DONACION_UNICA, fechaColaboracion);
                colaborador.agregarContribucion(donacionDinero);
            }
            case "REDISTRIBUCION_VIANDAS" -> {
                Distribucion_viandas distribucionViandas = new Distribucion_viandas(cantidad, fechaColaboracion);
                colaborador.agregarContribucion(distribucionViandas);
            }
            case "ENTREGA_TARJETAS" -> {
                RegistrarPersonasSV entregaTarjetas = new RegistrarPersonasSV(cantidad, fechaColaboracion);
                colaborador.agregarContribucion(entregaTarjetas);
            }
            case "DONACION_VIANDAS" -> {
                // no seteo la cantidad de viandas porque deberia agregar la vianda a la lista de viandas pero seria una vianda vacia
                Donacion_viandas donacionViandas = new Donacion_viandas(fechaColaboracion);
                colaborador.agregarContribucion(donacionViandas);
            }
            default -> throw new Error("FORMA DE COLABORACION INVALIDA");
        }
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
        if (!existeColaborador){
            // creo los objetos con cada uno de los parametros recibidos
            // para crear a la persona necesito el objeto documento
            Documento_identidad documento = this.crearDocumento(numeroDocumentoString, tipoDocumento);

            // para crear al colaborador necesito a la persona
            Persona_fisica nuevaPersonaFisica = this.crearPersonaFisica(nombre, apellido, documento);
            // creo un colaborador con la persona, todavia no tiene la contribucion
            Colaborador colaborador = this.crearColaborador(nuevaPersonaFisica);

            this.agregarColaboradorNuevoAExistentes(colaborador, nuevaPersonaFisica, colaboradoresExistentes, colaboradoresExistentesMap, personasFisicasExistentesMap, numeroDocumentoString);
            Email emailPersona = new Email(mail);
            EnvioMail.enviarEmail(emailPersona);
            return colaborador;
        } else {
            return colaboradoresExistentesMap.get(numeroDocumentoString);
        }
    }

    public void agregarColaboradorNuevoAExistentes(Colaborador nuevoColaborador, Persona_fisica nuevaPersonaFisica, List<Colaborador> colaboradoresExistentes, Map<String, Colaborador> colaboradoresExistentesMap, Map<String, Persona> personasFisicasExistentesMap, String numeroDocumentoString){
        colaboradoresExistentes.add(nuevoColaborador);
        personasFisicasExistentesMap.put(numeroDocumentoString, nuevaPersonaFisica);
        colaboradoresExistentesMap.put(numeroDocumentoString, nuevoColaborador);
    }
}
