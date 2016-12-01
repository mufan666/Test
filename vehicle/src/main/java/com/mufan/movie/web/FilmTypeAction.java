package com.mufan.movie.web;

import com.mufan.movie.domain.FilmType;
import com.mufan.movie.service.FilmTypeService;
import com.mufan.utils.JsonResult;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by aswe on 2016/8/4.
 */
@Controller
@ParentPackage("json-default")
@Namespace("/filmType")
@Result(type = "json", params = {"includeProperties", "jsonResult.*","excludeProperties","jsonResult.data\\[\\d+\\].film.*","noCache","true","encoding", "UTF-8", "excludeNullProperties", "true"})
public class FilmTypeAction {


    @Action(value = "getAll")
    public String getAll(){
        List<FilmType> filmTypes = filmTypeService.findAll();
        jsonResult.setData(filmTypes);
        jsonResult.toSuccess();
        return "success";
    }

    @Resource
    private FilmTypeService filmTypeService;

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

