package com.mufan.bus.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mufan.bus.domain.SubGroup;
import com.mufan.bus.service.SubGroupService;
import com.mufan.movie.domain.Film;
import com.mufan.utils.JsonResult;
import com.mufan.utils.jsonStrategy.gson.SubGroupRelationStrategy;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by aswe on 2016/7/25.
 */

@ParentPackage("json-default")
@Controller
@Result(type = "json", params = { "includeProperties", "jsonResult.*","excludeProperties","jsonResult.data\\[\\d+\\].bus\\[\\d+\\].hosts.*","noCache","true","enableGZIP","true", "encoding","UTF-8","excludeNullProperties","true"})
@Namespace("/subgroup")
public class SubGroupAction {

    @Action(value = "getSubGroup")
    public String getSubGroup(){
        Gson gson = new Gson();
        SubGroup sb = gson.fromJson(jsonPara,SubGroup.class);
        SubGroup subGroup = subGroupService.findSubGroup(sb);
//        JSONObject jsonObject = JSONObject.fromObject(subGroup);
//        result = gson.toJson(subGroup);
//        result = jsonObject.toString();
        jsonResult.getData().add(subGroup);
        jsonResult.toSuccess();

//        result = jsonResult.toGsonAsStrategies(new SubGroupRelationStrategy());
        return "success";
    }
    @Action(value = "getAllGroup")
    public String getAllGroup(){
        List<SubGroup> subGroupList = subGroupService.findAllGroup();
        jsonResult.setData(subGroupList);
        jsonResult.toSuccess();
        return "success";
    }

    @Resource
    private SubGroupService subGroupService;

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
