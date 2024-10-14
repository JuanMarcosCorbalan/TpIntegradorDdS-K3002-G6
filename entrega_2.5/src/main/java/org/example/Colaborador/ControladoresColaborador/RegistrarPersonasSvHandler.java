package org.example.Colaborador.ControladoresColaborador;

import org.example.Colaborador.Colaborador;

public class RegistrarPersonasSvHandler {
//  public void registrarPersonaSv(String nombre, String apellido, boolean situacionCalle, String domicilioString, Integer menoresACargo){
//

    public static void registrarPersonaSv(Colaborador colaborador, String nombre, String apellido, boolean situacionCalle, String domicilioString, Integer menoresACargo){
        colaborador.registrarPersonaSv(nombre, apellido,situacionCalle,domicilioString,menoresACargo);
    }
}
