/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itca.requerimientos.model.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author dfhernandez
 */
@Entity
@Table(name = "ctl_tipo_requerimiento", catalog = "dbrequerimientos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CtlTipoRequerimiento.findAll", query = "SELECT c FROM CtlTipoRequerimiento c"),
    @NamedQuery(name = "CtlTipoRequerimiento.findById", query = "SELECT c FROM CtlTipoRequerimiento c WHERE c.id = :id"),
    @NamedQuery(name = "CtlTipoRequerimiento.findByNombre", query = "SELECT c FROM CtlTipoRequerimiento c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CtlTipoRequerimiento.findByCodigo", query = "SELECT c FROM CtlTipoRequerimiento c WHERE c.codigo = :codigo")})
public class CtlTipoRequerimiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Short id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "codigo")
    private String codigo;
    @JoinColumn(name = "id_area_requerimiento", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CtlAreaRequerimiento idAreaRequerimiento;
    @OneToMany(mappedBy = "idTipoRequerimiento", fetch = FetchType.LAZY)
    private List<TDetalleSolicitud> tDetalleSolicitudList;

    public CtlTipoRequerimiento() {
    }

    public CtlTipoRequerimiento(Short id) {
        this.id = id;
    }

    public CtlTipoRequerimiento(Short id, String nombre, String codigo) {
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

    public CtlAreaRequerimiento getIdAreaRequerimiento() {
        return idAreaRequerimiento;
    }

    public void setIdAreaRequerimiento(CtlAreaRequerimiento idAreaRequerimiento) {
        this.idAreaRequerimiento = idAreaRequerimiento;
    }

    @XmlTransient
    public List<TDetalleSolicitud> getTDetalleSolicitudList() {
        return tDetalleSolicitudList;
    }

    public void setTDetalleSolicitudList(List<TDetalleSolicitud> tDetalleSolicitudList) {
        this.tDetalleSolicitudList = tDetalleSolicitudList;
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
        if (!(object instanceof CtlTipoRequerimiento)) {
            return false;
        }
        CtlTipoRequerimiento other = (CtlTipoRequerimiento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.itca.requerimientos.model.entities.CtlTipoRequerimiento[ id=" + id + " ]";
    }
    
}
