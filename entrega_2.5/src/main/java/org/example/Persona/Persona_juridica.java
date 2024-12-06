package org.example.Persona;
import java.util.List;
import javax.persistence.*;

import java.util.List;

@Entity
public class Persona_juridica extends Persona{

    String razon_social;
    String rubro;

    @Enumerated(EnumType.STRING)
    Tipo_juridico tipo;


    public Persona_juridica(Domicilio domicilio, List<Medio_contacto> mediosContacto, String razon_social, Tipo_juridico tipo, String rubro)
    {
        super(domicilio,mediosContacto);
        this.razon_social = razon_social;
        this.tipo = tipo;
        this.rubro = rubro;
    }

    public Persona_juridica(Domicilio domicilio, List<Medio_contacto> mediosContacto, String razon_social, Tipo_juridico tipo)
    {
        super(domicilio,mediosContacto);
        this.razon_social = razon_social;
        this.tipo = tipo;
    }




    public Persona_juridica(){}

    public void setRazonSocial(String razon_social){
        this.razon_social = razon_social;
    }
    public void setTipoJuridico(Tipo_juridico tipo){
        this.tipo = tipo;
    }
    public void setRubro(String rubro){
        this.rubro = rubro;
    }

    public String getRazon_social() {
        return razon_social;
    }
}
