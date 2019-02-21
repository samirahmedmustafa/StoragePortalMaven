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
@Table(name = "BackupStatus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BackupStatus.findAll", query = "SELECT b FROM BackupStatus b")
    , @NamedQuery(name = "BackupStatus.findById", query = "SELECT b FROM BackupStatus b WHERE b.id = :id")
    , @NamedQuery(name = "BackupStatus.findByDomains", query = "SELECT b FROM BackupStatus b WHERE b.domains = :domains")
    , @NamedQuery(name = "BackupStatus.findByGroups", query = "SELECT b FROM BackupStatus b WHERE b.groups = :groups")
    , @NamedQuery(name = "BackupStatus.findByCompleted", query = "SELECT b FROM BackupStatus b WHERE b.completed = :completed")
    , @NamedQuery(name = "BackupStatus.findBySucceeded", query = "SELECT b FROM BackupStatus b WHERE b.succeeded = :succeeded")
    , @NamedQuery(name = "BackupStatus.findByFailed", query = "SELECT b FROM BackupStatus b WHERE b.failed = :failed")
    , @NamedQuery(name = "BackupStatus.findBySizeinGB", query = "SELECT b FROM BackupStatus b WHERE b.sizeinGB = :sizeinGB")
    , @NamedQuery(name = "BackupStatus.findByDate", query = "SELECT b FROM BackupStatus b WHERE b.mydate LIKE :mydate")
    , @NamedQuery(name = "BackupStatus.findByServer", query = "SELECT b FROM BackupStatus b WHERE b.server = :server")})
public class BackupStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 100)
    @Column(name = "Domains")
    private String domains;
    @Size(max = 100)
    @Column(name = "Groups")
    private String groups;
    @Column(name = "Completed")
    private Integer completed;
    @Column(name = "Succeeded")
    private Integer succeeded;
    @Column(name = "Failed")
    private Integer failed;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Size_in_GB")
    private Double sizeinGB;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date mydate;
    @Size(max = 100)
    @Column(name = "Server")
    private String server;

    public BackupStatus() {
    }

    public BackupStatus(Integer id) {
        this.id = id;
    }

    public BackupStatus(Integer id, Date date) {
        this.id = id;
        this.mydate = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDomains() {
        return domains;
    }

    public void setDomains(String domains) {
        this.domains = domains;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public Integer getCompleted() {
        return completed;
    }

    public void setCompleted(Integer completed) {
        this.completed = completed;
    }

    public Integer getSucceeded() {
        return succeeded;
    }

    public void setSucceeded(Integer succeeded) {
        this.succeeded = succeeded;
    }

    public Integer getFailed() {
        return failed;
    }

    public void setFailed(Integer failed) {
        this.failed = failed;
    }

    public Double getSizeinGB() {
        return sizeinGB;
    }

    public void setSizeinGB(Double sizeinGB) {
        this.sizeinGB = sizeinGB;
    }

    public Date getMydate() {
        return mydate;
    }

    public void setMydate(Date mydate) {
        this.mydate = mydate;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
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
        if (!(object instanceof BackupStatus)) {
            return false;
        }
        BackupStatus other = (BackupStatus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.BackupStatus[ id=" + id + " ]";
    }
    
}
