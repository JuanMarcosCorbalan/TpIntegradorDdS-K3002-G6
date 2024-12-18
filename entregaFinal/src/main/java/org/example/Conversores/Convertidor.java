package org.example.Conversores;

import org.example.DAO.DomicilioDAO;
import org.example.DAO.LocalidadDAO;
import org.example.Persona.Ciudad;
import org.example.Persona.Domicilio;
import org.example.Persona.Localidad;
import org.example.Persona.Pais;

import javax.persistence.EntityManager;


public class Convertidor {

    private EntityManager em;

    public Convertidor(EntityManager entityManager) {
        this.em = entityManager;
    }

    public Domicilio DTOtoDomicilio(String calle,String altura,String nombre_localidad,String nombre_ciudad,String nombre_pais ) {
        DomicilioDAO domicilioDAO = new DomicilioDAO(em);
        return domicilioDAO.findOrCreate(calle + " " + altura,nombre_localidad,nombre_ciudad,nombre_pais);
    }
}
