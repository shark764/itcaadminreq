/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itca.requerimientos.model.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dfhernandez
 */
@Entity
@Table(name = "ctl_inventario_defectuoso", catalog = "dbrequerimientos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CtlInventarioDefectuoso.findAll", query = "SELECT c FROM CtlInventarioDefectuoso c"),
    @NamedQuery(name = "CtlInventarioDefectuoso.findById", query = "SELECT c FROM CtlInventarioDefectuoso c WHERE c.id = :id"),
    @NamedQuery(name = "CtlInventarioDefectuoso.findByDescripcion", query = "SELECT c FROM CtlInventarioDefectuoso c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CtlInventarioDefectuoso.findByFechaIngreso", query = "SELECT c FROM CtlInventarioDefectuoso c WHERE c.fechaIngreso = :fechaIngreso")})
public class CtlInventarioDefectuoso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @JoinColumn(name = "id_equipo", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CtlEquipo idEquipo;

    public CtlInventarioDefectuoso() {
    }

    public CtlInventarioDefectuoso(Long id) {
        this.id = id;
    }

    public CtlInventarioDefectuoso(Long id, Date fechaIngreso) {
        this.id = id;
        this.fechaIngreso = fechaIngreso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public CtlEquipo getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(CtlEquipo idEquipo) {
        this.idEquipo = idEquipo;
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
        if (!(object instanceof CtlInventarioDefectuoso)) {
            return false;
        }
        CtlInventarioDefectuoso other = (CtlInventarioDefectuoso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.itca.requerimientos.model.entities.CtlInventarioDefectuoso[ id=" + id + " ]";
    }
    
}
