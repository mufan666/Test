package com.mufan.ap.web;

import com.google.gson.Gson;
import com.mufan.ap.domain.Hosts;
import com.mufan.ap.service.HostsService;
import com.mufan.utils.JsonResult;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by aswe on 2016/11/21.
 */
@Controller
@ParentPackage("json-default")
@Result(type = "json",params = {"root","jsonResult"})
@Namespace("/test")
public class OgnlTestAction {
    @Resource
    private HostsService hostsService;

//    @Action(value="getTestValue",results = {@Result(type = "json",params = {"includeProperties","jsonResult.*","noCache","true","encoding", "UTF-8", "excludeNullProperties", "true"})})
    @Action(value="getTestValue",results = {@Result(type = "json",params = {"includeProperties","jsonResult.*","noCache","true","encoding", "${coding}", "excludeNullProperties", "true"})})
//    @Action(value="getTestValue",results = {@Result(type = "json",params = {"${jsonParams}"})})
    public String getTestValue() {
//        Hosts h = hostsService.findApByID(1);
        jsonResult.getData().add("aa");
        System.out.println(jsonResult.getData().get(0));
        ActionContext.getContext().put("coding","UTF-8");
        return "success";
    }

    public JsonResult jsonResult = new JsonResult();

    public JsonResult getJsonResult() {
        return jsonResult;
    }

    public void setJsonResult(JsonResult jsonResult) {
        this.jsonResult = jsonResult;
    }
    public String getJsonParams(){
        String params = "\"includeProperties\",\"jsonResult.*\",\"noCache\",\"true\",\"encoding\", \"UTF-8\", \"excludeNullProperties\", \"true\"";
        System.out.println(params);
        return params;
    }
    private String downloadFileName;
    public String getDEncoding(){
        return "UTF-8";
    }
    public String getDownloadFileName() {
        return "UTF-8";
    }
}
