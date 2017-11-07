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
@Table(name = "t_detalle_solicitud", catalog = "dbrequerimientos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TDetalleSolicitud.findAll", query = "SELECT t FROM TDetalleSolicitud t"),
    @NamedQuery(name = "TDetalleSolicitud.findById", query = "SELECT t FROM TDetalleSolicitud t WHERE t.id = :id"),
    @NamedQuery(name = "TDetalleSolicitud.findByFechaRequerimiento", query = "SELECT t FROM TDetalleSolicitud t WHERE t.fechaRequerimiento = :fechaRequerimiento"),
    @NamedQuery(name = "TDetalleSolicitud.findByFechaInicio", query = "SELECT t FROM TDetalleSolicitud t WHERE t.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "TDetalleSolicitud.findByFechaLimite", query = "SELECT t FROM TDetalleSolicitud t WHERE t.fechaLimite = :fechaLimite"),
    @NamedQuery(name = "TDetalleSolicitud.findByFechaFin", query = "SELECT t FROM TDetalleSolicitud t WHERE t.fechaFin = :fechaFin"),
    @NamedQuery(name = "TDetalleSolicitud.findByDescripcionFalla", query = "SELECT t FROM TDetalleSolicitud t WHERE t.descripcionFalla = :descripcionFalla"),
    @NamedQuery(name = "TDetalleSolicitud.findByDescripcionSolucion", query = "SELECT t FROM TDetalleSolicitud t WHERE t.descripcionSolucion = :descripcionSolucion"),
    @NamedQuery(name = "TDetalleSolicitud.findByComentario", query = "SELECT t FROM TDetalleSolicitud t WHERE t.comentario = :comentario")})
public class TDetalleSolicitud implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_requerimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRequerimiento;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "fecha_limite")
    @Temporal(TemporalType.DATE)
    private Date fechaLimite;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Size(max = 255)
    @Column(name = "descripcion_falla")
    private String descripcionFalla;
    @Size(max = 255)
    @Column(name = "descripcion_solucion")
    private String descripcionSolucion;
    @Size(max = 255)
    @Column(name = "comentario")
    private String comentario;
    @JoinColumn(name = "id_area_requerimiento", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CtlAreaRequerimiento idAreaRequerimiento;
    @JoinColumn(name = "id_equipo", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CtlEquipo idEquipo;
    @JoinColumn(name = "id_estado_solicitud", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CtlEstadoSolicitud idEstadoSolicitud;
    @JoinColumn(name = "id_solicitud_requerimiento", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TSolicitudRequerimiento idSolicitudRequerimiento;
    @JoinColumn(name = "id_tecnico_asignado", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TEmpleado idTecnicoAsignado;
    @JoinColumn(name = "id_tipo_falla", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CtlTipoFalla idTipoFalla;
    @JoinColumn(name = "id_tipo_requerimiento", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CtlTipoRequerimiento idTipoRequerimiento;
    @JoinColumn(name = "id_tipo_solucion", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CtlTipoSolucion idTipoSolucion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDetalleSolicitud", fetch = FetchType.LAZY)
    private List<TInsumoUtilizado> tInsumoUtilizadoList;

    public TDetalleSolicitud() {
    }

    public TDetalleSolicitud(Long id) {
        this.id = id;
    }

    public TDetalleSolicitud(Long id, Date fechaRequerimiento) {
        this.id = id;
        this.fechaRequerimiento = fechaRequerimiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaRequerimiento() {
        return fechaRequerimiento;
    }

    public void setFechaRequerimiento(Date fechaRequerimiento) {
        this.fechaRequerimiento = fechaRequerimiento;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getDescripcionFalla() {
        return descripcionFalla;
    }

    public void setDescripcionFalla(String descripcionFalla) {
        this.descripcionFalla = descripcionFalla;
    }

    public String getDescripcionSolucion() {
        return descripcionSolucion;
    }

    public void setDescripcionSolucion(String descripcionSolucion) {
        this.descripcionSolucion = descripcionSolucion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public CtlAreaRequerimiento getIdAreaRequerimiento() {
        return idAreaRequerimiento;
    }

    public void setIdAreaRequerimiento(CtlAreaRequerimiento idAreaRequerimiento) {
        this.idAreaRequerimiento = idAreaRequerimiento;
    }

    public CtlEquipo getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(CtlEquipo idEquipo) {
        this.idEquipo = idEquipo;
    }

    public CtlEstadoSolicitud getIdEstadoSolicitud() {
        return idEstadoSolicitud;
    }

    public void setIdEstadoSolicitud(CtlEstadoSolicitud idEstadoSolicitud) {
        this.idEstadoSolicitud = idEstadoSolicitud;
    }

    public TSolicitudRequerimiento getIdSolicitudRequerimiento() {
        return idSolicitudRequerimiento;
    }

    public void setIdSolicitudRequerimiento(TSolicitudRequerimiento idSolicitudRequerimiento) {
        this.idSolicitudRequerimiento = idSolicitudRequerimiento;
    }

    public TEmpleado getIdTecnicoAsignado() {
        return idTecnicoAsignado;
    }

    public void setIdTecnicoAsignado(TEmpleado idTecnicoAsignado) {
        this.idTecnicoAsignado = idTecnicoAsignado;
    }

    public CtlTipoFalla getIdTipoFalla() {
        return idTipoFalla;
    }

    public void setIdTipoFalla(CtlTipoFalla idTipoFalla) {
        this.idTipoFalla = idTipoFalla;
    }

    public CtlTipoRequerimiento getIdTipoRequerimiento() {
        return idTipoRequerimiento;
    }

    public void setIdTipoRequerimiento(CtlTipoRequerimiento idTipoRequerimiento) {
        this.idTipoRequerimiento = idTipoRequerimiento;
    }

    public CtlTipoSolucion getIdTipoSolucion() {
        return idTipoSolucion;
    }

    public void setIdTipoSolucion(CtlTipoSolucion idTipoSolucion) {
        this.idTipoSolucion = idTipoSolucion;
    }

    @XmlTransient
    public List<TInsumoUtilizado> getTInsumoUtilizadoList() {
        return tInsumoUtilizadoList;
    }

    public void setTInsumoUtilizadoList(List<TInsumoUtilizado> tInsumoUtilizadoList) {
        this.tInsumoUtilizadoList = tInsumoUtilizadoList;
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
        if (!(object instanceof TDetalleSolicitud)) {
            return false;
        }
        TDetalleSolicitud other = (TDetalleSolicitud) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.itca.requerimientos.model.entities.TDetalleSolicitud[ id=" + id + " ]";
    }
    
}
