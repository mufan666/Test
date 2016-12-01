package com.mufan.ap.dao;

import com.mufan.ap.domain.MapPoint;

import java.util.List;

/**
 * Created by aswe on 2016/10/26.
 */
public interface MapPointRepository {
    List<MapPoint> getLastPointsGroupByBus();
}
