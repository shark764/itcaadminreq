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
@Table(name = "ctl_tipo_mantenimiento", catalog = "dbrequerimientos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CtlTipoMantenimiento.findAll", query = "SELECT c FROM CtlTipoMantenimiento c"),
    @NamedQuery(name = "CtlTipoMantenimiento.findById", query = "SELECT c FROM CtlTipoMantenimiento c WHERE c.id = :id"),
    @NamedQuery(name = "CtlTipoMantenimiento.findByNombre", query = "SELECT c FROM CtlTipoMantenimiento c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CtlTipoMantenimiento.findByCodigo", query = "SELECT c FROM CtlTipoMantenimiento c WHERE c.codigo = :codigo")})
public class CtlTipoMantenimiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableGenerator(name = "sec_tipo_mantenimiento",
            table = "t_sequence",
            pkColumnName = "sequence_name",
            valueColumnName = "last_value",
            pkColumnValue = "sec_tipo_mantenimiento")
    @Id
    @GeneratedValue(generator = "sec_tipo_mantenimiento")
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoMantenimiento", fetch = FetchType.LAZY)
    private List<TMantenimiento> tMantenimientoList;

    public CtlTipoMantenimiento() {
    }

    public CtlTipoMantenimiento(Short id) {
        this.id = id;
    }

    public CtlTipoMantenimiento(Short id, String nombre) {
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
        if (!(object instanceof CtlTipoMantenimiento)) {
            return false;
        }
        CtlTipoMantenimiento other = (CtlTipoMantenimiento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + this.codigo + "] " + this.nombre;
        // return "org.itca.requerimientos.model.entities.CtlTipoMantenimiento[ id=" + id + " ]";
    }
    
}
