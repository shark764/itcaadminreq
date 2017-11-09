/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itca.requerimientos.controller.facade.request;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.itca.requerimientos.controller.facade.AbstractFacade;
import org.itca.requerimientos.model.entities.TInsumoUtilizado;

/**
 *
 * @author dfhernandez
 */
@Stateless
public class TInsumoUtilizadoFacade extends AbstractFacade<TInsumoUtilizado> {
    @PersistenceContext(unitName = "AdminReqPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TInsumoUtilizadoFacade() {
        super(TInsumoUtilizado.class);
    }

    public List<TInsumoUtilizado> usedRange(Short start, Short end, int[] range)
    {
        Query q = em.createNamedQuery("TInsumoUtilizado.usedRange")
        		.setParameter("start", start)
        		.setParameter("end", end)
        		.setMaxResults(range[1] - range[0])
        		.setFirstResult(range[0]);
        List<TInsumoUtilizado> list = q.getResultList();
        return list;
    }

    public List<TInsumoUtilizado> wastedRange(Short start, Short end, int[] range)
    {
        Query q = em.createNamedQuery("TInsumoUtilizado.wastedRange")
        		.setParameter("start", start)
        		.setParameter("end", end)
        		.setMaxResults(range[1] - range[0])
        		.setFirstResult(range[0]);
        List<TInsumoUtilizado> list = q.getResultList();
        return list;
    }

    public List<TInsumoUtilizado> findByEquipment(Long id, int[] range)
    {
        Query q = em.createNamedQuery("TInsumoUtilizado.findByEquipment")
        		.setParameter("id", id)
        		.setMaxResults(range[1] - range[0])
        		.setFirstResult(range[0]);
        List<TInsumoUtilizado> list = q.getResultList();
        return list;
    }

    public List<TInsumoUtilizado> entryRange(Date start, Date end, int[] range)
    {
        Query q = em.createNamedQuery("TInsumoUtilizado.entryRange")
        		.setParameter("start", start)
        		.setParameter("end", end)
        		.setMaxResults(range[1] - range[0])
        		.setFirstResult(range[0]);
        List<TInsumoUtilizado> list = q.getResultList();
        return list;
    }

    public List<TInsumoUtilizado> findByEquipmentUsed(Long id, int[] range)
    {
        Query q = em.createNamedQuery("TInsumoUtilizado.findByEquipmentUsed")
        		.setParameter("id", id)
        		.setMaxResults(range[1] - range[0])
        		.setFirstResult(range[0]);
        List<TInsumoUtilizado> list = q.getResultList();
        return list;
    }

    public List<TInsumoUtilizado> findByResourceUsed(Short id, int[] range)
    {
        Query q = em.createNamedQuery("TInsumoUtilizado.findByResourceUsed")
        		.setParameter("id", id)
        		.setMaxResults(range[1] - range[0])
        		.setFirstResult(range[0]);
        List<TInsumoUtilizado> list = q.getResultList();
        return list;
    }
    
}
