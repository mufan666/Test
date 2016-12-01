package com.mufan.ap.dao;

import com.mufan.ap.domain.Hosts;
import com.mufan.ap.domain.MapPoint;
import com.mufan.utils.EntityFactoryUtils;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * Created by aswe on 2016/9/23.
 */
public class MapPointSweeperImpl implements MapPointSweeper {

    private EntityManager entityManager=EntityFactoryUtils.getInstance();

    public void insertSweep(MapPoint mapPoint) {
        entityManager.getTransaction().begin();
        entityManager.persist(mapPoint);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Hosts isExist(String apMac) {
        Hosts hosts = null;
        try {
            Query query = entityManager.createQuery("select o from Hosts o where o.serialno = :i");
            query.setParameter("i",apMac);
            hosts = (Hosts) query.getSingleResult();
        } catch (NoResultException e) {

        }
        /*if(null == hosts) System.out.println("null");
        else System.out.println("exsit");*/
        return hosts;
    }
}
