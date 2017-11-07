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
@Table(name = "ctl_estado_entrega", catalog = "dbrequerimientos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CtlEstadoEntrega.findAll", query = "SELECT c FROM CtlEstadoEntrega c"),
    @NamedQuery(name = "CtlEstadoEntrega.findById", query = "SELECT c FROM CtlEstadoEntrega c WHERE c.id = :id"),
    @NamedQuery(name = "CtlEstadoEntrega.findByNombre", query = "SELECT c FROM CtlEstadoEntrega c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CtlEstadoEntrega.findByCodigo", query = "SELECT c FROM CtlEstadoEntrega c WHERE c.codigo = :codigo")})
public class CtlEstadoEntrega implements Serializable {
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "codigo")
    private String codigo;
    @OneToMany(mappedBy = "idEstadoEntrega", fetch = FetchType.LAZY)
    private List<TDetallePrestamo> tDetallePrestamoList;

    public CtlEstadoEntrega() {
    }

    public CtlEstadoEntrega(Short id) {
        this.id = id;
    }

    public CtlEstadoEntrega(Short id, String nombre, String codigo) {
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
    public List<TDetallePrestamo> getTDetallePrestamoList() {
        return tDetallePrestamoList;
    }

    public void setTDetallePrestamoList(List<TDetallePrestamo> tDetallePrestamoList) {
        this.tDetallePrestamoList = tDetallePrestamoList;
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
        if (!(object instanceof CtlEstadoEntrega)) {
            return false;
        }
        CtlEstadoEntrega other = (CtlEstadoEntrega) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.itca.requerimientos.model.entities.CtlEstadoEntrega[ id=" + id + " ]";
    }
    
}
