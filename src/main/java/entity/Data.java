/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sawad
 */
@Entity
@Table(name = "data")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Data.findAll", query = "SELECT d FROM Data d")
    , @NamedQuery(name = "Data.findById", query = "SELECT d FROM Data d WHERE d.id = :id")
    , @NamedQuery(name = "Data.findByTotal", query = "SELECT d FROM Data d WHERE d.total = :total")
    , @NamedQuery(name = "Data.findByUsed", query = "SELECT d FROM Data d WHERE d.used = :used")
    , @NamedQuery(name = "Data.findByGatherDate", query = "SELECT d FROM Data d WHERE d.gatherDate = :gatherDate")})
public class Data implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total")
    private Double total;
    @Column(name = "used")
    private Double used;
    @Size(max = 10)
    @Column(name = "gather_date")
    private String gatherDate;
    @JoinColumn(name = "type", referencedColumnName = "id")
    @ManyToOne
    private Category type;

    public Data() {
    }

    public Data(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getUsed() {
        return used;
    }

    public void setUsed(Double used) {
        this.used = used;
    }

    public String getGatherDate() {
        return gatherDate;
    }

    public void setGatherDate(String gatherDate) {
        this.gatherDate = gatherDate;
    }

    public Category getType() {
        return type;
    }

    public void setType(Category type) {
        this.type = type;
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
        if (!(object instanceof Data)) {
            return false;
        }
        Data other = (Data) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Data{" + "id=" + id + ", total=" + total + ", used=" + used + ", gatherDate=" + gatherDate + ", type=" + type + '}';
    }
    
}
