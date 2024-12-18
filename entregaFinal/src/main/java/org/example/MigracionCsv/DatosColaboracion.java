package org.example.MigracionCsv;

public class DatosColaboracion {
    private String tipoDoc;
    private Integer numeroDocumento;
    private String nombre;
    private String apellido;
    private String mail;
    private String fechaColaboracionString;
    private String formaColaboracion;
    private Integer cantidad;

    // Getters y Setters para cada campo
    public String getTipoDoc() { return tipoDoc; }
    public void setTipoDoc(String tipoDoc) { this.tipoDoc = tipoDoc; }

    public Integer getNumeroDocumento() { return numeroDocumento; }
    public void setNumeroDocumento(Integer numeroDocumento) { this.numeroDocumento = numeroDocumento; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }

    public String getFechaColaboracionString() { return fechaColaboracionString; }
    public void setFechaColaboracionString(String fechaColaboracionString) { this.fechaColaboracionString = fechaColaboracionString; }

    public String getFormaColaboracion() { return formaColaboracion; }
    public void setFormaColaboracion(String formaColaboracion) { this.formaColaboracion = formaColaboracion; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }


}