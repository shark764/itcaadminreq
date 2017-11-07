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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dfhernandez
 */
@Entity
@Table(name = "t_sequence", catalog = "dbrequerimientos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TSequence.findAll", query = "SELECT t FROM TSequence t"),
    @NamedQuery(name = "TSequence.findBySequenceName", query = "SELECT t FROM TSequence t WHERE t.sequenceName = :sequenceName"),
    @NamedQuery(name = "TSequence.findByLastValue", query = "SELECT t FROM TSequence t WHERE t.lastValue = :lastValue")})
public class TSequence implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "sequence_name")
    private String sequenceName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "last_value")
    private long lastValue;

    public TSequence() {
    }

    public TSequence(String sequenceName) {
        this.sequenceName = sequenceName;
    }

    public TSequence(String sequenceName, long lastValue) {
        this.sequenceName = sequenceName;
        this.lastValue = lastValue;
    }

    public String getSequenceName() {
        return sequenceName;
    }

    public void setSequenceName(String sequenceName) {
        this.sequenceName = sequenceName;
    }

    public long getLastValue() {
        return lastValue;
    }

    public void setLastValue(long lastValue) {
        this.lastValue = lastValue;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sequenceName != null ? sequenceName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TSequence)) {
            return false;
        }
        TSequence other = (TSequence) object;
        if ((this.sequenceName == null && other.sequenceName != null) || (this.sequenceName != null && !this.sequenceName.equals(other.sequenceName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.itca.requerimientos.model.entities.TSequence[ sequenceName=" + sequenceName + " ]";
    }
    
}
