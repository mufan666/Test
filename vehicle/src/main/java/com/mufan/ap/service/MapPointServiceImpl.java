package com.mufan.ap.service;

import com.mufan.ap.dao.MapPointDao;
import com.mufan.ap.domain.Hosts;
import com.mufan.ap.domain.MapPoint;
import com.mufan.bus.dao.BusDao;
import org.apache.commons.lang.time.FastDateFormat;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by aswe on 2016/7/31.
 */
@Service("mapPointService")
public class MapPointServiceImpl implements MapPointService {

    @Resource
    private MapPointDao mapPointDao;

    public void insertPoint(MapPoint point) {
        mapPointDao.save(point);
    }

    public void batchInsertPoint(List<MapPoint> points) {
        mapPointDao.save(points);
    }

    public MapPoint getMapPoint(Long id) {
        return mapPointDao.findOne(id);
    }

    public MapPoint getLastPoint(Integer id) {
        return mapPointDao.findTopByHostsIdIsOrderByApTimeDesc(id);
    }
    public MapPoint getLastPoint() {
        return mapPointDao.findTopByOrderByApTimeDesc();
    }

    public List<MapPoint> getLastPointByHosts() {
//        FastDateFormat fdf = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
//        return mapPointDao.findlsatPoint(new Date(System.currentTimeMillis()-1000*60*10));
        return mapPointDao.getLastPointsGroupByBus();
    }

//    @Override
    public List<MapPoint> getTrackByHosts(Hosts hosts, Date startTime, Date endTime) {
//        return mapPointDao.findByHostsIsAndReporttimeBetween(hosts,startTime,endTime);
        return mapPointDao.findByHostsIsAndFixedStatusNotAndReporttimeBetween(hosts,"0",startTime,endTime);
//        return null;
    }


}
