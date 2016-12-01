package com.mufan.movie;

import org.apache.struts2.StrutsSpringTestCase;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import java.io.UnsupportedEncodingException;

/**
 * Created by aswe on 2016/7/31.
 */
@Transactional
/*@ContextConfiguration(locations = {"classpath*:beans.xml"})
@RunWith(SpringJUnit4ClassRunner.class)*/
public class FilmActionTest extends StrutsSpringTestCase {
    @Override
    protected String[] getContextLocations() {
        return new String[]{"classpath*:beans.xml"};
    }


    public void testPublishFilm() throws UnsupportedEncodingException, ServletException {
        request.addParameter("jsonPara","{\"id\":1}");
        String res = executeAction("/film/publishFilm.do");
        System.out.println(res);
    }
    public void testUnPublishFilm() throws UnsupportedEncodingException, ServletException {
        request.addParameter("jsonPara","{\"id\":1}");
        String res = executeAction("/film/unPublishFilm.do");
        System.out.println(res);
    }

    public void testGetAllFilms() throws UnsupportedEncodingException, ServletException {
        String res = executeAction("/film/getAllFilms.do");
        System.out.println(res);
    }

    public void testGetFilm() throws UnsupportedEncodingException, ServletException {
        request.addParameter("jsonPara","{\"id\":5}");
        String res = executeAction("/film/getFilm.do");
        System.out.println(res);
    }

    public void testUploadFilmInfo() throws UnsupportedEncodingException, ServletException {
        request.addParameter("jsonPara","{click\":,\"cname\":\"abaa\",\"country\":\"\",\"date\":\"\",\"descInfo\":\"\",\"ename\":\"\",\"filmFileName\":\"Wildlife.wmv\"\n" +
                ",\"filmSuffix\":\"wmv\",\"filmTypes\":[],\"filmcode\":\"d8c2eafd90c266e19ab9dcacc479f8af\",\"filmpath\":\"1\\\\Wildlife\n" +
                ".wmv\",\"filmsize\":123123123,\"id\":1,\"imageFileName\":\"aaa.jpg\",\"imageSuffix\":\"jpg\",\"imagecode\":\"076e3caed758a1c18c91a0e9cae3368f\"\n" +
                ",\"imagepath\":\"Chrysanthemum.jpg\",\"imagesize\":12345566,\"publish\":true}");
        String msg = executeAction("/film/uploadFilmInfo.do");
        System.out.println(msg);
    }

    public void testgetPublishFilm() throws UnsupportedEncodingException, ServletException {
        String res = executeAction("/film/getPublishConfig.do");
        System.out.println(res);
    }

    public void testgetProperties() throws UnsupportedEncodingException, ServletException {
        request.addParameter("jsonPara","{\"key\":\"jdbc.driverClassName\"}");
        String res = executeAction("/film/getProperties.do");
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
