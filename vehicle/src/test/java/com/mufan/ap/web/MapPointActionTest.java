package com.mufan.ap.web;

import org.apache.struts2.StrutsSpringTestCase;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import java.io.UnsupportedEncodingException;


/**
 * Created by aswe on 2016/10/18.
 */
public class MapPointActionTest extends StrutsSpringTestCase {
    @Override
    protected String[] getContextLocations() {
        return new String[]{"classpath*:beans.xml"};
    }

    public void testGet() throws UnsupportedEncodingException, ServletException {
        String res = executeAction("/mapPoint/getLastMapPointByHosts.do");
        System.out.println(res);
    }
    public void testTrack() throws UnsupportedEncodingException, ServletException {
        request.addParameter("jsonPara","{\"licencePlate\":\"chepai1\",\"startTime\":\"1476783300000\",\"endTime\":\"1476956100000\"}");
        String res = executeAction("/mapPoint/getTrackByBus.do");
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