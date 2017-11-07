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
@Table(name = "ctl_tipo_empleado", catalog = "dbrequerimientos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CtlTipoEmpleado.findAll", query = "SELECT c FROM CtlTipoEmpleado c"),
    @NamedQuery(name = "CtlTipoEmpleado.findById", query = "SELECT c FROM CtlTipoEmpleado c WHERE c.id = :id"),
    @NamedQuery(name = "CtlTipoEmpleado.findByNombre", query = "SELECT c FROM CtlTipoEmpleado c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CtlTipoEmpleado.findByCodigo", query = "SELECT c FROM CtlTipoEmpleado c WHERE c.codigo = :codigo")})
public class CtlTipoEmpleado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Short id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "codigo")
    private String codigo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoEmpleado", fetch = FetchType.LAZY)
    private List<TEmpleado> tEmpleadoList;

    public CtlTipoEmpleado() {
    }

    public CtlTipoEmpleado(Short id) {
        this.id = id;
    }

    public CtlTipoEmpleado(Short id, String nombre, String codigo) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
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
    public List<TEmpleado> getTEmpleadoList() {
        return tEmpleadoList;
    }

    public void setTEmpleadoList(List<TEmpleado> tEmpleadoList) {
        this.tEmpleadoList = tEmpleadoList;
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
        if (!(object instanceof CtlTipoEmpleado)) {
            return false;
        }
        CtlTipoEmpleado other = (CtlTipoEmpleado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.itca.requerimientos.model.entities.CtlTipoEmpleado[ id=" + id + " ]";
    }
    
}
