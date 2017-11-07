/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itca.requerimientos.model.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "ctl_proveedor", catalog = "dbrequerimientos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CtlProveedor.findAll", query = "SELECT c FROM CtlProveedor c"),
    @NamedQuery(name = "CtlProveedor.findById", query = "SELECT c FROM CtlProveedor c WHERE c.id = :id"),
    @NamedQuery(name = "CtlProveedor.findByNombre", query = "SELECT c FROM CtlProveedor c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CtlProveedor.findByNit", query = "SELECT c FROM CtlProveedor c WHERE c.nit = :nit"),
    @NamedQuery(name = "CtlProveedor.findByDireccion", query = "SELECT c FROM CtlProveedor c WHERE c.direccion = :direccion"),
    @NamedQuery(name = "CtlProveedor.findByNrc", query = "SELECT c FROM CtlProveedor c WHERE c.nrc = :nrc"),
    @NamedQuery(name = "CtlProveedor.findByEMail", query = "SELECT c FROM CtlProveedor c WHERE c.eMail = :eMail"),
    @NamedQuery(name = "CtlProveedor.findByTelefono", query = "SELECT c FROM CtlProveedor c WHERE c.telefono = :telefono"),
    @NamedQuery(name = "CtlProveedor.findBySitioWeb", query = "SELECT c FROM CtlProveedor c WHERE c.sitioWeb = :sitioWeb")})
public class CtlProveedor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Short id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 17)
    @Column(name = "nit")
    private String nit;
    @Size(max = 255)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 45)
    @Column(name = "nrc")
    private String nrc;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "e_mail")
    private String eMail;
    @Size(max = 45)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 255)
    @Column(name = "sitio_web")
    private String sitioWeb;
    @OneToMany(mappedBy = "idProveedor", fetch = FetchType.LAZY)
    private List<CtlEquipo> ctlEquipoList;

    public CtlProveedor() {
    }

    public CtlProveedor(Short id) {
        this.id = id;
    }

    public CtlProveedor(Short id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    @XmlTransient
    public List<CtlEquipo> getCtlEquipoList() {
        return ctlEquipoList;
    }

    public void setCtlEquipoList(List<CtlEquipo> ctlEquipoList) {
        this.ctlEquipoList = ctlEquipoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CtlProveedor)) {
            return false;
        }
        CtlProveedor other = (CtlProveedor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.itca.requerimientos.model.entities.CtlProveedor[ id=" + id + " ]";
    }
    
}
