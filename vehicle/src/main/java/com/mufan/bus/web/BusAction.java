package com.mufan.bus.web;

import com.google.gson.Gson;
import com.mufan.bus.domain.Bus;
import com.mufan.bus.domain.SubGroup;
import com.mufan.bus.service.BusService;
import com.mufan.utils.JsonResult;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by aswe on 2016/8/7.
 */
@Controller
@ParentPackage("json-default")
@Namespace("/bus")
@Result(type = "json", params = { "includeProperties", "jsonResult.*","excludeProperties","jsonResult.data\\[\\d+\\].bus\\[\\d+\\].hosts.*","noCache","true","enableGZIP","true", "encoding","UTF-8","excludeNullProperties","true"})
public class BusAction {
    @Resource
    private BusService busService;
    @Action(value = "changeSubGroup")
    public String changeSubGroup(){
        Gson gson = new Gson();
        Bus bus = gson.fromJson(jsonPara,Bus.class);
        busService.changeSubgroup(bus);
        jsonResult.toSuccess();
        return "success";
    }
    @Action(value = "updateBus")
    public String updateBus(){
        Gson gson = new Gson();
        Bus bus = gson.fromJson(jsonPara,Bus.class);
        busService.updateBus(bus);
        jsonResult.toSuccess();
        return "success";
    }
    @Action(value = "getBusById")
    public String getBusById(){
        Gson gson = new Gson();
        Bus bus = gson.fromJson(jsonPara,Bus.class);
        Bus b = busService.findById(bus.getId());
        jsonResult.getData().add(b);
        jsonResult.toSuccess();
        return "success";
    }

    public JsonResult jsonResult = new JsonResult();
    public String jsonPara;

    public JsonResult getJsonResult() {
        return jsonResult;
    }

    public void setJsonResult(JsonResult jsonResult) {
        this.jsonResult = jsonResult;
    }

    public String getJsonPara() {
        return jsonPara;
    }

    public void setJsonPara(String jsonPara) {
        this.jsonPara = jsonPara;
    }
}
