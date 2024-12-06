package org.example.Persona;


import javax.persistence.*;

@Entity
@Table (name = "Documento")
public class Documento_identidad {
    String numeroDocumento;

    @Enumerated(EnumType.STRING)
    Tipo_documento Tipo;

    /************************/
    //PARA PERSISTENCIA
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    public Documento_identidad(){}
    /************************/

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
