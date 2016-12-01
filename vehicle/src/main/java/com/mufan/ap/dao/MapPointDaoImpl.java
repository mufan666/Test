package com.mufan.ap.dao;

import com.mufan.ap.domain.MapPoint;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by aswe on 2016/10/26.
 */
public class MapPointDaoImpl implements MapPointRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<MapPoint> getLastPointsGroupByBus() {
        String sql = "SELECT b from MapPoint b where b.id in (select max(a.id) from MapPoint a where a.hosts is not null and a.fixedStatus <> '0' group by a.hosts)";
        System.out.println(sql);
        return entityManager.createQuery(sql).getResultList();
    }
}
