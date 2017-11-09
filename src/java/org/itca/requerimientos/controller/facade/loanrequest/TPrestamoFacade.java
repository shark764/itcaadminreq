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
import org.itca.requerimientos.model.entities.TPrestamo;

/**
 *
 * @author dfhernandez
 */
@Stateless
public class TPrestamoFacade extends AbstractFacade<TPrestamo> {
    @PersistenceContext(unitName = "AdminReqPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TPrestamoFacade() {
        super(TPrestamo.class);
    }

    public List<TPrestamo> findByEmployee(Integer id, int[] range)
    {
        Query q = em.createNamedQuery("TPrestamo.findByEmployee")
        		.setParameter("id", id)
        		.setMaxResults(range[1] - range[0])
        		.setFirstResult(range[0]);
        List<TPrestamo> list = q.getResultList();
        return list;
    }

    public List<TPrestamo> entryRange(Date start, Date end, int[] range)
    {
        Query q = em.createNamedQuery("TPrestamo.entryRange")
        		.setParameter("start", start)
        		.setParameter("end", end)
        		.setMaxResults(range[1] - range[0])
        		.setFirstResult(range[0]);
        List<TPrestamo> list = q.getResultList();
        return list;
    }
    
}
