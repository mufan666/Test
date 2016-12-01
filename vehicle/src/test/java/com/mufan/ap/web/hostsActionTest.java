package com.mufan.ap.web;

import com.google.gson.Gson;
import com.mufan.ap.domain.Hosts;
import org.apache.struts2.StrutsSpringTestCase;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import java.io.UnsupportedEncodingException;

/**
 * Created by aswe on 2016/7/21.
 */
public class hostsActionTest extends StrutsSpringTestCase {
    @Override
    protected String[] getContextLocations() {
        return new String[]{"classpath*:beans.xml"};
    }

    public void testPreConfig() throws UnsupportedEncodingException, ServletException {
        Gson gson = new Gson();
        Hosts hosts = new Hosts();
        hosts.setId(1);
        hosts.setSsid("xx_abcdssid");
        hosts.setChannel(9);
        hosts.setBandWidth("20");
        hosts.setBasicEncryptionmodes("wep");
        hosts.setWepKey("key1");
        hosts.setWpapass("123123");
        request.addParameter("jsonPara",gson.toJson(hosts));
        String res = executeAction("/hosts/preConfig.do");
        System.out.println(res);
    }


    public void testGet() throws UnsupportedEncodingException, ServletException {
        String res = executeAction("/Person_get.do");
        System.out.println(res);
    }
    public void testGetById() throws UnsupportedEncodingException, ServletException {
        request.addParameter("jsonPara","{\"id\":7}");
        String res = executeAction("/Person_getbyid.do");
        System.out.println(res);
    }
    public void testGetOffline() throws UnsupportedEncodingException, ServletException {
        String res = executeAction("/hosts/getAllOffline.do");
        System.out.println(res);
    }
    public void testGetHostsById() throws UnsupportedEncodingException, ServletException {
        request.addParameter("jsonPara","{\"id\":15}");
        String res = executeAction("/hosts/getHostsByIdExclude.do");
        System.out.println(res);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        EntityManagerFactory factory = (EntityManagerFactory) this.applicationContext.getBean("entityFactory");
        EntityManager ex = factory.createEntityManager();
        EntityManagerHolder emHolder1 = new EntityManagerHolder(ex);
        TransactionSynchronizationManager.bindResource(factory, emHolder1);
    }
}
