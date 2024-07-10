package org.example.Modificacion;

import org.example.Colaborador.Colaborador;
import org.example.Persona.*;

public class Modificacion_colaborador_juridico {
    public void modificar_lat_dom(Colaborador colaborador, String lat_dom){
        colaborador.getPersona_colaboradora().getDomicilio().setLatitud(lat_dom);
    }
    public void modificar_long_dom(Colaborador colaborador, String long_dom){
        colaborador.getPersona_colaboradora().getDomicilio().setLongitud(long_dom);
    }
    public void modificar_pais(Colaborador colaborador, Pais pais){
        colaborador.getPersona_colaboradora().getDomicilio().setPais(pais);
    }
    public void modificar_ciudad(Colaborador colaborador, Ciudad ciudad){
        colaborador.getPersona_colaboradora().getDomicilio().setCiudad(ciudad);
    }
    public void modificar_direccion(Colaborador colaborador,String direccion) {
        colaborador.getPersona_colaboradora().getDomicilio().setDireccion(direccion);
    }
    public void modificar_medios(Colaborador colaborador, Medio_contacto[] mediosContacto) {
        colaborador.getPersona_colaboradora().setMediosDeContacto(mediosContacto);
    }
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
