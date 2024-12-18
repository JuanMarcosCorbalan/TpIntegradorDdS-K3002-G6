package org.example.DAO;

import org.example.Colaborador.Colaborador;
import org.example.Conversores.DistribucionViandaHistorial;
import org.example.Conversores.DonacionViandaHistorial;
import org.example.Formas_contribucion.Distribucion_viandas;
import org.example.Formas_contribucion.Donacion_viandas;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class DistribucionViandasDAO {

    private EntityManager entityManager;

    public DistribucionViandasDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public List<Distribucion_viandas> findAllByColaborador(Colaborador colaborador) {
        List<Distribucion_viandas> distribucionViandas;
        try {
            distribucionViandas = entityManager
                    .createQuery("select d from Distribucion_viandas d where d.colaborador = :colaborador", Distribucion_viandas.class)
                    .setParameter("colaborador", colaborador)
                    .getResultList();
        } catch (NoResultException e) {
            distribucionViandas = null;
        }
        return distribucionViandas;
    }

    public List<DistribucionViandaHistorial> cargarHistorial(List<Distribucion_viandas> distribucionViandas){
        List<DistribucionViandaHistorial> distribucionHistorial = new ArrayList<>();
        for(Distribucion_viandas distribucion : distribucionViandas){
            String heladera_destino = distribucion.getHeladeraDestino().getNombre();
            String heladera_origen = distribucion.getHeladeraOrigen().getNombre();

            DistribucionViandaHistorial dvh = new DistribucionViandaHistorial(distribucion.getEstado(),distribucion.getCantidadViandasAMover(),distribucion.getMotivo(),heladera_origen,heladera_origen,distribucion.getFecha_contribucion(),distribucion.getFechaDistribucion());
            distribucionHistorial.add(dvh);
        }
        return distribucionHistorial;
    }

    public List<DistribucionViandaHistorial> obtenerHistorial(Colaborador colaborador) {
        List<Distribucion_viandas> distribucionViandas = this.findAllByColaborador(colaborador);
        return this.cargarHistorial(distribucionViandas);
    }

}
