package com.mufan.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Created by aswe on 2016/9/23.
 */
public class EntityFactoryUtils {
//    private EntityManager entityManager=null;
    private EntityFactoryUtils() {
    }

    private static class SingletonEntity{
        private static ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"beans.xml"});
        private static  EntityManagerFactory INSTANCE = (EntityManagerFactory) context.getBean("entityFactory");
    }

    public static final EntityManager getInstance(){
        return SingletonEntity.INSTANCE.createEntityManager();
    }

}
