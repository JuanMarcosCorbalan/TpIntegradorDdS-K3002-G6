package org.example.Suscripcion;

import org.example.Colaborador.Colaborador;
import org.example.Colaborador.Forma_colaborar;
import org.example.Heladeras.Heladera;
import org.example.Heladeras.PuntoUbicacion;
import org.example.Heladeras.Vianda;
import org.example.Persona.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.example.Colaborador.Forma_colaborar.*;
import static org.example.Persona.Tipo_documento.DNI;

public class PruebaSuscripcion {

    public static void main(String[] args){
        Pais pais = new Pais("Argentina");
        Ciudad ciudad = new Ciudad("Buenos Aires",pais);
        Localidad localidad = new Localidad("Palermo",ciudad);
        Domicilio domicilio = new Domicilio("-34,583200","-58,429600","Honduras 4700",localidad);
        Documento_identidad doc = new Documento_identidad("19723832",DNI);
        Telegram tg = new Telegram();
        tg.setId_chat("1257216385");
        tg.setId_user("Robert");
        List<Medio_contacto> medios = List.of(tg);
        Persona_fisica perColab = new Persona_fisica("Pablo","Diaz","17-04-1969",doc,medios,domicilio);
        tg.setPersona(perColab);
        List<Forma_colaborar> forma = List.of(DONACION_DINERO,DONACION_VIANDAS,DISTRIBUCION_VIANDAS);
        Colaborador colab = new Colaborador(perColab,forma);

        //UN COLABORADOR
        PuntoUbicacion ubi1 = new PuntoUbicacion("-34.59949215153663","-58.420667586506816","Av Medrano 900","UTN Medrano",localidad) ;
        LocalDate fechaEspecifica = LocalDate.of(2024,10,2);
        String codigoUnico = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        Heladera heladera = new Heladera (codigoUnico, ubi1, fechaEspecifica, 7, 2, null,150);

        //SUSCRIPCION
        //CADA SUSCRIPCION ESTA EN UNA HELADERA, PUEDO AGARRAR TODAS ESAS Y DARLE A NOTIFICAR, ESTAS CREAN EL MENSAJE DE AVISO
        //Y SE LO MANDAN AL COLABORADOR
        //AL CREAR LA SUSCRIPCION ESTA DEBE ANIADIRSE A LA HELADERA
        colab.suscribirseAHeladera(heladera,TipoSuscripcion.FALTANNVIANDAS,149);

        Vianda vianda = new Vianda("milanesa",LocalDate.of(2025,5,8),"350","450");

        System.out.println(heladera.getCantidadViandasActuales());

        heladera.aniadirVianda(vianda);

        System.out.println(heladera.getCantidadViandasActuales());

        return;







    }
}
