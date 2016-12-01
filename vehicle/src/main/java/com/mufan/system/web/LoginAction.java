package com.mufan.system.web;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

/**
 * Created by aswe on 2016/8/7.
 */
@Controller
@ParentPackage("json-default")
public class LoginAction {

    @Action(value = "login",results = {@Result(name = "success",location = "/default.html",type = "redirect"),
            @Result(name = "error",location = "/login.jsp"/*,type = "redirect",params = {"loginname","${loginname}","loginpass","${loginpass}","errormsg","${errormsg}"}*/)})
    public String login() {
        if (loginname.equals("admin")
                && loginpass.equals("admin")) {
            return "success";
        } else {
            errormsg = "用户名或密码错误！";
            return "error";
        }
    }

    public String loginname;
    public String loginpass;
    public String errormsg;

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getLoginpass() {
        return loginpass;
    }

    public void setLoginpass(String loginpass) {
        this.loginpass = loginpass;
    }

    public String getErrormsg() {
        return errormsg;
    }

    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }
}
