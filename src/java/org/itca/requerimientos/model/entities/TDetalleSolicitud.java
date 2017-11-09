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
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.itca.requerimientos.model.entities.jasper.SolicitudEquipoJasper;
import org.itca.requerimientos.model.entities.jasper.SolicitudFallaJasper;
import org.itca.requerimientos.model.entities.jasper.SolicitudTecnicoJasper;

/**
 *
 * @author dfhernandez
 */
@Entity
@Table(name = "t_detalle_solicitud", catalog = "dbrequerimientos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TDetalleSolicitud.limitTime", query = "SELECT t FROM TDetalleSolicitud t WHERE t.fechaLimite >= :now"),
    @NamedQuery(name = "TDetalleSolicitud.solvedOverTime", query = "SELECT t FROM TDetalleSolicitud t WHERE t.fechaFin >= t.fechaLimite"),
    @NamedQuery(name = "TDetalleSolicitud.findByRequestArea", query = "SELECT t FROM TDetalleSolicitud t WHERE t.idAreaRequerimiento.id = :id"),
    @NamedQuery(name = "TDetalleSolicitud.findByEquipment", query = "SELECT t FROM TDetalleSolicitud t WHERE t.idEquipo.id = :id"),
    @NamedQuery(name = "TDetalleSolicitud.findByEmployee", query = "SELECT t FROM TDetalleSolicitud t WHERE t.idSolicitudRequerimiento.idEmpleado.id = :id"),
    @NamedQuery(name = "TDetalleSolicitud.notSolved", query = "SELECT t FROM TDetalleSolicitud t WHERE t.fechaFin IS NULL"),
    @NamedQuery(name = "TDetalleSolicitud.notSolvedByAssignedTechnician", query = "SELECT t FROM TDetalleSolicitud t WHERE t.fechaFin IS NULL AND t.idTecnicoAsignado.id = :id"),
    @NamedQuery(name = "TDetalleSolicitud.entryRange", query = "SELECT t FROM TDetalleSolicitud t WHERE t.fechaInicio >= :start AND t.fechaInicio <= :end"),
    @NamedQuery(name = "TDetalleSolicitud.findByAssignedTechnician", query = "SELECT t FROM TDetalleSolicitud t WHERE t.idTecnicoAsignado.id = :id"),
    @NamedQuery(name = "TDetalleSolicitud.findBySolutionType", query = "SELECT t FROM TDetalleSolicitud t WHERE t.idTipoSolucion.id = :id"),
    @NamedQuery(name = "TDetalleSolicitud.findByRequestType", query = "SELECT t FROM TDetalleSolicitud t WHERE t.idTipoRequerimiento.id = :id"),
    @NamedQuery(name = "TDetalleSolicitud.findByFaultType", query = "SELECT t FROM TDetalleSolicitud t WHERE t.idTipoFalla.id = :id"),
    @NamedQuery(name = "TDetalleSolicitud.findAll", query = "SELECT t FROM TDetalleSolicitud t"),
    @NamedQuery(name = "TDetalleSolicitud.findById", query = "SELECT t FROM TDetalleSolicitud t WHERE t.id = :id"),
    @NamedQuery(name = "TDetalleSolicitud.findByFechaRequerimiento", query = "SELECT t FROM TDetalleSolicitud t WHERE t.fechaRequerimiento = :fechaRequerimiento"),
    @NamedQuery(name = "TDetalleSolicitud.findByFechaInicio", query = "SELECT t FROM TDetalleSolicitud t WHERE t.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "TDetalleSolicitud.findByFechaLimite", query = "SELECT t FROM TDetalleSolicitud t WHERE t.fechaLimite = :fechaLimite"),
    @NamedQuery(name = "TDetalleSolicitud.findByFechaFin", query = "SELECT t FROM TDetalleSolicitud t WHERE t.fechaFin = :fechaFin"),
    @NamedQuery(name = "TDetalleSolicitud.findByDescripcionFalla", query = "SELECT t FROM TDetalleSolicitud t WHERE t.descripcionFalla = :descripcionFalla"),
    @NamedQuery(name = "TDetalleSolicitud.findByDescripcionSolucion", query = "SELECT t FROM TDetalleSolicitud t WHERE t.descripcionSolucion = :descripcionSolucion"),
    @NamedQuery(name = "TDetalleSolicitud.findByComentario", query = "SELECT t FROM TDetalleSolicitud t WHERE t.comentario = :comentario")})
@NamedNativeQueries({
    @NamedNativeQuery(name = "TDetalleSolicitud.requestByEquipmentModelReport", query = "SELECT t01.codigo AS t01codigo, t01.nombre AS t01nombre, t02.codigo AS t02codigo, t02.nombre AS t02nombre, COUNT(t04.id) AS t04conteo FROM ctl_marca_equipo AS t01 JOIN ctl_modelo_equipo AS t02 ON t01.id = t02.id_marca JOIN ctl_equipo AS t03 ON t02.id = t03.id_modelo JOIN t_detalle_solicitud t04 ON t03.id = t04.id_equipo GROUP BY 1, 2, 3, 4 ORDER BY 5 DESC, 2, 4", resultSetMapping = "SolicitudEquipoJasperValueMapping"),
    @NamedNativeQuery(name = "TDetalleSolicitud.requestByEquipmentFailureReport", query = "SELECT t05.codigo as t05codigo, t05.nombre as t05nombre, t04.codigo as t04codigo, t04.nombre as t04nombre, t01.codigo as t01codigo, t01.nombre as t01nombre, COUNT(t02.id) as t02fallas FROM ctl_tipo_falla AS t01 LEFT JOIN t_detalle_solicitud AS t02 ON t01.id = t02.id_tipo_falla LEFT JOIN ctl_equipo AS t03 ON t03.id = t02.id_equipo LEFT JOIN ctl_modelo_equipo AS t04 ON t04.id = t03.id_modelo LEFT JOIN ctl_marca_equipo AS t05 ON t05.id = t04.id_marca GROUP BY 1, 2, 3, 4, 5, 6 ORDER BY 7 DESC, 2, 4, 6", resultSetMapping = "SolicitudFallaJasperValueMapping"),
    @NamedNativeQuery(name = "TDetalleSolicitud.requestByAssignedTechnicianReport", query = "SELECT CONCAT(t04.nombre, t04.apellido) AS t04tecnico, t01.codigo as t01codigo, t01.nombre as t01nombre, t03.codigo as t03codigo, t03.nombre as t03nombre, COUNT(t02.id) as t02mantenimientos FROM ctl_tipo_falla AS t01 JOIN t_detalle_solicitud AS t02 ON t01.id = t02.id_tipo_falla JOIN ctl_tipo_solucion AS t03 ON t03.id = t02.id_tipo_solucion JOIN t_empleado AS t04 ON t04.id = t02.id_tecnico_asignado GROUP BY 1, 2, 3, 4, 5 ORDER BY 6 DESC, 1, 3, 5", resultSetMapping = "SolicitudTecnicoJasperValueMapping"),
})
@SqlResultSetMappings({
    @SqlResultSetMapping(
        name = "SolicitudEquipoJasperValueMapping",
        classes = @ConstructorResult(
            targetClass = SolicitudEquipoJasper.class,
            columns = {
            @ColumnResult(name = "t01codigo"),
            @ColumnResult(name = "t01nombre"),
            @ColumnResult(name = "t02codigo"),
            @ColumnResult(name = "t02nombre"),
            @ColumnResult(name = "t04conteo", type = Long.class)
            }
        )
    ),
    @SqlResultSetMapping(
        name = "SolicitudFallaJasperValueMapping",
        classes = @ConstructorResult(
            targetClass = SolicitudFallaJasper.class,
            columns = {
            @ColumnResult(name = "t05codigo"),
            @ColumnResult(name = "t05nombre"),
            @ColumnResult(name = "t04codigo"),
            @ColumnResult(name = "t04nombre"),
            @ColumnResult(name = "t01codigo"),
            @ColumnResult(name = "t01nombre"),
            @ColumnResult(name = "t02fallas", type = Long.class)
            }
        )
    ),
    @SqlResultSetMapping(
        name = "SolicitudTecnicoJasperValueMapping",
        classes = @ConstructorResult(
            targetClass = SolicitudTecnicoJasper.class,
            columns = {
            @ColumnResult(name = "t04tecnico"),
            @ColumnResult(name = "t01codigo"),
            @ColumnResult(name = "t01nombre"),
            @ColumnResult(name = "t03codigo"),
            @ColumnResult(name = "t03nombre"),
            @ColumnResult(name = "t02mantenimientos", type = Long.class)
            }
        )
    )
})
public class TDetalleSolicitud implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableGenerator(name = "sec_detalle_solicitud",
            table = "t_sequence",
            pkColumnName = "sequence_name",
            valueColumnName = "last_value",
            pkColumnValue = "sec_detalle_solicitud")
    @Id
    @GeneratedValue(generator = "sec_detalle_solicitud")
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

    public TDetalleSolicitud(TSolicitudRequerimiento idSolicitudRequerimiento) {
        this.idSolicitudRequerimiento = idSolicitudRequerimiento;
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
        return "[" + this.idEquipo + "] " + this.idTecnicoAsignado;
        // return "org.itca.requerimientos.model.entities.TDetalleSolicitud[ id=" + id + " ]";
    }
    
}
