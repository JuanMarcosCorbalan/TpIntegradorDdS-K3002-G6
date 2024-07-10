package org.example.Persona;

public class Documento_identidad {
    String numeroDocumento;
    Tipo_documento Tipo;

    public Documento_identidad(String numeroDocumento, Tipo_documento Tipo) {
        this.numeroDocumento = numeroDocumento;
        this.Tipo = Tipo;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }
    public String getNumeroDocumento() {
        return numeroDocumento;
    }
    public void setTipo(Tipo_documento Tipo) {
        this.Tipo = Tipo;
    }
    public Tipo_documento getTipo() {
        return Tipo;
    }


}
