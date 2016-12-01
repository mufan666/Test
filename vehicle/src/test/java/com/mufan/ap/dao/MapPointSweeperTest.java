package com.mufan.ap.dao;

import com.mufan.ap.domain.Hosts;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by aswe on 2016/10/17.
 */
public class MapPointSweeperTest {
    private static ApplicationContext context;

    @BeforeClass
    public static void init(){
        context = new ClassPathXmlApplicationContext(new String[]{"beans.xml"});
    }

    @Test
    public void testIsExist(){
        MapPointSweeper mapPointDao = new MapPointSweeperImpl();
        Hosts h = mapPointDao.isExist("aaaa1");
        if(null == h ) {
            System.out.println("null");
        }else {
            System.out.println(h.getId());
        }
    }
}
