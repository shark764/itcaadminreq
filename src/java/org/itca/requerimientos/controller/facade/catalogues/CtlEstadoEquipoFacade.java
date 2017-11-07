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
import org.itca.requerimientos.model.entities.CtlEstadoEquipo;

/**
 *
 * @author dfhernandez
 */
@Stateless
public class CtlEstadoEquipoFacade extends AbstractFacade<CtlEstadoEquipo> {
    @PersistenceContext(unitName = "AdminReqPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CtlEstadoEquipoFacade() {
        super(CtlEstadoEquipo.class);
    }
    
}
