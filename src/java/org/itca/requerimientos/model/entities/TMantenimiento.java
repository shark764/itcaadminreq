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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
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
@Table(name = "t_mantenimiento", catalog = "dbrequerimientos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TMantenimiento.findByIdRequestArea", query = "SELECT t FROM TMantenimiento t WHERE t.idAreaRequerimiento.id = :id"),
    @NamedQuery(name = "TMantenimiento.limitRange", query = "SELECT t FROM TMantenimiento t WHERE t.fechaLimite >= :start AND t.fechaLimite <= :end"),
    @NamedQuery(name = "TMantenimiento.startRange", query = "SELECT t FROM TMantenimiento t WHERE t.fechaInicio >= :start AND t.fechaInicio <= :end"),
    @NamedQuery(name = "TMantenimiento.findAll", query = "SELECT t FROM TMantenimiento t"),
    @NamedQuery(name = "TMantenimiento.findById", query = "SELECT t FROM TMantenimiento t WHERE t.id = :id"),
    @NamedQuery(name = "TMantenimiento.findByCodigo", query = "SELECT t FROM TMantenimiento t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TMantenimiento.findByFecha", query = "SELECT t FROM TMantenimiento t WHERE t.fecha = :fecha"),
    @NamedQuery(name = "TMantenimiento.findByFechaInicio", query = "SELECT t FROM TMantenimiento t WHERE t.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "TMantenimiento.findByFechaLimite", query = "SELECT t FROM TMantenimiento t WHERE t.fechaLimite = :fechaLimite"),
    @NamedQuery(name = "TMantenimiento.findByFechaFin", query = "SELECT t FROM TMantenimiento t WHERE t.fechaFin = :fechaFin"),
    @NamedQuery(name = "TMantenimiento.findByDescripcion", query = "SELECT t FROM TMantenimiento t WHERE t.descripcion = :descripcion")})
public class TMantenimiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableGenerator(name = "sec_mantenimiento",
            table = "t_sequence",
            pkColumnName = "sequence_name",
            valueColumnName = "last_value",
            pkColumnValue = "sec_mantenimiento")
    @Id
    @GeneratedValue(generator = "sec_mantenimiento")
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Short id;
    @Size(max = 4)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_limite")
    @Temporal(TemporalType.DATE)
    private Date fechaLimite;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "id_area_requerimiento", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CtlAreaRequerimiento idAreaRequerimiento;
    @JoinColumn(name = "id_autoriza", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TEmpleado idAutoriza;
    @JoinColumn(name = "id_coordina", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TEmpleado idCoordina;
    @JoinColumn(name = "id_tipo_mantenimiento", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CtlTipoMantenimiento idTipoMantenimiento;
    @JoinColumn(name = "id_user_mod", referencedColumnName = "username")
    @ManyToOne(fetch = FetchType.LAZY)
    private TUser idUserMod;
    @JoinColumn(name = "id_user_reg", referencedColumnName = "username")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TUser idUserReg;

    public TMantenimiento() {
    }

    public TMantenimiento(Short id) {
        this.id = id;
    }

    public TMantenimiento(Short id, Date fecha, Date fechaInicio, Date fechaLimite, Date fechaFin) {
        this.id = id;
        this.fecha = fecha;
        this.fechaInicio = fechaInicio;
        this.fechaLimite = fechaLimite;
        this.fechaFin = fechaFin;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CtlAreaRequerimiento getIdAreaRequerimiento() {
        return idAreaRequerimiento;
    }

    public void setIdAreaRequerimiento(CtlAreaRequerimiento idAreaRequerimiento) {
        this.idAreaRequerimiento = idAreaRequerimiento;
    }

    public TEmpleado getIdAutoriza() {
        return idAutoriza;
    }

    public void setIdAutoriza(TEmpleado idAutoriza) {
        this.idAutoriza = idAutoriza;
    }

    public TEmpleado getIdCoordina() {
        return idCoordina;
    }

    public void setIdCoordina(TEmpleado idCoordina) {
        this.idCoordina = idCoordina;
    }

    public CtlTipoMantenimiento getIdTipoMantenimiento() {
        return idTipoMantenimiento;
    }

    public void setIdTipoMantenimiento(CtlTipoMantenimiento idTipoMantenimiento) {
        this.idTipoMantenimiento = idTipoMantenimiento;
    }

    public TUser getIdUserMod() {
        return idUserMod;
    }

    public void setIdUserMod(TUser idUserMod) {
        this.idUserMod = idUserMod;
    }

    public TUser getIdUserReg() {
        return idUserReg;
    }

    public void setIdUserReg(TUser idUserReg) {
        this.idUserReg = idUserReg;
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
        if (!(object instanceof TMantenimiento)) {
            return false;
        }
        TMantenimiento other = (TMantenimiento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + this.codigo + "] " + this.idAreaRequerimiento;
        // return "org.itca.requerimientos.model.entities.TMantenimiento[ id=" + id + " ]";
    }
    
}
