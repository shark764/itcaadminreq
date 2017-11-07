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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.itca.requerimientos.model.entities.jasper.ModeloEquipoJasper;

/**
 *
 * @author dfhernandez
 */
@Entity
@Table(name = "ctl_modelo_equipo", catalog = "dbrequerimientos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CtlModeloEquipo.findAll", query = "SELECT c FROM CtlModeloEquipo c"),
    @NamedQuery(name = "CtlModeloEquipo.findById", query = "SELECT c FROM CtlModeloEquipo c WHERE c.id = :id"),
    @NamedQuery(name = "CtlModeloEquipo.findByNombre", query = "SELECT c FROM CtlModeloEquipo c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CtlModeloEquipo.findByCodigo", query = "SELECT c FROM CtlModeloEquipo c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "CtlModeloEquipo.findByExistencia", query = "SELECT c FROM CtlModeloEquipo c WHERE c.existencia = :existencia")})
@NamedNativeQueries({
    @NamedNativeQuery(name = "CtlModeloEquipo.stockByEquipmentModelReport", query = "SELECT t01.id AS t01id, t01.codigo AS t01codigo, t01.nombre AS t01nombre, t02.id AS t02id, t02.codigo AS t02codigo, t02.nombre AS t02nombre, t02.existencia AS t02existencia FROM ctl_modelo_equipo AS t02 JOIN ctl_marca_equipo AS t01 ON t01.id = t02.id_marca ORDER BY 7 DESC, 3, 6", resultSetMapping = "ModeloEquipoJasperValueMapping"),
})
@SqlResultSetMappings({
    @SqlResultSetMapping(
	    name = "ModeloEquipoJasperValueMapping",
	    classes = @ConstructorResult(
		    targetClass = ModeloEquipoJasper.class,
		    columns = {
			@ColumnResult(name = "t01id", type = Short.class),
			@ColumnResult(name = "t01codigo"),
			@ColumnResult(name = "t01nombre"),
			@ColumnResult(name = "t02id", type = Short.class),
			@ColumnResult(name = "t02codigo"),
			@ColumnResult(name = "t02nombre"),
			@ColumnResult(name = "t02existencia", type = Short.class)
		    }
	    )
    )
})
public class CtlModeloEquipo implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableGenerator(name = "sec_modelo_equipo",
            table = "t_sequence",
            pkColumnName = "sequence_name",
            valueColumnName = "last_value",
            pkColumnValue = "sec_modelo_equipo")
    @Id
    @GeneratedValue(generator = "sec_modelo_equipo")
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Short id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 3)
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "existencia")
    private Short existencia;
    @OneToMany(mappedBy = "idModelo", fetch = FetchType.LAZY)
    private List<CtlEquipo> ctlEquipoList;
    @JoinColumn(name = "id_marca", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CtlMarcaEquipo idMarca;

    public CtlModeloEquipo() {
    }

    public CtlModeloEquipo(Short id) {
        this.id = id;
    }

    public CtlModeloEquipo(Short id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public CtlModeloEquipo(CtlMarcaEquipo idMarca) {
        this.idMarca = idMarca;
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

    public Short getExistencia() {
        return existencia;
    }

    public void setExistencia(Short existencia) {
        this.existencia = existencia;
    }

    @XmlTransient
    public List<CtlEquipo> getCtlEquipoList() {
        return ctlEquipoList;
    }

    public void setCtlEquipoList(List<CtlEquipo> ctlEquipoList) {
        this.ctlEquipoList = ctlEquipoList;
    }

    public CtlMarcaEquipo getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(CtlMarcaEquipo idMarca) {
        this.idMarca = idMarca;
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
        if (!(object instanceof CtlModeloEquipo)) {
            return false;
        }
        CtlModeloEquipo other = (CtlModeloEquipo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + this.codigo + "] " + this.nombre;
        // return "org.itca.requerimientos.model.entities.CtlModeloEquipo[ id=" + id + " ]";
    }
    
}
