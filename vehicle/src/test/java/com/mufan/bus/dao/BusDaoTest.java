package com.mufan.bus.dao;

import com.mufan.ap.dao.HostsDao;
import com.mufan.bus.domain.Bus;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aswe on 2016/7/23.
 */
public class BusDaoTest {
    private static ApplicationContext context;

    @BeforeClass
    public static void init(){
        context = new ClassPathXmlApplicationContext(new String[]{"beans.xml"});
    }

    @Test
    public void test01(){
        BusDao busDao = (BusDao) context.getBean("busDao");
        Bus bus = new Bus();
        bus.setLicensePlate("aaaaaaaaa");
        busDao.save(bus);
    }

    @Test
    public void test02(){
        BusDao busDao = (BusDao) context.getBean("busDao");

        List<Bus> list = new ArrayList<Bus>();

        for(int i=2;i<50;i++){
            Bus bus = new Bus();
            bus.setLicensePlate("chepai"+i);
            list.add(bus);
        }
        busDao.save(list);

        Bus bus = new Bus();
        bus.setLicensePlate("aaaaaaaaa");
        busDao.save(bus);
    }
    @Test
    public void test03(){
        BusDao busDao = (BusDao) context.getBean("busDao");
        Bus bus = new Bus();
        bus.setId(1L);
        bus.setLicensePlate("chepai"+1);
        busDao.save(bus);
    }

    @Test
    public void test04(){
        BusDao busDao = (BusDao) context.getBean("busDao");
        busDao.setHostsById(8,51L);
    }

    @Test
    public void test05(){
        BusDao busDao = (BusDao) context.getBean("busDao");
        busDao.cancelHostsById(51l);
    }

    @Test
    public void test06(){
        HostsDao hostsDao = (HostsDao) context.getBean("hostsDao");
        hostsDao.setBusById(51L,8);
    }

    @Test
    public void testfindByLicensePlateIs(){
        BusDao busDao = (BusDao) context.getBean("busDao");
        Bus bus = busDao.findByLicensePlateIs("chepai1");
        System.out.println(bus.getId());
        System.out.println("hostsid:"+bus.getHosts().getId());
    }

}
