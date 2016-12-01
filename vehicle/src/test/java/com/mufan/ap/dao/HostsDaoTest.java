package com.mufan.ap.dao;

import com.mufan.ap.domain.Hosts;
import com.mufan.ap.domain.Script;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by aswe on 2016/7/20.
 */
public class HostsDaoTest {
    @Test
    public void test01(){
        /*EntityManagerFactory factory = Persistence.createEntityManagerFactory("vehicle");
        EntityManager manager = factory.createEntityManager();*/
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"beans.xml"});
        HostsDao dao = (HostsDao) context.getBean("hostsDao");
        System.out.println(dao);
        Hosts h = dao.findOne(1);
        /*h.setReboot(true);
        dao.save(h);*/
        System.out.println(h.getId()+"\t"+h.getSerialno()+"\t"+h.isReboot()+"\t"+h.getCurrentSoftware()+"\t"+h.getLastContact());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
        String dateString = format.format(h.getLastContact());
        System.out.println(dateString);
        System.out.println(h.getLastContact().getTime());

    }

    @Test
    public void test001(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
    }

    @Test
    public void test02(){
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"beans.xml"});
        HostsDao dao = (HostsDao) context.getBean("hostsDao");
        dao.setReboot(false,1);
//        System.out.println(h.getId()+"\t"+h.getSerialno()+"\t"+h.isReboot());

    }

    @Test
    public void test03(){
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"beans.xml"});
        HostsDao dao = (HostsDao) context.getBean("hostsDao");
        long current = System.currentTimeMillis();
        System.out.println(current);
        Timestamp timestamp = new Timestamp(current);
        dao.setLastContact(timestamp,1);
//        System.out.println(h.getId()+"\t"+h.getSerialno()+"\t"+h.isReboot());
        //1469007581523
        //1469007582000
    }
    @Test
    public void test04(){
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"beans.xml"});
        HostsDao dao = (HostsDao) context.getBean("hostsDao");
        Date date = new Date();
        System.out.println(date);
        dao.setLastContactAsDate(date,1);
//        System.out.println(h.getId()+"\t"+h.getSerialno()+"\t"+h.isReboot());

    }

    @Test
    public void test05(){
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"beans.xml"});
        HostsDao dao = (HostsDao) context.getBean("hostsDao");

//        System.out.println(h.getId()+"\t"+h.getSerialno()+"\t"+h.isReboot());

    }
    @Test
    public void test06(){
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"beans.xml"});
        ScriptDao dao = (ScriptDao) context.getBean("scriptDao");

        Script s = dao.findOne("conreq2");
        if(null != s)
        System.out.println(s.getScript());
        else {
            System.out.println("空");
            s = new Script();
            s.setName("aaaa");
            System.out.println(s.getName());
        }
//        System.out.println(h.getId()+"\t"+h.getSerialno()+"\t"+h.isReboot());

    }

    @Test
    public void test07() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"beans.xml"});
        HostsDao dao = (HostsDao) context.getBean("hostsDao");
        Hosts h = dao.findBySerialnoIs("4CE676D9A17C");
        System.out.println(h.getId());
    }

}
