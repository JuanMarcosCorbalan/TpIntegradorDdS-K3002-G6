package org.example.DAO;

import org.example.Colaborador.Colaborador;
import org.example.Formas_contribucion.Distribucion_viandas;
import org.example.Formas_contribucion.RegistrarPersonasSV;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class DistribucionTarjetasDAO {

    private EntityManager entityManager;

    public DistribucionTarjetasDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public List<RegistrarPersonasSV> findAllByColaborador(Colaborador colaborador) {
        List<RegistrarPersonasSV> registrarPersonasSV;
        try {
            registrarPersonasSV = entityManager
                    .createQuery("select d from RegistrarPersonasSV d where d.colaborador = :colaborador", RegistrarPersonasSV.class)
                    .setParameter("colaborador", colaborador)
                    .getResultList();
        } catch (NoResultException e) {
            registrarPersonasSV = null;
        }
        return registrarPersonasSV;
    }

}