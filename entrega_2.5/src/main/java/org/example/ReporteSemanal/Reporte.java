package org.example.ReporteSemanal;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private String contenido;

    private LocalDate fechaGeneracion;

    private String filePath;  // Nueva ruta del archivo

    public Reporte(String contenido, LocalDate generationDate, String filePath) {
        this.contenido = contenido;
        this.fechaGeneracion = generationDate;
        this.filePath = filePath;
    }

    public Reporte() {
    }

    public String getContenido() {
        return contenido;
    }

    public LocalDate getFechaGeneracion() {
        return fechaGeneracion;
    }

    public Long getId() {
        return id;
    }

    public String getFilePath() {
        return filePath;
    }
}

