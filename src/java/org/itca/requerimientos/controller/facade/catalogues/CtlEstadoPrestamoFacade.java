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
import org.itca.requerimientos.model.entities.CtlEstadoPrestamo;

/**
 *
 * @author dfhernandez
 */
@Stateless
public class CtlEstadoPrestamoFacade extends AbstractFacade<CtlEstadoPrestamo> {
    @PersistenceContext(unitName = "AdminReqPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CtlEstadoPrestamoFacade() {
        super(CtlEstadoPrestamo.class);
    }

    public CtlEstadoPrestamo findByCodigo(String codigo)
    {
        Query q = em.createNamedQuery("CtlEstadoPrestamo.findByCodigo");
        q.setParameter("codigo", codigo);
        return (CtlEstadoPrestamo) q.getSingleResult();
    }
    
}
