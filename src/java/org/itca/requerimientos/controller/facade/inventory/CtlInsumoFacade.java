/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itca.requerimientos.controller.facade.inventory;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.itca.requerimientos.controller.facade.AbstractFacade;
import org.itca.requerimientos.model.entities.CtlInsumo;

/**
 *
 * @author dfhernandez
 */
@Stateless
public class CtlInsumoFacade extends AbstractFacade<CtlInsumo> {
    @PersistenceContext(unitName = "AdminReqPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CtlInsumoFacade() {
        super(CtlInsumo.class);
    }

    public List<CtlInsumo> nonStock(Integer min, int[] range)
    {
        Query q = em.createNamedQuery("CtlInsumo.nonStock")
        		.setParameter("min", min)
        		.setMaxResults(range[1] - range[0])
        		.setFirstResult(range[0]);
        List<CtlInsumo> list = q.getResultList();
        return list;
    }

    public List<CtlInsumo> stockRange(Integer start, Integer end, int[] range)
    {
        Query q = em.createNamedQuery("CtlInsumo.stockRange")
        		.setParameter("start", start)
        		.setParameter("end", end)
        		.setMaxResults(range[1] - range[0])
        		.setFirstResult(range[0]);
        List<CtlInsumo> list = q.getResultList();
        return list;
    }
    
}
