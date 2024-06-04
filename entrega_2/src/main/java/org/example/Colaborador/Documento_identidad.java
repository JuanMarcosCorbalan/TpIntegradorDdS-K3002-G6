package org.example.Colaborador;

public class Documento_identidad {
    String numeroDocumento;
    Tipo_documento Tipo;

    public Documento_identidad(String numeroDocumento, Tipo_documento Tipo) {
        this.numeroDocumento = numeroDocumento;
        this.Tipo = Tipo;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Tipo_documento getTipo() {
        return Tipo;
    }

    public void setTipo(Tipo_documento tipo) {
        Tipo = tipo;
    }
}
