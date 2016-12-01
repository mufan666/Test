package com.mufan.utils;

import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aswe on 2016/7/28.
 */
public class JsonResult implements Serializable {

    // 状态码
    private int statusCode;
    // 返回信息
    private String message;
    // 错误信息
    private String errormsg;
    // 返回的数据
    private List data = new ArrayList();

    private int total;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrormsg() {
        return errormsg;
    }

    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public JsonResult putData(List list){
        this.setData(list);
        return this;
    }

    public JsonResult toSuccess(){
        this.setTotal(this.getData().size());
        this.setStatusCode(200);
        return this;
    }
    public JsonResult toError(String message){
        this.setStatusCode(404);
        this.setMessage(message);
        return this;
    }

    public String toJson(){
        this.setTotal(this.getData().size());
        JSONObject json = JSONObject.fromObject(this);
        String res = json.toString();
        return res;
    }

    public String toJsonAsStrategies(JsonConfig jsonConfig){
        this.setTotal(this.getData().size());
        JSONObject json = JSONObject.fromObject(this,jsonConfig);
        String res = json.toString();
        return res;
    }

    public JSONObject toJSONObject(){
        this.setTotal(this.getData().size());
        JSONObject json = JSONObject.fromObject(this);
        return json;
    }
    public JSONObject toJSONObjectAsStrategies(JsonConfig jsonConfig){
        this.setTotal(this.getData().size());
        JSONObject json = JSONObject.fromObject(this,jsonConfig);
        return json;
    }

    public String toGson(){
        this.setTotal(this.getData().size());
        Gson gson = new Gson();
        String res = gson.toJson(this);
        return res;
    }

    public String toGsonAsStrategies(ExclusionStrategy strategy){
        return toGsonAsStrategies(strategy,"yyyy-MM-dd HH:mm:ss");
    }
    public String toGsonAsStrategies(ExclusionStrategy strategy,String format){
        this.setTotal(this.getData().size());
        Gson gson = new GsonBuilder().setDateFormat(format).setExclusionStrategies(strategy).create();
        String res = gson.toJson(this);
        return res;
    }


}
