package com.mufan.ap.web;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mufan.ap.dao.HostsDao;
import com.mufan.ap.domain.Hosts;
import com.mufan.ap.service.HostsService;
import com.mufan.utils.JsonResult;
import com.mufan.utils.jsonStrategy.gson.HostsRelationStrategy;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aswe on 2016/7/21.
 */
@ParentPackage("json-default")
@Controller
//@Result(type = "json", params = { "root", "result" })
@Result(type = "json", params = {"root", "jsonResult", "noCache", "true", "enableGZIP", "true", "excludeProperties", "hosts,subGroup.buses", "encoding", "UTF-8", "excludeNullProperties", "true"})
@Namespace("/hosts")
public class HostsAction {
    @Resource
    private HostsService hostsService;

    @Resource
    private HostsDao hostsDao;

    @Action(value = "preConfig")
    public String preConfig() {

        Gson gson = new Gson();
        Hosts hosts = gson.fromJson(jsonPara, Hosts.class);
        hostsService.genConifgScript(hosts);
        return "success";
    }

    @Action(value = "getHostsById", results = {@Result(type = "json", params = {"root", "result"})})
    public String getHostsById() {
        Gson gson = new Gson();
        Hosts hosts = gson.fromJson(jsonPara, Hosts.class);
        Hosts h = hostsService.findApByID(hosts.getId());
//        h.setBus(null);
        jsonResult.getData().add(h);
        result = jsonResult.toSuccess().toGson();
        return "success";
    }

    @Action(value = "getHostsByIdExclude", results = {@Result(type = "json", params = {"includeProperties", "jsonResult.*","excludeProperties",".*.bus,.*.subGroup.buses","noCache","true","encoding", "UTF-8", "excludeNullProperties", "true"})})
    public String getHostsByIdExclude() {
        Gson gson = new Gson();
        Hosts hosts = gson.fromJson(jsonPara, Hosts.class);
        Hosts h = hostsService.findApByID(hosts.getId());
//        h.setBus(null);
        jsonResult.getData().add(h);
        return "success";
    }

    @Action(value = "getAllBinding", results = {@Result(type = "json", params = {"root", "result", "noCache", "true", "enableGZIP", "true", "excludeProperties", "hosts.bus.hosts,hosts.bus.subGroup.buses", "encoding", "UTF-8", "excludeNullProperties", "true"})})
    public String getAllBinding() {
        List<Hosts> hostses = hostsDao.findAll();
        for (Hosts h : hostses) {
            System.out.println("hostsAction"+h.getId());
            h.initDevicestatus();
            jsonResult.getData().add(h);
        }
//        jsonResult.toSuccess();
        result = jsonResult.toGsonAsStrategies(new HostsRelationStrategy());
        return "success";
    }

    @Action(value = "requestReboot")
    public String requestReboot() {
        Gson gson = new Gson();
        Hosts hosts = gson.fromJson(jsonPara, Hosts.class);
        hostsDao.setReboot(true, hosts.getId());
        jsonResult.toSuccess();
        return "success";
    }

    @Action(value = "requestFactoryReset")
    public String requestFactoryReset() {
        Gson gson = new Gson();
        Hosts hosts = gson.fromJson(jsonPara, Hosts.class);
        hostsDao.setFactoryReset(true, hosts.getId());
        jsonResult.toSuccess();
        return "success";
    }
    @Action(value = "batchInput")
    public String batchInput() {
        List<Hosts> hostses = new ArrayList<Hosts>();
        JSONObject jsonObject = JSONObject.fromObject(jsonPara);
        JSONArray jsonArray = jsonObject.getJSONArray("hostses");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            Hosts h = (Hosts) JSONObject.toBean(obj, Hosts.class);
            hostses.add(h);
        }
        /*for (Hosts h : hostses) {
            System.out.println(h.getLineNum()+"\t"+h.getSerialno());
            if (null != h.getBus()) {
                System.out.println(h.getBus().getLicensePlate());
                if(null!= h.getBus().getSubGroup()) System.out.println(h.getBus().getSubGroup().getName());
            }
        }*/
        hostsService.batchInsert(hostses);
        result = jsonResult.toSuccess().toString();
        return "success";
    }


    @Action(value = "getAllOffline")
    public String getAllOffline() {

        List<Hosts> hostses = hostsService.findByOffline(1000 * 60L);
        result = jsonResult.putData(hostses).toGsonAsStrategies(new HostsRelationStrategy());
        return "success";
    }

    private String jsonPara;
    private String result;

    private JsonResult jsonResult = new JsonResult();

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

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
}
