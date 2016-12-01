package com.mufan.movie;

import com.mufan.movie.dao.PublishFilmVersionDao;
import com.mufan.movie.domain.PublishFilmVersion;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * Created by aswe on 2016/8/5.
 */
public class PublishFilmVersionDaoTest {
    private static ApplicationContext context;
    private static PublishFilmVersionDao publishFilmVersionDao;

    @BeforeClass
    public static void init(){
        context = new ClassPathXmlApplicationContext(new String[]{"beans.xml"});
        publishFilmVersionDao = (PublishFilmVersionDao) context.getBean("publishFilmVersionDao");
    }

    @Test
    public void test01(){
        PublishFilmVersion p = new PublishFilmVersion();
        p.setId(1L);
        p.setVersion(System.currentTimeMillis());
        publishFilmVersionDao.save(p);
    }

    @Test
    public void test02(){
        publishFilmVersionDao.insertVersion(System.currentTimeMillis());
    }
}
