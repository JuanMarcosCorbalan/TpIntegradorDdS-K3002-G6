package org.example.ReporteSemanal;

import java.util.Date;

public class Reporte {
    private int id;
    private String contenido;
    private Date fechaGeneracion;
    private String filePath;  // Nueva ruta del archivo

    public Reporte(Integer id, String contenido, Date generationDate, String filePath) {
        this.id = id;
        this.contenido = contenido;
        this.fechaGeneracion = generationDate;
        this.filePath = filePath;
    }

    public int getId() {
        return id;
    }

    public String getContenido() {
        return contenido;
    }

    public Date getFechaGeneracion() {
        return fechaGeneracion;
    }

    public String getFilePath() {
        return filePath;
    }
}

