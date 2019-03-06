/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author sawad
 */
@Entity
public class AggrBackupReport implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String group;
    private Integer failed;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Integer getFailed() {
        return failed;
    }

    public void setFailed(Integer failed) {
        this.failed = failed;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.group);
        hash = 89 * hash + Objects.hashCode(this.failed);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AggrBackupReport other = (AggrBackupReport) obj;
        if (!Objects.equals(this.group, other.group)) {
            return false;
        }
        if (!Objects.equals(this.failed, other.failed)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AggrBackupReport{" + "group=" + group + ", failed=" + failed + '}';
    }
}
