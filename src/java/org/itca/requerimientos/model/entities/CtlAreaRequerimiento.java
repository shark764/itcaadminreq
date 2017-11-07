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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author dfhernandez
 */
@Entity
@Table(name = "ctl_area_requerimiento", catalog = "dbrequerimientos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CtlAreaRequerimiento.findAll", query = "SELECT c FROM CtlAreaRequerimiento c"),
    @NamedQuery(name = "CtlAreaRequerimiento.findById", query = "SELECT c FROM CtlAreaRequerimiento c WHERE c.id = :id"),
    @NamedQuery(name = "CtlAreaRequerimiento.findByNombre", query = "SELECT c FROM CtlAreaRequerimiento c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CtlAreaRequerimiento.findByCodigo", query = "SELECT c FROM CtlAreaRequerimiento c WHERE c.codigo = :codigo")})
public class CtlAreaRequerimiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Short id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 3)
    @Column(name = "codigo")
    private String codigo;
    @OneToMany(mappedBy = "idAreaRequerimiento", fetch = FetchType.LAZY)
    private List<CtlTipoRequerimiento> ctlTipoRequerimientoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAreaRequerimiento", fetch = FetchType.LAZY)
    private List<TDetalleSolicitud> tDetalleSolicitudList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAreaRequerimiento", fetch = FetchType.LAZY)
    private List<TMantenimiento> tMantenimientoList;

    public CtlAreaRequerimiento() {
    }

    public CtlAreaRequerimiento(Short id) {
        this.id = id;
    }

    public CtlAreaRequerimiento(Short id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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
    public List<CtlTipoRequerimiento> getCtlTipoRequerimientoList() {
        return ctlTipoRequerimientoList;
    }

    public void setCtlTipoRequerimientoList(List<CtlTipoRequerimiento> ctlTipoRequerimientoList) {
        this.ctlTipoRequerimientoList = ctlTipoRequerimientoList;
    }

    @XmlTransient
    public List<TDetalleSolicitud> getTDetalleSolicitudList() {
        return tDetalleSolicitudList;
    }

    public void setTDetalleSolicitudList(List<TDetalleSolicitud> tDetalleSolicitudList) {
        this.tDetalleSolicitudList = tDetalleSolicitudList;
    }

    @XmlTransient
    public List<TMantenimiento> getTMantenimientoList() {
        return tMantenimientoList;
    }

    public void setTMantenimientoList(List<TMantenimiento> tMantenimientoList) {
        this.tMantenimientoList = tMantenimientoList;
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
        if (!(object instanceof CtlAreaRequerimiento)) {
            return false;
        }
        CtlAreaRequerimiento other = (CtlAreaRequerimiento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.itca.requerimientos.model.entities.CtlAreaRequerimiento[ id=" + id + " ]";
    }
    
}
