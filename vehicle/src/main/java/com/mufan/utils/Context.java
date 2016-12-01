package com.mufan.utils;

import com.mufan.message.service.MessageServer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by aswe on 2016/9/23.
 */
public class Context implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        new MessageServer().starAcceptIOTMessageServer();
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
