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
@Table(name = "ctl_insumo", catalog = "dbrequerimientos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CtlInsumo.stockRange", query = "SELECT c FROM CtlInsumo c WHERE c.existencia >= :start AND c.existencia <= :end"),
    @NamedQuery(name = "CtlInsumo.nonStock", query = "SELECT c FROM CtlInsumo c WHERE c.existencia <= :min"),
    @NamedQuery(name = "CtlInsumo.findAll", query = "SELECT c FROM CtlInsumo c"),
    @NamedQuery(name = "CtlInsumo.findById", query = "SELECT c FROM CtlInsumo c WHERE c.id = :id"),
    @NamedQuery(name = "CtlInsumo.findByNombre", query = "SELECT c FROM CtlInsumo c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CtlInsumo.findByExistencia", query = "SELECT c FROM CtlInsumo c WHERE c.existencia = :existencia"),
    @NamedQuery(name = "CtlInsumo.findByCodigo", query = "SELECT c FROM CtlInsumo c WHERE c.codigo = :codigo")})
public class CtlInsumo implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableGenerator(name = "sec_insumo",
            table = "t_sequence",
            pkColumnName = "sequence_name",
            valueColumnName = "last_value",
            pkColumnValue = "sec_insumo")
    @Id
    @GeneratedValue(generator = "sec_insumo")
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Short id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "existencia")
    private Short existencia;
    @Size(max = 3)
    @Column(name = "codigo")
    private String codigo;
    @OneToMany(mappedBy = "idInsumo", fetch = FetchType.LAZY)
    private List<TInsumoUtilizado> tInsumoUtilizadoList;

    public CtlInsumo() {
    }

    public CtlInsumo(Short id) {
        this.id = id;
    }

    public CtlInsumo(Short id, String nombre) {
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

    public Short getExistencia() {
        return existencia;
    }

    public void setExistencia(Short existencia) {
        this.existencia = existencia;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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
        if (!(object instanceof CtlInsumo)) {
            return false;
        }
        CtlInsumo other = (CtlInsumo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[Existencia: " + this.existencia + "] " + this.nombre;
        // return "org.itca.requerimientos.model.entities.CtlInsumo[ id=" + id + " ]";
    }
    
}
