/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author sawad
 */
public class NewClass {

    public static void main(String[] args) {
        //2019-02-21 09:00:37.9200000
//        System.out.println((DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(LocalDateTime.now())).getClass().getName());

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StoragePortalPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
//        List<BackupStatus> bkpList = em.createNamedQuery("BackupStatus.findAll").getResultList();
//        System.out.println(bkpList);
        em.close();
        
    }
}
