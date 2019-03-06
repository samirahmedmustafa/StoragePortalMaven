/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.BackupStatus;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author sawad
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findByDateStorage(String server) {
        return getEntityManager()
                .createNativeQuery("SELECT * FROM BackupStatus WHERE date LIKE '" + new SimpleDateFormat("yyyy-MM-dd")
                        .format(new Date()) + "%' AND server = '" + server + "'", BackupStatus.class)
                .getResultList();
    }

    public List<String> findLast7(String server) {
        return getEntityManager()
                .createNativeQuery("select a.datefield as DATEVALUE,sum(a.Succeeded) as SUCCESS,sum(a.Failed) as FAILED from ("
                        + "select convert(varchar,date,1) as datefield,succeeded,failed from [dbo].[BackupStatus] "
                        + "WHERE server = '" + server + "' ) a "
                        + "where cast(a.datefield as datetime) > getdate()-7"
                        + "group by a.datefield").getResultList();
    }

    public List<String> findAggreByGroup(String server) {
        return getEntityManager().createNativeQuery("SELECT groups, SUM(failed) as failed "
                + "FROM BackupStatus "
                + "WHERE date LIKE '" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "%' AND server = '" + server
                + "' AND failed != 0"
                + " GROUP BY groups")
                .getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

}
