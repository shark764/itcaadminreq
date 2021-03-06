/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itca.requerimientos.controller.facade.maintenance;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.itca.requerimientos.controller.facade.AbstractFacade;
import org.itca.requerimientos.model.entities.TMantenimiento;

/**
 *
 * @author dfhernandez
 */
@Stateless
public class TMantenimientoFacade extends AbstractFacade<TMantenimiento> {
    @PersistenceContext(unitName = "AdminReqPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TMantenimientoFacade() {
        super(TMantenimiento.class);
    }

    public List<TMantenimiento> findByRequestArea(Short id, int[] range)
    {
        Query q = em.createNamedQuery("TMantenimiento.findByRequestArea")
        		.setParameter("id", id)
        		.setMaxResults(range[1] - range[0])
        		.setFirstResult(range[0]);
        List<TMantenimiento> list = q.getResultList();
        return list;
    }

    public List<TMantenimiento> limitRange(Date start, Date end, int[] range)
    {
        Query q = em.createNamedQuery("TMantenimiento.limitRange")
        		.setParameter("start", start)
        		.setParameter("end", end)
        		.setMaxResults(range[1] - range[0])
        		.setFirstResult(range[0]);
        List<TMantenimiento> list = q.getResultList();
        return list;
    }

    public List<TMantenimiento> startRange(Date start, Date end, int[] range)
    {
        Query q = em.createNamedQuery("TMantenimiento.startRange")
        		.setParameter("start", start)
        		.setParameter("end", end)
        		.setMaxResults(range[1] - range[0])
        		.setFirstResult(range[0]);
        List<TMantenimiento> list = q.getResultList();
        return list;
    }
    
}
