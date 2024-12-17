package org.example;

import org.example.DAO.IncidenteDAO;
import org.example.DAO.TecnicoDAO;
import org.example.Persona.Persona_fisica;
import org.example.Personal.Tecnico;
import org.example.Utils.BDutils;
import org.example.Validadores_Sensores.FallaTecnica;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class prueba_tecnico {

    public static void main(String[] args){

        EntityManager em = BDutils.getEntityManager();
        IncidenteDAO dao = new IncidenteDAO(em);
        FallaTecnica fallaTecnica = (FallaTecnica) dao.findById(3);

        List<Tecnico> tecnicos = new ArrayList<Tecnico>();

        TecnicoDAO tecnicoDAO = new TecnicoDAO(em);

        Tecnico tecnico1 = tecnicoDAO.findById(11);
        Tecnico tecnico2 = tecnicoDAO.findById(12);

        tecnicos.add(tecnico1);
        tecnicos.add(tecnico2);

        fallaTecnica.asignarTecnico(fallaTecnica.getHeladera().getPuntoUbicacion(),tecnicos);


        Persona_fisica persona = (Persona_fisica) fallaTecnica.getTecnicoAsignado().getPersona();
        String nombre = persona.getNombre();

        dao.update(fallaTecnica);



    }
}
