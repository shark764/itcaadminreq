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
@Table(name = "t_prestamo", catalog = "dbrequerimientos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TPrestamo.findByEmployee", query = "SELECT t FROM TPrestamo t WHERE t.idEmpleado.id = :id"),
    @NamedQuery(name = "TPrestamo.entryRange", query = "SELECT t FROM TPrestamo t WHERE t.fecha >= :start AND t.fecha <= :end"),
    @NamedQuery(name = "TPrestamo.findAll", query = "SELECT t FROM TPrestamo t"),
    @NamedQuery(name = "TPrestamo.findById", query = "SELECT t FROM TPrestamo t WHERE t.id = :id"),
    @NamedQuery(name = "TPrestamo.findByCodigo", query = "SELECT t FROM TPrestamo t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TPrestamo.findByFecha", query = "SELECT t FROM TPrestamo t WHERE t.fecha = :fecha")})
public class TPrestamo implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableGenerator(name = "sec_prestamo",
            table = "t_sequence",
            pkColumnName = "sequence_name",
            valueColumnName = "last_value",
            pkColumnValue = "sec_prestamo")
    @Id
    @GeneratedValue(generator = "sec_prestamo")
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Size(max = 6)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "id_area", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CtlArea idArea;
    @JoinColumn(name = "id_empleado", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TEmpleado idEmpleado;
    @JoinColumn(name = "id_user_mod", referencedColumnName = "username")
    @ManyToOne(fetch = FetchType.LAZY)
    private TUser idUserMod;
    @JoinColumn(name = "id_user_reg", referencedColumnName = "username")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TUser idUserReg;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPrestamo", fetch = FetchType.LAZY)
    private List<TDetallePrestamo> tDetallePrestamoList;

    public TPrestamo() {
    }

    public TPrestamo(Long id) {
        this.id = id;
    }

    public TPrestamo(Long id, Date fecha) {
        this.id = id;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public CtlArea getIdArea() {
        return idArea;
    }

    public void setIdArea(CtlArea idArea) {
        this.idArea = idArea;
    }

    public TEmpleado getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(TEmpleado idEmpleado) {
        this.idEmpleado = idEmpleado;
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
        if (!(object instanceof TPrestamo)) {
            return false;
        }
        TPrestamo other = (TPrestamo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + this.idArea + "] " + this.fecha;
        // return "org.itca.requerimientos.model.entities.TPrestamo[ id=" + id + " ]";
    }
    
}
