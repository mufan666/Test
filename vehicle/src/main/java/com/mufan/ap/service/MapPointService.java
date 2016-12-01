package com.mufan.ap.service;

import com.mufan.ap.domain.Hosts;
import com.mufan.ap.domain.MapPoint;

import java.util.Date;
import java.util.List;

/**
 * Created by aswe on 2016/7/31.
 */
public interface MapPointService {
    void insertPoint(MapPoint point);
    void batchInsertPoint(List<MapPoint> points);

    MapPoint getMapPoint(Long id);
    MapPoint getLastPoint(Integer id);
    MapPoint getLastPoint();
    List<MapPoint> getLastPointByHosts();
    List<MapPoint> getTrackByHosts(Hosts hosts, Date startTime,Date endTime);
}
