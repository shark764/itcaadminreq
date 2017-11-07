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
@Table(name = "t_detalle_prestamo", catalog = "dbrequerimientos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TDetallePrestamo.findAll", query = "SELECT t FROM TDetallePrestamo t"),
    @NamedQuery(name = "TDetallePrestamo.findById", query = "SELECT t FROM TDetallePrestamo t WHERE t.id = :id"),
    @NamedQuery(name = "TDetallePrestamo.findByFechaPrestamo", query = "SELECT t FROM TDetallePrestamo t WHERE t.fechaPrestamo = :fechaPrestamo"),
    @NamedQuery(name = "TDetallePrestamo.findByFechaLimite", query = "SELECT t FROM TDetallePrestamo t WHERE t.fechaLimite = :fechaLimite"),
    @NamedQuery(name = "TDetallePrestamo.findByFechaEntrega", query = "SELECT t FROM TDetallePrestamo t WHERE t.fechaEntrega = :fechaEntrega"),
    @NamedQuery(name = "TDetallePrestamo.findByDescripcion", query = "SELECT t FROM TDetallePrestamo t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "TDetallePrestamo.findByComentario", query = "SELECT t FROM TDetallePrestamo t WHERE t.comentario = :comentario")})
public class TDetallePrestamo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_prestamo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPrestamo;
    @Column(name = "fecha_limite")
    @Temporal(TemporalType.DATE)
    private Date fechaLimite;
    @Column(name = "fecha_entrega")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEntrega;
    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 255)
    @Column(name = "comentario")
    private String comentario;
    @JoinColumn(name = "id_equipo", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CtlEquipo idEquipo;
    @JoinColumn(name = "id_estado_entrega", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CtlEstadoEntrega idEstadoEntrega;
    @JoinColumn(name = "id_estado_prestamo", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CtlEstadoPrestamo idEstadoPrestamo;
    @JoinColumn(name = "id_prestamo", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TPrestamo idPrestamo;

    public TDetallePrestamo() {
    }

    public TDetallePrestamo(Long id) {
        this.id = id;
    }

    public TDetallePrestamo(Long id, Date fechaPrestamo) {
        this.id = id;
        this.fechaPrestamo = fechaPrestamo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public CtlEquipo getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(CtlEquipo idEquipo) {
        this.idEquipo = idEquipo;
    }

    public CtlEstadoEntrega getIdEstadoEntrega() {
        return idEstadoEntrega;
    }

    public void setIdEstadoEntrega(CtlEstadoEntrega idEstadoEntrega) {
        this.idEstadoEntrega = idEstadoEntrega;
    }

    public CtlEstadoPrestamo getIdEstadoPrestamo() {
        return idEstadoPrestamo;
    }

    public void setIdEstadoPrestamo(CtlEstadoPrestamo idEstadoPrestamo) {
        this.idEstadoPrestamo = idEstadoPrestamo;
    }

    public TPrestamo getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(TPrestamo idPrestamo) {
        this.idPrestamo = idPrestamo;
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
        if (!(object instanceof TDetallePrestamo)) {
            return false;
        }
        TDetallePrestamo other = (TDetallePrestamo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.itca.requerimientos.model.entities.TDetallePrestamo[ id=" + id + " ]";
    }
    
}
