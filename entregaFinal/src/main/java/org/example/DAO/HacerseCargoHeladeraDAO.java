package org.example.DAO;

import org.example.Colaborador.Colaborador;
import org.example.Conversores.DonacionViandaHistorial;
import org.example.Conversores.HacerseCargoHeladeraHistorial;
import org.example.Formas_contribucion.Donacion_viandas;
import org.example.Formas_contribucion.HacerseCargoHeladera;
import org.example.DAO.HacerseCargoHeladeraDAO;
import org.example.Heladeras.EstadoHeladera;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HacerseCargoHeladeraDAO {

    private EntityManager entityManager;

    public HacerseCargoHeladeraDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public List<HacerseCargoHeladera> findAllByColaborador(Colaborador colaborador) {
        List<HacerseCargoHeladera> hacerseCargoHeladera;
        try {
            hacerseCargoHeladera = entityManager
                    .createQuery("select d from HacerseCargoHeladera d where d.colaborador = :colaborador", HacerseCargoHeladera.class)
                    .setParameter("colaborador", colaborador)
                    .getResultList();
        } catch (NoResultException e) {
            hacerseCargoHeladera = null;
        }
        return hacerseCargoHeladera;
    }

    public List<HacerseCargoHeladeraHistorial> cargarHistorial(List<HacerseCargoHeladera> heladerasACargo){
        List<HacerseCargoHeladeraHistorial> heladerasHistorial = new ArrayList<>();
        for(HacerseCargoHeladera heladera : heladerasACargo){
            String nombre_heladera = heladera.getHeladera().getPuntoUbicacion().getNombre();
            LocalDate fecha_contribucion = heladera.getFecha_contribucion();
            EstadoHeladera estado = heladera.getHeladera().getEstado();

            HacerseCargoHeladeraHistorial dvh = new HacerseCargoHeladeraHistorial(nombre_heladera,fecha_contribucion,estado);
            heladerasHistorial.add(dvh);
        }
        return heladerasHistorial;
    }

    public List<HacerseCargoHeladeraHistorial> obtenerHistorial(Colaborador colaborador) {
        List<HacerseCargoHeladera> heladerasACargo = this.findAllByColaborador(colaborador);
        return this.cargarHistorial(heladerasACargo);
    }

}
