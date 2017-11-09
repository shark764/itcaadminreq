/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itca.requerimientos.controller.facade.loanrequest;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.itca.requerimientos.controller.facade.AbstractFacade;
import org.itca.requerimientos.model.entities.TDetallePrestamo;
import org.itca.requerimientos.model.entities.jasper.RetrasoPrestamoJasper;

/**
 *
 * @author dfhernandez
 */
@Stateless
public class TDetallePrestamoFacade extends AbstractFacade<TDetallePrestamo> {
    @PersistenceContext(unitName = "AdminReqPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TDetallePrestamoFacade() {
        super(TDetallePrestamo.class);
    }

    public List<TDetallePrestamo> returnedOverTime(int[] range)
    {
        Query q = em.createNamedQuery("TDetallePrestamo.returnedOverTime")
        		// .setParameter("limit", new Date())
        		.setMaxResults(range[1] - range[0])
        		.setFirstResult(range[0]);
        List<TDetallePrestamo> list = q.getResultList();
        return list;
    }

    public List<TDetallePrestamo> notReturnedLimit(int[] range)
    {
        Query q = em.createNamedQuery("TDetallePrestamo.notReturnedLimit")
        		.setParameter("now", new Date())
        		.setMaxResults(range[1] - range[0])
        		.setFirstResult(range[0]);
        List<TDetallePrestamo> list = q.getResultList();
        return list;
    }

    public List<TDetallePrestamo> notReturned(int[] range)
    {
        Query q = em.createNamedQuery("TDetallePrestamo.notReturned")
        		.setMaxResults(range[1] - range[0])
        		.setFirstResult(range[0]);
        List<TDetallePrestamo> list = q.getResultList();
        return list;
    }

    public List<TDetallePrestamo> notReturnedByEmployee(Integer id, int[] range)
    {
        Query q = em.createNamedQuery("TDetallePrestamo.notReturnedByEmployee")
        		.setParameter("id", id)
        		.setMaxResults(range[1] - range[0])
        		.setFirstResult(range[0]);
        List<TDetallePrestamo> list = q.getResultList();
        return list;
    }

    public List<TDetallePrestamo> entryRange(Date start, Date end, int[] range)
    {
        Query q = em.createNamedQuery("TDetallePrestamo.entryRange")
        		.setParameter("start", start)
        		.setParameter("end", end)
        		.setMaxResults(range[1] - range[0])
        		.setFirstResult(range[0]);
        List<TDetallePrestamo> list = q.getResultList();
        return list;
    }

    public List<TDetallePrestamo> findByEquipment(Long id, int[] range)
    {
        Query q = em.createNamedQuery("TDetallePrestamo.findByEquipment")
        		.setParameter("id", id)
        		.setMaxResults(range[1] - range[0])
        		.setFirstResult(range[0]);
        List<TDetallePrestamo> list = q.getResultList();
        return list;
    }

    public List<TDetallePrestamo> findByEmployee(Integer id, int[] range)
    {
        Query q = em.createNamedQuery("TDetallePrestamo.findByEmployee")
        		.setParameter("id", id)
        		.setMaxResults(range[1] - range[0])
        		.setFirstResult(range[0]);
        List<TDetallePrestamo> list = q.getResultList();
        return list;
    }

    public List<TDetallePrestamo> limitTime(int[] range)
    {
        Query q = em.createNamedQuery("TDetallePrestamo.limitTime")
        		.setParameter("now", new Date())
        		.setMaxResults(range[1] - range[0])
        		.setFirstResult(range[0]);
        List<TDetallePrestamo> list = q.getResultList();
        return list;
    }

    public List<RetrasoPrestamoJasper> findAllForEquipmentReturnedOverTimeReport()
    {
        Query q = em.createNamedQuery("TDetallePrestamo.equipmentReturnedOverTimeReport");
        List<RetrasoPrestamoJasper> list = q.getResultList();
        return list;
    }
    
}
