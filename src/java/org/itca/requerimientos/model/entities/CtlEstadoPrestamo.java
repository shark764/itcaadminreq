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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author dfhernandez
 */
@Entity
@Table(name = "ctl_estado_prestamo", catalog = "dbrequerimientos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CtlEstadoPrestamo.findAll", query = "SELECT c FROM CtlEstadoPrestamo c"),
    @NamedQuery(name = "CtlEstadoPrestamo.findById", query = "SELECT c FROM CtlEstadoPrestamo c WHERE c.id = :id"),
    @NamedQuery(name = "CtlEstadoPrestamo.findByNombre", query = "SELECT c FROM CtlEstadoPrestamo c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CtlEstadoPrestamo.findByCodigo", query = "SELECT c FROM CtlEstadoPrestamo c WHERE c.codigo = :codigo")})
public class CtlEstadoPrestamo implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableGenerator(name = "sec_estado_prestamo",
            table = "t_sequence",
            pkColumnName = "sequence_name",
            valueColumnName = "last_value",
            pkColumnValue = "sec_estado_prestamo")
    @Id
    @GeneratedValue(generator = "sec_estado_prestamo")
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstadoPrestamo", fetch = FetchType.LAZY)
    private List<TDetallePrestamo> tDetallePrestamoList;

    public CtlEstadoPrestamo() {
    }

    public CtlEstadoPrestamo(Short id) {
        this.id = id;
    }

    public CtlEstadoPrestamo(Short id, String nombre, String codigo) {
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
        if (!(object instanceof CtlEstadoPrestamo)) {
            return false;
        }
        CtlEstadoPrestamo other = (CtlEstadoPrestamo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + this.codigo + "] " + this.nombre;
        // return "org.itca.requerimientos.model.entities.CtlEstadoPrestamo[ id=" + id + " ]";
    }
    
}
