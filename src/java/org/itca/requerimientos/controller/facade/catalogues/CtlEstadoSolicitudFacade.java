/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itca.requerimientos.controller.facade.catalogues;

import org.itca.requerimientos.controller.facade.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.itca.requerimientos.model.entities.CtlEstadoSolicitud;

/**
 *
 * @author dfhernandez
 */
@Stateless
public class CtlEstadoSolicitudFacade extends AbstractFacade<CtlEstadoSolicitud> {
    @PersistenceContext(unitName = "AdminReqPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CtlEstadoSolicitudFacade() {
        super(CtlEstadoSolicitud.class);
    }

    public CtlEstadoSolicitud findByCodigo(String codigo)
    {
        Query q = em.createNamedQuery("CtlEstadoSolicitud.findByCodigo")
                .setParameter("codigo", codigo);
        return (CtlEstadoSolicitud) q.getSingleResult();
    }
    
}
