package com.mufan.ap.web;

import com.mufan.ap.domain.MapPoint;
import com.mufan.ap.service.MapPointService;
import com.mufan.bus.dao.BusDao;
import com.mufan.bus.domain.Bus;
import com.mufan.utils.JsonResult;
import net.sf.json.JSONObject;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.xpath.SourceTree;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.naming.Name;
import java.util.Date;
import java.util.List;

/**
 * Created by aswe on 2016/7/31.
 */
@Controller
@ParentPackage("json-default")
@Result(type = "json",params = {"root","jsonResult"})
@Namespace("/mapPoint")
public class MapPointAction {

    @Resource
    private MapPointService mapPointService;

    @Resource
    private BusDao busDao;

    @Action(value="getMapPointByID",results = {@Result(type = "json",params = {"includeProperties","mapPoint.*","noCache","true","encoding", "UTF-8", "excludeNullProperties", "true"})})
    public String getMapPointByID(){
        System.out.println("mappointaction"+mapPoint.getId());
        mapPoint = mapPointService.getMapPoint(mapPoint.getId());
        System.out.println("123");
        return "success";
    }

    @Action(value="getMapPointByHostsID",results = {@Result(type = "json",params = {"includeProperties","mapPoint.*"})})
    public String getMapPointByHostsID(){
//        mapPoint = mapPointService.getLastPoint(1);
        System.out.println("aa");
        mapPoint = mapPointService.getLastPoint();
        return "success";
    }

//    @Action(value="getLastMapPointByHosts",results = {@Result(type = "json",params = {"includeProperties","mapPoints.*"})})
    @Action(value="getLastMapPointByHosts",results = {@Result(type = "json",params = {"includeProperties", "jsonResult.*","excludeProperties",".*.apTimeData,.*.subGroup.buses","noCache","true","encoding", "UTF-8", "excludeNullProperties", "true"})})
//    @Action(value="getLastMapPointByHosts",results = {@Result(type = "json",params = {"includeProperties", "jsonResult.*","noCache","true","encoding", "UTF-8", "excludeNullProperties", "true"})})
    public String getLastMapPointByHosts(){
        List<MapPoint> mapPoints = mapPointService.getLastPointByHosts();
//        mapPoints.forEach(mapPoint1 -> System.out.println(mapPoint1.getId()));
        jsonResult.putData(mapPoints).toSuccess();

        return "success";
    }

    @Action(value="getTrackByBus",results = {@Result(type = "json",params = {"includeProperties", "jsonResult.*","excludeProperties","jsonResult.data\\[\\d+\\].hosts","noCache","true","encoding", "UTF-8", "excludeNullProperties", "true"})})
//    @Action(value="getTrackByBus",results = {@Result(type = "json",params = {"includeProperties", "jsonResult.*","noCache","true","encoding", "UTF-8", "excludeNullProperties", "true"})})
    public String getTrackByBus(){
        System.out.println("aaa");
        JSONObject jsonObject = JSONObject.fromObject(jsonPara);
        String licencePlate = jsonObject.getString("licencePlate");
//        String startTime = jsonObject.getString("startTime");
//        String endTime = jsonObject.getString("endTime");
        Long startTime = jsonObject.getLong("startTime");
        Long endTime = jsonObject.getLong("endTime");
        /*System.out.println(new Date(startTime));
        System.out.println(licencePlate+"\t"+startTime+"\t"+endTime);*/
        Bus bus = busDao.findByLicensePlateIs(licencePlate);
        List<MapPoint> mapPoints = mapPointService.getTrackByHosts(bus.getHosts(),new Date(startTime),new Date(endTime));
//        mapPoints.forEach((final MapPoint mapPoint1) -> System.out.println(mapPoint1.getId()));
        jsonResult.putData(mapPoints).toSuccess();
        return "success";
    }




    public String jsonPara;
    public JsonResult jsonResult = new JsonResult();
    public MapPoint mapPoint = new MapPoint();

    public String getJsonPara() {
        return jsonPara;
    }

    public void setJsonPara(String jsonPara) {
        this.jsonPara = jsonPara;
    }

    public JsonResult getJsonResult() {
        return jsonResult;
    }

    public void setJsonResult(JsonResult jsonResult) {
        this.jsonResult = jsonResult;
    }

    public MapPoint getMapPoint() {
        return mapPoint;
    }

    public void setMapPoint(MapPoint mapPoint) {
        this.mapPoint = mapPoint;
    }
}
