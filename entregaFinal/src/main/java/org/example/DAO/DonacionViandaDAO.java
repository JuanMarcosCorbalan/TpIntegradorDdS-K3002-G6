package org.example.DAO;

import org.example.Colaborador.Colaborador;
import org.example.Conversores.DonacionViandaHistorial;
import org.example.Formas_contribucion.Donacion_viandas;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class DonacionViandaDAO {

    private EntityManager entityManager;

    public DonacionViandaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public List<Donacion_viandas> findAllByColaborador(Colaborador colaborador) {
        List<Donacion_viandas> donacionViandas;
        try {
            donacionViandas = entityManager
                    .createQuery("select d from Donacion_viandas d  where d.colaborador = :colaborador", Donacion_viandas.class)
                    .setParameter("colaborador", colaborador)
                    .getResultList();
        } catch (NoResultException e) {
            donacionViandas = null;
        }
        return donacionViandas;
    }

    public List<DonacionViandaHistorial> cargarHistorial(List<Donacion_viandas> donacionesViandas){
        List<DonacionViandaHistorial> donacionesHistorial = new ArrayList<>();
        for(Donacion_viandas donacion : donacionesViandas){
            String nombre_heladera = donacion.getHeladera() != null ? donacion.getHeladera().getNombre(): "Sin especificar" ;
            String nombre_vianda = donacion.getVianda() != null ? donacion.getVianda().getNombre(): "Sin especificar";

            DonacionViandaHistorial dvh = new DonacionViandaHistorial(nombre_heladera,nombre_vianda,donacion.getEstado());
            donacionesHistorial.add(dvh);
        }
        return donacionesHistorial;
    }

    public List<DonacionViandaHistorial> obtenerHistorial(Colaborador colaborador) {
        List<Donacion_viandas> donacionViandas = this.findAllByColaborador(colaborador);
        return this.cargarHistorial(donacionViandas);
    }
}
