/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itca.requerimientos.controller.facade.inventory;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.itca.requerimientos.controller.facade.AbstractFacade;
import org.itca.requerimientos.model.entities.CtlInventarioDefectuoso;
import org.itca.requerimientos.model.entities.jasper.InventarioDefectuosoJasper;

/**
 *
 * @author dfhernandez
 */
@Stateless
public class CtlInventarioDefectuosoFacade extends AbstractFacade<CtlInventarioDefectuoso> {
    @PersistenceContext(unitName = "AdminReqPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CtlInventarioDefectuosoFacade() {
        super(CtlInventarioDefectuoso.class);
    }

    public List<CtlInventarioDefectuoso> entryRange(Date start, Date end, int[] range)
    {
        Query q = em.createNamedQuery("CtlInventarioDefectuoso.entryRange")
        		.setParameter("start", start)
        		.setParameter("end", end)
        		.setMaxResults(range[1] - range[0] + 1)
        		.setFirstResult(range[0]);
        List<CtlInventarioDefectuoso> list = q.getResultList();
        return list;
    }

    public List<CtlInventarioDefectuoso> findByEquipmentModel(Short id, int[] range)
    {
        Query q = em.createNamedQuery("CtlInventarioDefectuoso.findByEquipmentModel")
        		.setParameter("id", id)
        		.setMaxResults(range[1] - range[0] + 1)
        		.setFirstResult(range[0]);
        List<CtlInventarioDefectuoso> list = q.getResultList();
        return list;
    }

    public List<InventarioDefectuosoJasper> findAllForDefectiveInventoryReport()
    {
        Query q = em.createNamedQuery("CtlInventarioDefectuoso.defectiveInventoryReport");
        List<InventarioDefectuosoJasper> list = q.getResultList();
        return list;
    }
    
}
