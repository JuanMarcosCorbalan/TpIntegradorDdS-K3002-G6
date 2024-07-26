package org.example.ReporteSemanal;

import org.example.Colaborador.Colaborador;
import org.example.Heladeras.Heladera;
import org.example.Persona.Persona_fisica;
import org.example.Persona.Persona_juridica;

import java.util.Date;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

public class GeneradorReporte {
    private int contadorReportes = 0;
    private List<Heladera> heladeras;
    private List<Colaborador> colaboradores;

    public GeneradorReporte(List<Heladera> heladeras, List<Colaborador> colaboradores) {
        this.heladeras = heladeras;
        this.colaboradores = colaboradores;
    }
    public Reporte generarReporte(){
        StringBuilder contenido = new StringBuilder();
        contenido.append("CANTIDAD_FALLAS_POR_HELADERA\n");
        for (Heladera heladera : heladeras){
            contenido.append("Heladera ").append(heladera.getIdHeladera())
                    .append(": ").append(heladera.getCantidadFallas()).append("\n");
        }
        contenido.append("\nCANTIDAD_DE_VIANDAS_RETIRADAS_POR_HELADERA\n");
        for (Heladera heladera: heladeras){
            contenido.append("Heladera ").append(heladera.getIdHeladera())
                    .append(": ").append(heladera.cantidadViandasRetiradas()).append("\n");
        }
        contenido.append("\nCANTIDAD_DE_VIANDAS_DONADAS_POR_HELADERA\n");
        for (Heladera heladera : heladeras){
            contenido.append("Heladera: ").append(heladera.getIdHeladera())
                    .append(": ").append(heladera.getCantidadViandasDonadas()).append("\n");
        }
        contenido.append("\nCANTIDAD_DE_VIANDAS_DONADAS_POR_COLABORADOR\n");
        for (Colaborador colaborador : colaboradores){
            if(colaborador.getPersona_colaboradora() instanceof Persona_fisica personaFisica) {
                contenido.append("Colaborador: ").append(personaFisica.getNombre()).append(" ").append(personaFisica.getApellido())
                    .append(": ").append(colaborador.getCantidadViandasDonadas()).append("\n");
            }

        }
        Date fechaGeneracion = new Date();
        String filePath = guardarReporteEnArchivo(contenido.toString(), fechaGeneracion);

        return new Reporte(++contadorReportes, contenido.toString(), fechaGeneracion, filePath);
    }

    private String guardarReporteEnArchivo(String contenido, Date fechaGeneracion){
        String nombreArchivo = "reporte_semanal_" + fechaGeneracion.getTime()+".txt";
        try (FileWriter writer = new FileWriter(nombreArchivo)){
            writer.write(contenido);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nombreArchivo;
    }
}

