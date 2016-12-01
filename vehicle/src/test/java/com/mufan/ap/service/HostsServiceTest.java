package com.mufan.ap.service;

import com.mufan.ap.dao.HostsDao;
import com.mufan.ap.domain.Hosts;
import com.mufan.bus.dao.BusDao;
import com.mufan.bus.domain.Bus;
import com.mufan.bus.domain.SubGroup;
import com.mufan.movie.dao.FilmDao;
import com.mufan.movie.dao.FilmTypeDao;
import com.mufan.movie.service.FilmService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aswe on 2016/8/6.
 */
public class HostsServiceTest {
    private static ApplicationContext context;
    private static HostsService hostsService;
    private static HostsDao hostsDao;
    private static BusDao busDao;

    @BeforeClass
    public static void init(){
        context = new ClassPathXmlApplicationContext(new String[]{"beans.xml"});
        hostsService = (HostsService) context.getBean("hostsService");
        hostsDao = (HostsDao) context.getBean("hostsDao");
        busDao = (BusDao) context.getBean("busDao");
    }
    @Test
    public void testbatchInsert(){
        Hosts hosts1 = new Hosts();
        hosts1.setHardware("V1.0.0");
        hosts1.setSerialno("abc");
        SubGroup subGroup = new SubGroup();
        subGroup.setName("group one");
        Bus bus = new Bus();
        bus.setLicensePlate("vaaaa");
        bus.setSubGroup(subGroup);
        hosts1.setBus(bus);

        List<Hosts> hostsList = new ArrayList<Hosts>();
        hostsList.add(hosts1);
        List<Hosts> list = hostsService.batchInsert(hostsList);
        if(list.isEmpty()){
            System.out.println("empty");
        }else {
            for(Hosts h:list){
                System.out.println(h.getCheckMsg());
            }
        }
    }

}
