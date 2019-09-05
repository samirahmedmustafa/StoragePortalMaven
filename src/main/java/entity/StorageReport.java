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
@Table(name = "Storage_report")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StorageReport.findAll", query = "SELECT s FROM StorageReport s")
    , @NamedQuery(name = "StorageReport.findTop10", query = "SELECT s FROM StorageReport s WHERE s.site = :site AND s.date = :date order by s.allocated desc")
    , @NamedQuery(name = "StorageReport.findById", query = "SELECT s FROM StorageReport s WHERE s.id = :id")
    , @NamedQuery(name = "StorageReport.findBySite", query = "SELECT s FROM StorageReport s WHERE s.site = :site")
    , @NamedQuery(name = "StorageReport.findByStorageGroup", query = "SELECT s FROM StorageReport s WHERE s.storageGroup = :storageGroup")
    , @NamedQuery(name = "StorageReport.findByTotal", query = "SELECT s FROM StorageReport s WHERE s.total = :total")
    , @NamedQuery(name = "StorageReport.findByAllocated", query = "SELECT s FROM StorageReport s WHERE s.allocated = :allocated")
    , @NamedQuery(name = "StorageReport.findByDate", query = "SELECT s FROM StorageReport s WHERE s.date = :date")})
public class StorageReport implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 50)
    @Column(name = "SITE")
    private String site;
    @Size(max = 100)
    @Column(name = "STORAGE_GROUP")
    private String storageGroup;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TOTAL")
    private Double total;
    @Column(name = "ALLOCATED")
    private Double allocated;
    @Column(name = "PROVISIONED")
    private Double provisioned;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private Date date;

    public StorageReport() {
    }

    public StorageReport(Integer id) {
        this.id = id;
    }

    public StorageReport(Integer id, Date date) {
        this.id = id;
        this.date = date;
    }

    public Double getProvisioned() {
        return provisioned;
    }

    public void setProvisioned(Double provisioned) {
        this.provisioned = provisioned;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getStorageGroup() {
        return storageGroup;
    }

    public void setStorageGroup(String storageGroup) {
        this.storageGroup = storageGroup;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getAllocated() {
        return allocated;
    }

    public void setAllocated(Double allocated) {
        this.allocated = allocated;
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
        if (!(object instanceof StorageReport)) {
            return false;
        }
        StorageReport other = (StorageReport) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.StorageReport[ id=" + id + " ]";
    }
    
}
