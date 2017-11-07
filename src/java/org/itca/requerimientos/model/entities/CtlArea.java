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
@Table(name = "ctl_area", catalog = "dbrequerimientos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CtlArea.findAll", query = "SELECT c FROM CtlArea c"),
    @NamedQuery(name = "CtlArea.findById", query = "SELECT c FROM CtlArea c WHERE c.id = :id"),
    @NamedQuery(name = "CtlArea.findByNombre", query = "SELECT c FROM CtlArea c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CtlArea.findByCodigo", query = "SELECT c FROM CtlArea c WHERE c.codigo = :codigo")})
public class CtlArea implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableGenerator(name = "sec_area",
            table = "t_sequence",
            pkColumnName = "sequence_name",
            valueColumnName = "last_value",
            pkColumnValue = "sec_area")
    @Id
    @GeneratedValue(generator = "sec_area")
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Short id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "codigo")
    private String codigo;
    @OneToMany(mappedBy = "idAreaAsignado", fetch = FetchType.LAZY)
    private List<CtlEquipo> ctlEquipoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArea", fetch = FetchType.LAZY)
    private List<TSolicitudRequerimiento> tSolicitudRequerimientoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArea", fetch = FetchType.LAZY)
    private List<TPrestamo> tPrestamoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArea", fetch = FetchType.LAZY)
    private List<TEmpleado> tEmpleadoList;

    public CtlArea() {
    }

    public CtlArea(Short id) {
        this.id = id;
    }

    public CtlArea(Short id, String nombre, String codigo) {
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
    public List<CtlEquipo> getCtlEquipoList() {
        return ctlEquipoList;
    }

    public void setCtlEquipoList(List<CtlEquipo> ctlEquipoList) {
        this.ctlEquipoList = ctlEquipoList;
    }

    @XmlTransient
    public List<TSolicitudRequerimiento> getTSolicitudRequerimientoList() {
        return tSolicitudRequerimientoList;
    }

    public void setTSolicitudRequerimientoList(List<TSolicitudRequerimiento> tSolicitudRequerimientoList) {
        this.tSolicitudRequerimientoList = tSolicitudRequerimientoList;
    }

    @XmlTransient
    public List<TPrestamo> getTPrestamoList() {
        return tPrestamoList;
    }

    public void setTPrestamoList(List<TPrestamo> tPrestamoList) {
        this.tPrestamoList = tPrestamoList;
    }

    @XmlTransient
    public List<TEmpleado> getTEmpleadoList() {
        return tEmpleadoList;
    }

    public void setTEmpleadoList(List<TEmpleado> tEmpleadoList) {
        this.tEmpleadoList = tEmpleadoList;
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
        if (!(object instanceof CtlArea)) {
            return false;
        }
        CtlArea other = (CtlArea) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + this.codigo + "] " + this.nombre;
        // return "org.itca.requerimientos.model.entities.CtlArea[ id=" + id + " ]";
    }
    
}
