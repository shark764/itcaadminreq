/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itca.requerimientos.controller.facade.security;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.itca.requerimientos.controller.facade.AbstractFacade;
import org.itca.requerimientos.model.entities.TRole;
import org.itca.requerimientos.model.entities.TUserRole;

/**
 *
 * @author dfhernandez
 */
@Stateless
public class TUserRoleFacade extends AbstractFacade<TUserRole> {
    @PersistenceContext(unitName = "AdminReqPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TUserRoleFacade() {
        super(TUserRole.class);
    }

    public TUserRole findByRolAndUser(String role, String username)
    {
        Query q = em.createNamedQuery("TUserRole.findByRolAndUser")
                .setParameter("role", role)
                .setParameter("username", username);
        return (TUserRole) q.getSingleResult();
    }

    public boolean userHasRole(String role, String username)
    {
        Query q = em.createNamedQuery("TUserRole.findByRolAndUser")
                .setParameter("role", role)
                .setParameter("username", username);
        return (TUserRole) q.getSingleResult() != null;
    }

    public List<TRole> findRolesByUser(String username)
    {
        Query q = em.createNamedQuery("TUserRole.findByUsername")
        		.setParameter("username", username);
        List<TUserRole> list = q.getResultList();
        List<TRole> rlist = new ArrayList<TRole>();
        for (TUserRole userRole : list) {
            rlist.add(userRole.getTRole());
        }
        return rlist;
    }
    
}
