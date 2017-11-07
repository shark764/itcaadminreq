/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itca.requerimientos.controller.facade.catalogues;

import java.util.List;
import org.itca.requerimientos.controller.facade.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.itca.requerimientos.model.entities.CtlModeloEquipo;
import org.itca.requerimientos.model.entities.jasper.ModeloEquipoJasper;

/**
 *
 * @author dfhernandez
 */
@Stateless
public class CtlModeloEquipoFacade extends AbstractFacade<CtlModeloEquipo> {
    @PersistenceContext(unitName = "AdminReqPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CtlModeloEquipoFacade() {
        super(CtlModeloEquipo.class);
    }

    public List<ModeloEquipoJasper> findAllForStockByEquipmentModelReport()
    {
        Query q = em.createNamedQuery("CtlModeloEquipo.stockByEquipmentModelReport");
        List<ModeloEquipoJasper> list = q.getResultList();
        return list;
    }
    
}
