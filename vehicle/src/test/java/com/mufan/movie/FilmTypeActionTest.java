package com.mufan.movie;

import org.apache.struts2.StrutsSpringTestCase;
import org.junit.Test;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import java.io.UnsupportedEncodingException;

/**
 * Created by aswe on 2016/8/4.
 */
public class FilmTypeActionTest extends StrutsSpringTestCase {
    @Override
    protected String[] getContextLocations() {
        return new String[]{"classpath*:beans.xml"};
    }

    @Test
    public void testGetAll() throws ServletException, UnsupportedEncodingException {
        String res = executeAction("/filmType/getAll.do");
        System.out.println(res);
    }

}
