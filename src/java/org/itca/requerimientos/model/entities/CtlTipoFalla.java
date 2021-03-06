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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ctl_tipo_falla", catalog = "dbrequerimientos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CtlTipoFalla.findAll", query = "SELECT c FROM CtlTipoFalla c"),
    @NamedQuery(name = "CtlTipoFalla.findById", query = "SELECT c FROM CtlTipoFalla c WHERE c.id = :id"),
    @NamedQuery(name = "CtlTipoFalla.findByNombre", query = "SELECT c FROM CtlTipoFalla c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CtlTipoFalla.findByCodigo", query = "SELECT c FROM CtlTipoFalla c WHERE c.codigo = :codigo")})
public class CtlTipoFalla implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableGenerator(name = "sec_tipo_falla",
            table = "t_sequence",
            pkColumnName = "sequence_name",
            valueColumnName = "last_value",
            pkColumnValue = "sec_tipo_falla")
    @Id
    @GeneratedValue(generator = "sec_tipo_falla")
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Short id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "codigo")
    private String codigo;
    @JoinColumn(name = "id_categoria_equipo", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CtlCategoriaEquipo idCategoriaEquipo;
    @OneToMany(mappedBy = "idTipoFalla", fetch = FetchType.LAZY)
    private List<TDetalleSolicitud> tDetalleSolicitudList;

    public CtlTipoFalla() {
    }

    public CtlTipoFalla(Short id) {
        this.id = id;
    }

    public CtlTipoFalla(Short id, String nombre, String codigo) {
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

    public CtlCategoriaEquipo getIdCategoriaEquipo() {
        return idCategoriaEquipo;
    }

    public void setIdCategoriaEquipo(CtlCategoriaEquipo idCategoriaEquipo) {
        this.idCategoriaEquipo = idCategoriaEquipo;
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
        if (!(object instanceof CtlTipoFalla)) {
            return false;
        }
        CtlTipoFalla other = (CtlTipoFalla) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + this.codigo + "] " + this.nombre;
        // return "org.itca.requerimientos.model.entities.CtlTipoFalla[ id=" + id + " ]";
    }
    
}
