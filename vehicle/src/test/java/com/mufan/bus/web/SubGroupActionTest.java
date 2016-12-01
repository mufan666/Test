package com.mufan.bus.web;

import org.apache.struts2.StrutsSpringTestCase;

import javax.servlet.ServletException;
import java.io.UnsupportedEncodingException;

/**
 * Created by aswe on 2016/7/25.
 */
public class SubGroupActionTest extends StrutsSpringTestCase {
    @Override
    protected String[] getContextLocations() {
        return new String[]{"classpath*:beans.xml"};
    }
    public void testGetById() throws UnsupportedEncodingException, ServletException {
        request.addParameter("jsonPara","{\"id\":3}");
        String res = executeAction("/subgroup/getSubGroup.do");
        System.out.println(res);
    }
}
