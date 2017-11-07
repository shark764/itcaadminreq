/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itca.requerimientos.model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author dfhernandez
 */
@Entity
@Table(name = "t_user", catalog = "dbrequerimientos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TUser.findAll", query = "SELECT t FROM TUser t"),
    @NamedQuery(name = "TUser.findByUsername", query = "SELECT t FROM TUser t WHERE t.username = :username"),
    @NamedQuery(name = "TUser.findByPassword", query = "SELECT t FROM TUser t WHERE t.password = :password"),
    @NamedQuery(name = "TUser.findByState", query = "SELECT t FROM TUser t WHERE t.state = :state"),
    @NamedQuery(name = "TUser.findByFirstname", query = "SELECT t FROM TUser t WHERE t.firstname = :firstname"),
    @NamedQuery(name = "TUser.findByLastname", query = "SELECT t FROM TUser t WHERE t.lastname = :lastname"),
    @NamedQuery(name = "TUser.findByCreationDate", query = "SELECT t FROM TUser t WHERE t.creationDate = :creationDate"),
    @NamedQuery(name = "TUser.findByLastLogin", query = "SELECT t FROM TUser t WHERE t.lastLogin = :lastLogin"),
    @NamedQuery(name = "TUser.findByConnected", query = "SELECT t FROM TUser t WHERE t.connected = :connected")})
public class TUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;
    @Column(name = "state")
    private Short state;
    @Size(max = 45)
    @Column(name = "firstname")
    private String firstname;
    @Size(max = 45)
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "last_login")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;
    @Column(name = "connected")
    private Boolean connected;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tUser", fetch = FetchType.LAZY)
    private List<TUserRole> tUserRoleList;
    @OneToMany(mappedBy = "idUserMod", fetch = FetchType.LAZY)
    private List<CtlEquipo> ctlEquipoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUserReg", fetch = FetchType.LAZY)
    private List<CtlEquipo> ctlEquipoList1;
    @JoinColumn(name = "id_empleado", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TEmpleado idEmpleado;
    @OneToMany(mappedBy = "idUserMod", fetch = FetchType.LAZY)
    private List<TSolicitudRequerimiento> tSolicitudRequerimientoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUserReg", fetch = FetchType.LAZY)
    private List<TSolicitudRequerimiento> tSolicitudRequerimientoList1;
    @OneToMany(mappedBy = "idUserMod", fetch = FetchType.LAZY)
    private List<TPrestamo> tPrestamoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUserReg", fetch = FetchType.LAZY)
    private List<TPrestamo> tPrestamoList1;

    public TUser() {
    }

    public TUser(String username) {
        this.username = username;
    }

    public TUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Boolean getConnected() {
        return connected;
    }

    public void setConnected(Boolean connected) {
        this.connected = connected;
    }

    @XmlTransient
    public List<TUserRole> getTUserRoleList() {
        return tUserRoleList;
    }

    public void setTUserRoleList(List<TUserRole> tUserRoleList) {
        this.tUserRoleList = tUserRoleList;
    }

    @XmlTransient
    public List<CtlEquipo> getCtlEquipoList() {
        return ctlEquipoList;
    }

    public void setCtlEquipoList(List<CtlEquipo> ctlEquipoList) {
        this.ctlEquipoList = ctlEquipoList;
    }

    @XmlTransient
    public List<CtlEquipo> getCtlEquipoList1() {
        return ctlEquipoList1;
    }

    public void setCtlEquipoList1(List<CtlEquipo> ctlEquipoList1) {
        this.ctlEquipoList1 = ctlEquipoList1;
    }

    public TEmpleado getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(TEmpleado idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    @XmlTransient
    public List<TSolicitudRequerimiento> getTSolicitudRequerimientoList() {
        return tSolicitudRequerimientoList;
    }

    public void setTSolicitudRequerimientoList(List<TSolicitudRequerimiento> tSolicitudRequerimientoList) {
        this.tSolicitudRequerimientoList = tSolicitudRequerimientoList;
    }

    @XmlTransient
    public List<TSolicitudRequerimiento> getTSolicitudRequerimientoList1() {
        return tSolicitudRequerimientoList1;
    }

    public void setTSolicitudRequerimientoList1(List<TSolicitudRequerimiento> tSolicitudRequerimientoList1) {
        this.tSolicitudRequerimientoList1 = tSolicitudRequerimientoList1;
    }

    @XmlTransient
    public List<TPrestamo> getTPrestamoList() {
        return tPrestamoList;
    }

    public void setTPrestamoList(List<TPrestamo> tPrestamoList) {
        this.tPrestamoList = tPrestamoList;
    }

    @XmlTransient
    public List<TPrestamo> getTPrestamoList1() {
        return tPrestamoList1;
    }

    public void setTPrestamoList1(List<TPrestamo> tPrestamoList1) {
        this.tPrestamoList1 = tPrestamoList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TUser)) {
            return false;
        }
        TUser other = (TUser) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.itca.requerimientos.model.entities.TUser[ username=" + username + " ]";
    }
    
}
