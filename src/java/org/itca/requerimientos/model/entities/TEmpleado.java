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
@Table(name = "t_empleado", catalog = "dbrequerimientos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TEmpleado.findByBoss", query = "SELECT t FROM TEmpleado t WHERE t.idEmpleado.id = :id"),
    @NamedQuery(name = "TEmpleado.findAll", query = "SELECT t FROM TEmpleado t"),
    @NamedQuery(name = "TEmpleado.findById", query = "SELECT t FROM TEmpleado t WHERE t.id = :id"),
    @NamedQuery(name = "TEmpleado.findByNombre", query = "SELECT t FROM TEmpleado t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TEmpleado.findByApellido", query = "SELECT t FROM TEmpleado t WHERE t.apellido = :apellido"),
    @NamedQuery(name = "TEmpleado.findByGenero", query = "SELECT t FROM TEmpleado t WHERE t.genero = :genero"),
    @NamedQuery(name = "TEmpleado.findByDui", query = "SELECT t FROM TEmpleado t WHERE t.dui = :dui"),
    @NamedQuery(name = "TEmpleado.findByNit", query = "SELECT t FROM TEmpleado t WHERE t.nit = :nit"),
    @NamedQuery(name = "TEmpleado.findByDireccion", query = "SELECT t FROM TEmpleado t WHERE t.direccion = :direccion"),
    @NamedQuery(name = "TEmpleado.findByTelefono", query = "SELECT t FROM TEmpleado t WHERE t.telefono = :telefono"),
    @NamedQuery(name = "TEmpleado.findByCelular", query = "SELECT t FROM TEmpleado t WHERE t.celular = :celular"),
    @NamedQuery(name = "TEmpleado.findByExtension", query = "SELECT t FROM TEmpleado t WHERE t.extension = :extension"),
    @NamedQuery(name = "TEmpleado.findByFechaContratacion", query = "SELECT t FROM TEmpleado t WHERE t.fechaContratacion = :fechaContratacion"),
    @NamedQuery(name = "TEmpleado.findByEMail", query = "SELECT t FROM TEmpleado t WHERE t.eMail = :eMail")})
public class TEmpleado implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableGenerator(name = "sec_empleado",
            table = "t_sequence",
            pkColumnName = "sequence_name",
            valueColumnName = "last_value",
            pkColumnValue = "sec_empleado")
    @Id
    @GeneratedValue(generator = "sec_empleado")
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "apellido")
    private String apellido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "genero")
    private Character genero;
    @Size(max = 10)
    @Column(name = "dui")
    private String dui;
    @Size(max = 17)
    @Column(name = "nit")
    private String nit;
    @Size(max = 255)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 15)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 15)
    @Column(name = "celular")
    private String celular;
    @Size(max = 10)
    @Column(name = "extension")
    private String extension;
    @Column(name = "fecha_contratacion")
    @Temporal(TemporalType.DATE)
    private Date fechaContratacion;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 75)
    @Column(name = "e_mail")
    private String eMail;
    @OneToMany(mappedBy = "idEmpleadoAsignado", fetch = FetchType.LAZY)
    private List<CtlEquipo> ctlEquipoList;
    @OneToMany(mappedBy = "idEmpleado", fetch = FetchType.LAZY)
    private List<TUser> tUserList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpleado", fetch = FetchType.LAZY)
    private List<TSolicitudRequerimiento> tSolicitudRequerimientoList;
    @OneToMany(mappedBy = "idTecnicoAsignado", fetch = FetchType.LAZY)
    private List<TDetalleSolicitud> tDetalleSolicitudList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpleado", fetch = FetchType.LAZY)
    private List<TPrestamo> tPrestamoList;
    @JoinColumn(name = "id_area", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CtlArea idArea;
    @OneToMany(mappedBy = "idEmpleado", fetch = FetchType.LAZY)
    private List<TEmpleado> tEmpleadoList;
    @JoinColumn(name = "id_empleado", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TEmpleado idEmpleado;
    @JoinColumn(name = "id_tipo_empleado", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CtlTipoEmpleado idTipoEmpleado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAutoriza", fetch = FetchType.LAZY)
    private List<TMantenimiento> tMantenimientoList;
    @OneToMany(mappedBy = "idCoordina", fetch = FetchType.LAZY)
    private List<TMantenimiento> tMantenimientoList1;

    public TEmpleado() {
    }

    public TEmpleado(Integer id) {
        this.id = id;
    }

    public TEmpleado(Integer id, String nombre, String apellido, Character genero) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Character getGenero() {
        return genero;
    }

    public void setGenero(Character genero) {
        this.genero = genero;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Date getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(Date fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    @XmlTransient
    public List<CtlEquipo> getCtlEquipoList() {
        return ctlEquipoList;
    }

    public void setCtlEquipoList(List<CtlEquipo> ctlEquipoList) {
        this.ctlEquipoList = ctlEquipoList;
    }

    @XmlTransient
    public List<TUser> getTUserList() {
        return tUserList;
    }

    public void setTUserList(List<TUser> tUserList) {
        this.tUserList = tUserList;
    }

    @XmlTransient
    public List<TSolicitudRequerimiento> getTSolicitudRequerimientoList() {
        return tSolicitudRequerimientoList;
    }

    public void setTSolicitudRequerimientoList(List<TSolicitudRequerimiento> tSolicitudRequerimientoList) {
        this.tSolicitudRequerimientoList = tSolicitudRequerimientoList;
    }

    @XmlTransient
    public List<TDetalleSolicitud> getTDetalleSolicitudList() {
        return tDetalleSolicitudList;
    }

    public void setTDetalleSolicitudList(List<TDetalleSolicitud> tDetalleSolicitudList) {
        this.tDetalleSolicitudList = tDetalleSolicitudList;
    }

    @XmlTransient
    public List<TPrestamo> getTPrestamoList() {
        return tPrestamoList;
    }

    public void setTPrestamoList(List<TPrestamo> tPrestamoList) {
        this.tPrestamoList = tPrestamoList;
    }

    public CtlArea getIdArea() {
        return idArea;
    }

    public void setIdArea(CtlArea idArea) {
        this.idArea = idArea;
    }

    @XmlTransient
    public List<TEmpleado> getTEmpleadoList() {
        return tEmpleadoList;
    }

    public void setTEmpleadoList(List<TEmpleado> tEmpleadoList) {
        this.tEmpleadoList = tEmpleadoList;
    }

    public TEmpleado getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(TEmpleado idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public CtlTipoEmpleado getIdTipoEmpleado() {
        return idTipoEmpleado;
    }

    public void setIdTipoEmpleado(CtlTipoEmpleado idTipoEmpleado) {
        this.idTipoEmpleado = idTipoEmpleado;
    }

    @XmlTransient
    public List<TMantenimiento> getTMantenimientoList() {
        return tMantenimientoList;
    }

    public void setTMantenimientoList(List<TMantenimiento> tMantenimientoList) {
        this.tMantenimientoList = tMantenimientoList;
    }

    @XmlTransient
    public List<TMantenimiento> getTMantenimientoList1() {
        return tMantenimientoList1;
    }

    public void setTMantenimientoList1(List<TMantenimiento> tMantenimientoList1) {
        this.tMantenimientoList1 = tMantenimientoList1;
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
        if (!(object instanceof TEmpleado)) {
            return false;
        }
        TEmpleado other = (TEmpleado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + this.idArea + "] " + this.apellido + ", " + this.nombre;
        // return "org.itca.requerimientos.model.entities.TEmpleado[ id=" + id + " ]";
    }
    
}
