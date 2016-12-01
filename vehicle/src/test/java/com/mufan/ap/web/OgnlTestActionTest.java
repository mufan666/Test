package com.mufan.ap.web;

import org.apache.struts2.StrutsSpringTestCase;
import org.junit.Test;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.Assert.*;

/**
 * Created by aswe on 2016/11/21.
 */
public class OgnlTestActionTest extends StrutsSpringTestCase {
    @Override
    protected String[] getContextLocations() {
        return new String[]{"classpath*:beans.xml"};
    }

    public void testGetTestValue() throws Exception {
        String res = executeAction("/test/getTestValue.do");
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