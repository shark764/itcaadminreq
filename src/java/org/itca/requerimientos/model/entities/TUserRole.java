/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itca.requerimientos.model.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dfhernandez
 */
@Entity
@Table(name = "t_user_role", catalog = "dbrequerimientos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TUserRole.findByRolAndUser", query = "SELECT t FROM TUserRole t WHERE t.tUserRolePK.role = :role AND t.tUserRolePK.username = :username"),
    @NamedQuery(name = "TUserRole.findAll", query = "SELECT t FROM TUserRole t"),
    @NamedQuery(name = "TUserRole.findByUsername", query = "SELECT t FROM TUserRole t WHERE t.tUserRolePK.username = :username"),
    @NamedQuery(name = "TUserRole.findByRole", query = "SELECT t FROM TUserRole t WHERE t.tUserRolePK.role = :role"),
    @NamedQuery(name = "TUserRole.findByState", query = "SELECT t FROM TUserRole t WHERE t.state = :state")})
public class TUserRole implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TUserRolePK tUserRolePK;
    @Column(name = "state")
    private Short state;
    @JoinColumn(name = "role", referencedColumnName = "role", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TRole tRole;
    @JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TUser tUser;

    public TUserRole() {
    }

    public TUserRole(TUserRolePK tUserRolePK) {
        this.tUserRolePK = tUserRolePK;
    }

    public TUserRole(String username, String role) {
        this.tUserRolePK = new TUserRolePK(username, role);
    }

    public TUserRole(String username, String role, Short state) {
        this.tUserRolePK = new TUserRolePK(username, role);
        this.state = state;
    }

    public TUserRolePK getTUserRolePK() {
        return tUserRolePK;
    }

    public void setTUserRolePK(TUserRolePK tUserRolePK) {
        this.tUserRolePK = tUserRolePK;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public TRole getTRole() {
        return tRole;
    }

    public void setTRole(TRole tRole) {
        this.tRole = tRole;
    }

    public TUser getTUser() {
        return tUser;
    }

    public void setTUser(TUser tUser) {
        this.tUser = tUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tUserRolePK != null ? tUserRolePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TUserRole)) {
            return false;
        }
        TUserRole other = (TUserRole) object;
        if ((this.tUserRolePK == null && other.tUserRolePK != null) || (this.tUserRolePK != null && !this.tUserRolePK.equals(other.tUserRolePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + this.tUserRolePK.getRole() + "] " + this.tUser;
        // return "org.itca.requerimientos.model.entities.TUserRole[ tUserRolePK=" + tUserRolePK + " ]";
    }
    
}
