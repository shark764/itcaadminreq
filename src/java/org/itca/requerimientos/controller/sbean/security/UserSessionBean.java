/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itca.requerimientos.controller.sbean.security;

import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.itca.requerimientos.model.entities.TUser;

/**
 *
 * @author dfhernandez
 */
@ManagedBean(name = "userSessionBean")
@SessionScoped
public class UserSessionBean implements Serializable {

    @EJB private org.itca.requerimientos.controller.facade.security.TUserFacade ejbTUserFacade;

    private TUser userLogged;
    
    public void logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        System.out.println("user: " + context.getExternalContext().getUserPrincipal());
        context.getExternalContext().invalidateSession();
        this.userLogged = null;
        try {
            context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + "/views/login.xhtml");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public TUser getUserLogged() {
        FacesContext context = FacesContext.getCurrentInstance();
        this.userLogged = ejbTUserFacade.findByUsername(context.getExternalContext().getUserPrincipal().getName());
        return this.userLogged;
    }

    public void setUserLogged(TUser userLogged) {
        this.userLogged = userLogged;
    }

    /**
     * Creates a new instance of JasperBean
     */
    public UserSessionBean() {
    }
    
}