/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itca.requerimientos.controller.facade.admin;

import java.util.List;
import org.itca.requerimientos.controller.facade.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.itca.requerimientos.model.entities.TEmpleado;

/**
 *
 * @author dfhernandez
 */
@Stateless
public class TEmpleadoFacade extends AbstractFacade<TEmpleado> {
    @PersistenceContext(unitName = "AdminReqPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TEmpleadoFacade() {
        super(TEmpleado.class);
    }

    public List<TEmpleado> notReturnedByEmployee(Integer id, int[] range)
    {
        List<TEmpleado> list = null;
        Query q = em.createNamedQuery("TEmpleado.findByBoss");
        q.setParameter("id", id);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        list = q.getResultList();
        return list;
    }
    
}
