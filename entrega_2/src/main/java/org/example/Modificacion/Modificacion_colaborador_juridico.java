package org.example.Modificacion;

import org.example.Colaborador.*;

public class Modificacion_colaborador_juridico {
    public void modificar_lat_dom(Colaborador colaborador, String lat_dom){
        colaborador.getPersona().getDomicilio().setLatitud(lat_dom);
    }
    public void modificar_long_dom(Colaborador colaborador, String long_dom){
        colaborador.getPersona().getDomicilio().setLongitud(long_dom);
    }
    public void modificar_pais(Colaborador colaborador, Pais pais){
        colaborador.getPersona().getDomicilio().setPais(pais);
    }
    public void modificar_ciudad(Colaborador colaborador, Ciudad ciudad){
        colaborador.getPersona().getDomicilio().setCiudad(ciudad);
    }
    public void modificar_direccion(Colaborador colaborador,String direccion) {
        colaborador.getPersona().getDomicilio().setDireccion(direccion);
    }
    public void modificar_medios(Colaborador colaborador, Medio_contacto[] mediosContacto) {
        colaborador.getPersona().setMediosDeContacto(mediosContacto);
    }
    public void modificar_razon(Colaborador colaborador,String razon){
        ((Persona_juridica) colaborador.getPersona()).setRazonSocial(razon);
    }
    public void modificar_tipo_juridico(Colaborador colaborador,Tipo_juridico tipo){
        ((Persona_juridica) colaborador.getPersona()).setTipoJuridico(tipo);
    }
    public void modificar_rubro(Colaborador colaborador,String rubro){
        ((Persona_juridica)colaborador.getPersona()).setRubro(rubro);
    }


}
