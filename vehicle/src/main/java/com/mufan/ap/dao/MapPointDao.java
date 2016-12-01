package com.mufan.ap.dao;

import com.mufan.ap.domain.Hosts;
import com.mufan.ap.domain.MapPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by aswe on 2016/7/31.
 */
public interface MapPointDao extends JpaRepository<MapPoint,Long>,MapPointRepository {

    public MapPoint findTopByHostsIdIsOrderByApTimeDesc(Integer hostsId);
    public MapPoint findTopByOrderByApTimeDesc();

    @Query("select a from MapPoint a where not exists (select 1 from MapPoint b where b.hosts=a.hosts and b.apTime>a.apTime) and a.hosts is not NULL and a.reporttime>:date")
    public List<MapPoint> findlsatPoint(@Param("date")Date date);

    List<MapPoint> findByHostsIsAndReporttimeBetween(Hosts hosts, Date startTime, Date endTime);

    List<MapPoint> findByHostsIsAndFixedStatusNotAndReporttimeBetween(Hosts hosts,String fixedStatus, Date startTime, Date endTime);
}
