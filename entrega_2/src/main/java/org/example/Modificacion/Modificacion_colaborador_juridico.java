package org.example.Modificacion;

import org.example.Colaborador.*;

public class Modificacion_colaborador_juridico extends Modificacion_persona{
    public void modificar_razon(Colaborador colaborador,String razon){
        ((Persona_juridica) colaborador.getPersona_colaboradora()).setRazonSocial(razon);
    }
    public void modificar_tipo_juridico(Colaborador colaborador,Tipo_juridico tipo){
        ((Persona_juridica) colaborador.getPersona_colaboradora()).setTipoJuridico(tipo);
    }
    public void modificar_rubro(Colaborador colaborador,String rubro){
        ((Persona_juridica)colaborador.getPersona_colaboradora()).setRubro(rubro);
    }


}
