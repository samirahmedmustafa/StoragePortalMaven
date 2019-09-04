/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sawad
 */
@Entity
@Table(name = "host_cluster")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HostCluster.findAll", query = "SELECT h FROM HostCluster h")
    , @NamedQuery(name = "HostCluster.findById", query = "SELECT h FROM HostCluster h WHERE h.id = :id")
    , @NamedQuery(name = "HostCluster.findByCluster", query = "SELECT h FROM HostCluster h WHERE h.cluster = :cluster")
    , @NamedQuery(name = "HostCluster.findBystorageView", query = "SELECT h FROM HostCluster h WHERE h.storageView = :storageView")})
public class HostCluster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "cluster")
    private String cluster;
    @Size(max = 50)
    @Column(name = "storage_view")
    private String storageView;

    public HostCluster() {
    }

    public HostCluster(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public String getStorageView() {
        return storageView;
    }

    public void setStorageView(String storageView) {
        this.storageView = storageView;
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
        if (!(object instanceof HostCluster)) {
            return false;
        }
        HostCluster other = (HostCluster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.HostCluster[ id=" + id + " ]";
    }
    
}
