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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
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
@Table(name = "ctl_equipo", catalog = "dbrequerimientos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CtlEquipo.findByModel", query = "SELECT c FROM CtlEquipo c WHERE c.idModelo.id = :id"),
    @NamedQuery(name = "CtlEquipo.nonStock", query = "SELECT c FROM CtlEquipo c WHERE c.existencia <= :min"),
    @NamedQuery(name = "CtlEquipo.entryRange", query = "SELECT c FROM CtlEquipo c WHERE c.fechaAdquisicion >= :start AND c.fechaAdquisicion <= :end"),
    @NamedQuery(name = "CtlEquipo.findByProvider", query = "SELECT c FROM CtlEquipo c WHERE c.idProveedor.id = :id"),
    @NamedQuery(name = "CtlEquipo.stockRange", query = "SELECT c FROM CtlEquipo c WHERE c.existencia >= :start AND c.existencia <= :end"),
    @NamedQuery(name = "CtlEquipo.findAll", query = "SELECT c FROM CtlEquipo c"),
    @NamedQuery(name = "CtlEquipo.findById", query = "SELECT c FROM CtlEquipo c WHERE c.id = :id"),
    @NamedQuery(name = "CtlEquipo.findByNombre", query = "SELECT c FROM CtlEquipo c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CtlEquipo.findBySerie", query = "SELECT c FROM CtlEquipo c WHERE c.serie = :serie"),
    @NamedQuery(name = "CtlEquipo.findByInventario", query = "SELECT c FROM CtlEquipo c WHERE c.inventario = :inventario"),
    @NamedQuery(name = "CtlEquipo.findByExistencia", query = "SELECT c FROM CtlEquipo c WHERE c.existencia = :existencia"),
    @NamedQuery(name = "CtlEquipo.findByFechaAdquisicion", query = "SELECT c FROM CtlEquipo c WHERE c.fechaAdquisicion = :fechaAdquisicion"),
    @NamedQuery(name = "CtlEquipo.findByIp", query = "SELECT c FROM CtlEquipo c WHERE c.ip = :ip"),
    @NamedQuery(name = "CtlEquipo.findByDescripcion", query = "SELECT c FROM CtlEquipo c WHERE c.descripcion = :descripcion")})
public class CtlEquipo implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableGenerator(name = "sec_equipo",
            table = "t_sequence",
            pkColumnName = "sequence_name",
            valueColumnName = "last_value",
            pkColumnValue = "sec_equipo")
    @Id
    @GeneratedValue(generator = "sec_equipo")
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 45)
    @Column(name = "serie")
    private String serie;
    @Size(max = 75)
    @Column(name = "inventario")
    private String inventario;
    @Column(name = "existencia")
    private Short existencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_adquisicion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAdquisicion;
    @Size(max = 45)
    @Column(name = "ip")
    private String ip;
    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "id_area_asignado", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CtlArea idAreaAsignado;
    @JoinColumn(name = "id_categoria_equipo", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CtlCategoriaEquipo idCategoriaEquipo;
    @JoinColumn(name = "id_empleado_asignado", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TEmpleado idEmpleadoAsignado;
    @OneToMany(mappedBy = "idEquipoComponente", fetch = FetchType.LAZY)
    private List<CtlEquipo> ctlEquipoList;
    @JoinColumn(name = "id_equipo_componente", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CtlEquipo idEquipoComponente;
    @JoinColumn(name = "id_estado_equipo", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CtlEstadoEquipo idEstadoEquipo;
    @JoinColumn(name = "id_modelo", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CtlModeloEquipo idModelo;
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CtlProveedor idProveedor;
    @JoinColumn(name = "id_user_mod", referencedColumnName = "username")
    @ManyToOne(fetch = FetchType.LAZY)
    private TUser idUserMod;
    @JoinColumn(name = "id_user_reg", referencedColumnName = "username")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TUser idUserReg;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEquipo", fetch = FetchType.LAZY)
    private List<CtlInventarioDefectuoso> ctlInventarioDefectuosoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEquipo", fetch = FetchType.LAZY)
    private List<TDetalleSolicitud> tDetalleSolicitudList;
    @OneToMany(mappedBy = "idEquipoIntercambio", fetch = FetchType.LAZY)
    private List<TInsumoUtilizado> tInsumoUtilizadoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEquipo", fetch = FetchType.LAZY)
    private List<TDetallePrestamo> tDetallePrestamoList;

    public CtlEquipo() {
    }

    public CtlEquipo(Long id) {
        this.id = id;
    }

    public CtlEquipo(Long id, String nombre, Date fechaAdquisicion) {
        this.id = id;
        this.nombre = nombre;
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getInventario() {
        return inventario;
    }

    public void setInventario(String inventario) {
        this.inventario = inventario;
    }

    public Short getExistencia() {
        return existencia;
    }

    public void setExistencia(Short existencia) {
        this.existencia = existencia;
    }

    public Date getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(Date fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CtlArea getIdAreaAsignado() {
        return idAreaAsignado;
    }

    public void setIdAreaAsignado(CtlArea idAreaAsignado) {
        this.idAreaAsignado = idAreaAsignado;
    }

    public CtlCategoriaEquipo getIdCategoriaEquipo() {
        return idCategoriaEquipo;
    }

    public void setIdCategoriaEquipo(CtlCategoriaEquipo idCategoriaEquipo) {
        this.idCategoriaEquipo = idCategoriaEquipo;
    }

    public TEmpleado getIdEmpleadoAsignado() {
        return idEmpleadoAsignado;
    }

    public void setIdEmpleadoAsignado(TEmpleado idEmpleadoAsignado) {
        this.idEmpleadoAsignado = idEmpleadoAsignado;
    }

    @XmlTransient
    public List<CtlEquipo> getCtlEquipoList() {
        return ctlEquipoList;
    }

    public void setCtlEquipoList(List<CtlEquipo> ctlEquipoList) {
        this.ctlEquipoList = ctlEquipoList;
    }

    public CtlEquipo getIdEquipoComponente() {
        return idEquipoComponente;
    }

    public void setIdEquipoComponente(CtlEquipo idEquipoComponente) {
        this.idEquipoComponente = idEquipoComponente;
    }

    public CtlEstadoEquipo getIdEstadoEquipo() {
        return idEstadoEquipo;
    }

    public void setIdEstadoEquipo(CtlEstadoEquipo idEstadoEquipo) {
        this.idEstadoEquipo = idEstadoEquipo;
    }

    public CtlModeloEquipo getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(CtlModeloEquipo idModelo) {
        this.idModelo = idModelo;
    }

    public CtlProveedor getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(CtlProveedor idProveedor) {
        this.idProveedor = idProveedor;
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

    @XmlTransient
    public List<CtlInventarioDefectuoso> getCtlInventarioDefectuosoList() {
        return ctlInventarioDefectuosoList;
    }

    public void setCtlInventarioDefectuosoList(List<CtlInventarioDefectuoso> ctlInventarioDefectuosoList) {
        this.ctlInventarioDefectuosoList = ctlInventarioDefectuosoList;
    }

    @XmlTransient
    public List<TDetalleSolicitud> getTDetalleSolicitudList() {
        return tDetalleSolicitudList;
    }

    public void setTDetalleSolicitudList(List<TDetalleSolicitud> tDetalleSolicitudList) {
        this.tDetalleSolicitudList = tDetalleSolicitudList;
    }

    @XmlTransient
    public List<TInsumoUtilizado> getTInsumoUtilizadoList() {
        return tInsumoUtilizadoList;
    }

    public void setTInsumoUtilizadoList(List<TInsumoUtilizado> tInsumoUtilizadoList) {
        this.tInsumoUtilizadoList = tInsumoUtilizadoList;
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
        if (!(object instanceof CtlEquipo)) {
            return false;
        }
        CtlEquipo other = (CtlEquipo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[Inventario: " + this.inventario + "] " + this.nombre;
        // return "org.itca.requerimientos.model.entities.CtlEquipo[ id=" + id + " ]";
    }
    
}
