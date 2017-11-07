/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itca.requerimientos.controller.facade.security;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.itca.requerimientos.controller.facade.AbstractFacade;
import org.itca.requerimientos.model.entities.TUser;

/**
 *
 * @author dfhernandez
 */
@Stateless
public class TUserFacade extends AbstractFacade<TUser> {
    @PersistenceContext(unitName = "AdminReqPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TUserFacade() {
        super(TUser.class);
    }

    public boolean login(String username, String password)
    {
        try {
            TUser u = em.createNamedQuery("TUser.login", TUser.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
            return u != null;
        } catch (Exception e) {
            return false;
        }
    }
    
}
