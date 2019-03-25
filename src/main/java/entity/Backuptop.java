/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sawad
 */
@Entity
@Table(name = "Backup_top")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Backuptop.findAll", query = "SELECT b FROM Backuptop b")
    , @NamedQuery(name = "Backuptop.findById", query = "SELECT b FROM Backuptop b WHERE b.id = :id")
    , @NamedQuery(name = "Backuptop.findByName", query = "SELECT b FROM Backuptop b WHERE b.name = :name")
    , @NamedQuery(name = "Backuptop.findByPercentageoftotal", query = "SELECT b FROM Backuptop b WHERE b.percentageoftotal = :percentageoftotal")
    , @NamedQuery(name = "Backuptop.findByChangerate", query = "SELECT b FROM Backuptop b WHERE b.changerate = :changerate")
    , @NamedQuery(name = "Backuptop.findBySizeGB", query = "SELECT b FROM Backuptop b WHERE b.sizeGB = :sizeGB")
    , @NamedQuery(name = "Backuptop.findByDate", query = "SELECT b FROM Backuptop b WHERE b.date = :date")})
public class Backuptop implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 100)
    @Column(name = "Name")
    private String name;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Percentage_of_total")
    private Double percentageoftotal;
    @Column(name = "changerate")
    private Double changerate;
    @Column(name = "Size_GB")
    private Double sizeGB;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Date")
    @Temporal(TemporalType.DATE)
    private Date date;

    public Backuptop() {
    }

    public Backuptop(Integer id) {
        this.id = id;
    }

    public Backuptop(Integer id, Date date) {
        this.id = id;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPercentageoftotal() {
        return percentageoftotal;
    }

    public void setPercentageoftotal(Double percentageoftotal) {
        this.percentageoftotal = percentageoftotal;
    }

    public Double getChangerate() {
        return changerate;
    }

    public void setChangerate(Double changerate) {
        this.changerate = changerate;
    }

    public Double getSizeGB() {
        return sizeGB;
    }

    public void setSizeGB(Double sizeGB) {
        this.sizeGB = sizeGB;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
        if (!(object instanceof Backuptop)) {
            return false;
        }
        Backuptop other = (Backuptop) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Backuptop[ id=" + id + " ]";
    }
    
}
