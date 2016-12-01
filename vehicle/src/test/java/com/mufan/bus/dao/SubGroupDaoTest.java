package com.mufan.bus.dao;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mufan.bus.domain.Bus;
import com.mufan.bus.domain.SubGroup;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by aswe on 2016/7/25.
 */
public class SubGroupDaoTest {

    private static ApplicationContext context;

    @BeforeClass
    public static void init(){
        context = new ClassPathXmlApplicationContext(new String[]{"beans.xml"});
    }

    @Test
    public void test01(){
        SubGroupDao subGroupDao = (SubGroupDao) context.getBean("subGroupDao");
        BusDao busDao= (BusDao) context.getBean("busDao");
        SubGroup s = new SubGroup();
        s.setName("group two");
        s.setDescInfo("第二组");
        Bus bus = busDao.findOne(1L);
        SubGroup subGroup = subGroupDao.save(s);
        bus.setSubGroup(s);
        busDao.save(bus);
    }

    @Test
    public void test02(){
        /*SubGroupDao subGroupDao = (SubGroupDao) context.getBean("subGroupDao");
        SubGroup subGroup = subGroupDao.findOne(3);
        Set<Bus> buses = subGroup.getBuses();
        for(Bus b:buses){
            System.out.println(b.getHosts().getSerialno()+b.getLicensePlate());
        }

        System.out.println(subGroup.getName());*/
    }


}
