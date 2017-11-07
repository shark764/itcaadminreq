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
import org.itca.requerimientos.model.entities.TSolicitudRequerimiento;

/**
 *
 * @author dfhernandez
 */
@Stateless
public class TSolicitudRequerimientoFacade extends AbstractFacade<TSolicitudRequerimiento> {
    @PersistenceContext(unitName = "AdminReqPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TSolicitudRequerimientoFacade() {
        super(TSolicitudRequerimiento.class);
    }

    public List<TSolicitudRequerimiento> findByArea(Short id, int[] range)
    {
        Query q = em.createNamedQuery("TSolicitudRequerimiento.findByArea")
        		.setParameter("id", id)
        		.setMaxResults(range[1] - range[0] + 1)
        		.setFirstResult(range[0]);
        List<TSolicitudRequerimiento> list = q.getResultList();
        return list;
    }

    public List<TSolicitudRequerimiento> findByEmployee(Integer id, int[] range)
    {
        Query q = em.createNamedQuery("TSolicitudRequerimiento.findByEmployee")
        		.setParameter("id", id)
        		.setMaxResults(range[1] - range[0] + 1)
        		.setFirstResult(range[0]);
        List<TSolicitudRequerimiento> list = q.getResultList();
        return list;
    }

    public List<TSolicitudRequerimiento> entryRange(Date start, Date end, int[] range)
    {
        Query q = em.createNamedQuery("TSolicitudRequerimiento.entryRange")
        		.setParameter("start", start)
        		.setParameter("end", end)
        		.setMaxResults(range[1] - range[0] + 1)
        		.setFirstResult(range[0]);
        List<TSolicitudRequerimiento> list = q.getResultList();
        return list;
    }
    
}
