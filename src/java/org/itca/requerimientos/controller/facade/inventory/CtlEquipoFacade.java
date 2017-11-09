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
import org.itca.requerimientos.model.entities.CtlEquipo;

/**
 *
 * @author dfhernandez
 */
@Stateless
public class CtlEquipoFacade extends AbstractFacade<CtlEquipo> {
    @PersistenceContext(unitName = "AdminReqPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CtlEquipoFacade() {
        super(CtlEquipo.class);
    }

    public List<CtlEquipo> nonStock(Integer min, int[] range)
    {
        Query q = em.createNamedQuery("CtlEquipo.nonStock")
        		.setParameter("min", min)
        		.setMaxResults(range[1] - range[0])
        		.setFirstResult(range[0]);
        List<CtlEquipo> list = q.getResultList();
        return list;
    }

    public List<CtlEquipo> entryRange(Date start, Date end, int[] range)
    {
        Query q = em.createNamedQuery("CtlEquipo.entryRange")
        		.setParameter("start", start)
        		.setParameter("end", end)
        		.setMaxResults(range[1] - range[0])
        		.setFirstResult(range[0]);
        List<CtlEquipo> list = q.getResultList();
        return list;
    }

    public List<CtlEquipo> findByProvider(Short id, int[] range)
    {
        Query q = em.createNamedQuery("CtlEquipo.findByProvider")
        		.setParameter("id", id)
        		.setMaxResults(range[1] - range[0])
        		.setFirstResult(range[0]);
        List<CtlEquipo> list = q.getResultList();
        return list;
    }

    public List<CtlEquipo> stockRange(Integer start, Integer end, int[] range)
    {
        Query q = em.createNamedQuery("CtlEquipo.stockRange")
        		.setParameter("start", start)
        		.setParameter("end", end)
        		.setMaxResults(range[1] - range[0])
        		.setFirstResult(range[0]);
        List<CtlEquipo> list = q.getResultList();
        return list;
    }

    public List<CtlEquipo> findByModel(Short id, int[] range)
    {
        Query q = em.createNamedQuery("CtlEquipo.findByModel")
        		.setParameter("id", id)
        		.setMaxResults(range[1] - range[0])
        		.setFirstResult(range[0]);
        List<CtlEquipo> list = q.getResultList();
        return list;
    }
    
}
