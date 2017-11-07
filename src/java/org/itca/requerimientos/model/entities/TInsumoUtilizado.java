/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itca.requerimientos.model.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dfhernandez
 */
@Entity
@Table(name = "t_insumo_utilizado", catalog = "dbrequerimientos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TInsumoUtilizado.findAll", query = "SELECT t FROM TInsumoUtilizado t"),
    @NamedQuery(name = "TInsumoUtilizado.findById", query = "SELECT t FROM TInsumoUtilizado t WHERE t.id = :id"),
    @NamedQuery(name = "TInsumoUtilizado.findByUtlilizado", query = "SELECT t FROM TInsumoUtilizado t WHERE t.utlilizado = :utlilizado"),
    @NamedQuery(name = "TInsumoUtilizado.findByDesperdicio", query = "SELECT t FROM TInsumoUtilizado t WHERE t.desperdicio = :desperdicio")})
public class TInsumoUtilizado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Column(name = "utlilizado")
    private Short utlilizado;
    @Column(name = "desperdicio")
    private Short desperdicio;
    @JoinColumn(name = "id_detalle_solicitud", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TDetalleSolicitud idDetalleSolicitud;
    @JoinColumn(name = "id_equipo_intercambio", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CtlEquipo idEquipoIntercambio;
    @JoinColumn(name = "id_insumo", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CtlInsumo idInsumo;

    public TInsumoUtilizado() {
    }

    public TInsumoUtilizado(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getUtlilizado() {
        return utlilizado;
    }

    public void setUtlilizado(Short utlilizado) {
        this.utlilizado = utlilizado;
    }

    public Short getDesperdicio() {
        return desperdicio;
    }

    public void setDesperdicio(Short desperdicio) {
        this.desperdicio = desperdicio;
    }

    public TDetalleSolicitud getIdDetalleSolicitud() {
        return idDetalleSolicitud;
    }

    public void setIdDetalleSolicitud(TDetalleSolicitud idDetalleSolicitud) {
        this.idDetalleSolicitud = idDetalleSolicitud;
    }

    public CtlEquipo getIdEquipoIntercambio() {
        return idEquipoIntercambio;
    }

    public void setIdEquipoIntercambio(CtlEquipo idEquipoIntercambio) {
        this.idEquipoIntercambio = idEquipoIntercambio;
    }

    public CtlInsumo getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(CtlInsumo idInsumo) {
        this.idInsumo = idInsumo;
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
        if (!(object instanceof TInsumoUtilizado)) {
            return false;
        }
        TInsumoUtilizado other = (TInsumoUtilizado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.itca.requerimientos.model.entities.TInsumoUtilizado[ id=" + id + " ]";
    }
    
}
