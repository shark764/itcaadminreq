/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itca.requerimientos.model.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author dfhernandez
 */
@Entity
@Table(name = "t_role", catalog = "dbrequerimientos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TRole.findAll", query = "SELECT t FROM TRole t"),
    @NamedQuery(name = "TRole.findByRole", query = "SELECT t FROM TRole t WHERE t.role = :role"),
    @NamedQuery(name = "TRole.findByState", query = "SELECT t FROM TRole t WHERE t.state = :state")})
public class TRole implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "role")
    private String role;
    @Column(name = "state")
    private Short state;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tRole", fetch = FetchType.LAZY)
    private List<TUserRole> tUserRoleList;

    public TRole() {
    }

    public TRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    @XmlTransient
    public List<TUserRole> getTUserRoleList() {
        return tUserRoleList;
    }

    public void setTUserRoleList(List<TUserRole> tUserRoleList) {
        this.tUserRoleList = tUserRoleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (role != null ? role.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TRole)) {
            return false;
        }
        TRole other = (TRole) object;
        if ((this.role == null && other.role != null) || (this.role != null && !this.role.equals(other.role))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.role;
        // return "org.itca.requerimientos.model.entities.TRole[ role=" + role + " ]";
    }
    
}
