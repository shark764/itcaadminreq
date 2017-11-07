/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itca.requerimientos.model.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.itca.requerimientos.model.entities.jasper.InventarioDefectuosoJasper;

/**
 *
 * @author dfhernandez
 */
@Entity
@Table(name = "ctl_inventario_defectuoso", catalog = "dbrequerimientos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CtlInventarioDefectuoso.findByEquipmentModel", query = "SELECT c FROM CtlInventarioDefectuoso c WHERE c.idEquipo.idModelo.id = :id"),
    @NamedQuery(name = "CtlInventarioDefectuoso.entryRange", query = "SELECT c FROM CtlInventarioDefectuoso c WHERE c.fechaIngreso >= :start AND c.fechaIngreso <= :end"),
    @NamedQuery(name = "CtlInventarioDefectuoso.findAll", query = "SELECT c FROM CtlInventarioDefectuoso c"),
    @NamedQuery(name = "CtlInventarioDefectuoso.findById", query = "SELECT c FROM CtlInventarioDefectuoso c WHERE c.id = :id"),
    @NamedQuery(name = "CtlInventarioDefectuoso.findByDescripcion", query = "SELECT c FROM CtlInventarioDefectuoso c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CtlInventarioDefectuoso.findByFechaIngreso", query = "SELECT c FROM CtlInventarioDefectuoso c WHERE c.fechaIngreso = :fechaIngreso")})
@NamedNativeQueries({
    @NamedNativeQuery(name = "CtlInventarioDefectuoso.defectiveInventoryReport", query = "SELECT t01.id AS t01id, t01.codigo AS t01codigo, t01.nombre AS t01nombre, t02.id AS t02id, t02.codigo AS t02codigo, t02.nombre AS t02nombre, t02.existencia AS t02existencia, t03.nombre AS t03nombre, t03.inventario AS t03inventario, t03.serie AS t03serie, t04.fecha_ingreso AS t04fecha FROM inventario_defectuoso AS t04 JOIN equipo AS t03 ON t03.id = t04.id_equipo JOIN modelo_equipo AS t02 ON t02.id = t03.id_modelo JOIN marca_equipo AS t01 ON t01.id = t02.id_marca ORDER BY 11 DESC, 3, 6", resultSetMapping = "InventarioDefectuosoJasperValueMapping"),
})
@SqlResultSetMappings({
    @SqlResultSetMapping(
        name = "InventarioDefectuosoJasperValueMapping",
        classes = @ConstructorResult(
            targetClass = InventarioDefectuosoJasper.class,
            columns = {
                @ColumnResult(name = "t01id", type = Short.class),
                @ColumnResult(name = "t01codigo"),
                @ColumnResult(name = "t01nombre"),
                @ColumnResult(name = "t02id", type = Short.class),
                @ColumnResult(name = "t02codigo"),
                @ColumnResult(name = "t02nombre"),
                @ColumnResult(name = "t02existencia", type = Short.class),
                @ColumnResult(name = "t03id", type = Long.class),
                @ColumnResult(name = "t03nombre"),
                @ColumnResult(name = "t03inventario"),
                @ColumnResult(name = "t03serie"),
                @ColumnResult(name = "t04fecha", type = Date.class)
            }
        )
    )
})
public class CtlInventarioDefectuoso implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableGenerator(name = "sec_inventario_defectuoso",
            table = "t_sequence",
            pkColumnName = "sequence_name",
            valueColumnName = "last_value",
            pkColumnValue = "sec_inventario_defectuoso")
    @Id
    @GeneratedValue(generator = "sec_inventario_defectuoso")
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @JoinColumn(name = "id_equipo", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CtlEquipo idEquipo;

    public CtlInventarioDefectuoso() {
    }

    public CtlInventarioDefectuoso(Long id) {
        this.id = id;
    }

    public CtlInventarioDefectuoso(Long id, Date fechaIngreso) {
        this.id = id;
        this.fechaIngreso = fechaIngreso;
    }

    public CtlInventarioDefectuoso(CtlEquipo idEquipo) {
        this.idEquipo = idEquipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public CtlEquipo getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(CtlEquipo idEquipo) {
        this.idEquipo = idEquipo;
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
        if (!(object instanceof CtlInventarioDefectuoso)) {
            return false;
        }
        CtlInventarioDefectuoso other = (CtlInventarioDefectuoso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + this.idEquipo + "] " + this.fechaIngreso;
        // return "org.itca.requerimientos.model.entities.CtlInventarioDefectuoso[ id=" + id + " ]";
    }
    
}
