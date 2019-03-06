/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.AggrBackupReport;
import entity.BackupStatus;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sawad
 */
@Stateless
public class AggrBackupReportFacade extends AbstractFacade<BackupStatus> {

    @PersistenceContext(unitName = "StoragePortalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AggrBackupReportFacade() {
        super(BackupStatus.class);
    }
    
}
