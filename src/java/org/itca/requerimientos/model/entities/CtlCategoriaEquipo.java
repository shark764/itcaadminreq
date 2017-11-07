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
@Table(name = "ctl_categoria_equipo", catalog = "dbrequerimientos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CtlCategoriaEquipo.findAll", query = "SELECT c FROM CtlCategoriaEquipo c"),
    @NamedQuery(name = "CtlCategoriaEquipo.findById", query = "SELECT c FROM CtlCategoriaEquipo c WHERE c.id = :id"),
    @NamedQuery(name = "CtlCategoriaEquipo.findByNombre", query = "SELECT c FROM CtlCategoriaEquipo c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CtlCategoriaEquipo.findByCodigo", query = "SELECT c FROM CtlCategoriaEquipo c WHERE c.codigo = :codigo")})
public class CtlCategoriaEquipo implements Serializable {
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
    @Size(max = 3)
    @Column(name = "codigo")
    private String codigo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCategoriaEquipo", fetch = FetchType.LAZY)
    private List<CtlEquipo> ctlEquipoList;
    @OneToMany(mappedBy = "idCategoriaEquipo", fetch = FetchType.LAZY)
    private List<CtlTipoFalla> ctlTipoFallaList;
    @OneToMany(mappedBy = "idCategoriaEquipo", fetch = FetchType.LAZY)
    private List<CtlTipoSolucion> ctlTipoSolucionList;

    public CtlCategoriaEquipo() {
    }

    public CtlCategoriaEquipo(Short id) {
        this.id = id;
    }

    public CtlCategoriaEquipo(Short id, String nombre) {
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @XmlTransient
    public List<CtlEquipo> getCtlEquipoList() {
        return ctlEquipoList;
    }

    public void setCtlEquipoList(List<CtlEquipo> ctlEquipoList) {
        this.ctlEquipoList = ctlEquipoList;
    }

    @XmlTransient
    public List<CtlTipoFalla> getCtlTipoFallaList() {
        return ctlTipoFallaList;
    }

    public void setCtlTipoFallaList(List<CtlTipoFalla> ctlTipoFallaList) {
        this.ctlTipoFallaList = ctlTipoFallaList;
    }

    @XmlTransient
    public List<CtlTipoSolucion> getCtlTipoSolucionList() {
        return ctlTipoSolucionList;
    }

    public void setCtlTipoSolucionList(List<CtlTipoSolucion> ctlTipoSolucionList) {
        this.ctlTipoSolucionList = ctlTipoSolucionList;
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
        if (!(object instanceof CtlCategoriaEquipo)) {
            return false;
        }
        CtlCategoriaEquipo other = (CtlCategoriaEquipo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.itca.requerimientos.model.entities.CtlCategoriaEquipo[ id=" + id + " ]";
    }
    
}
