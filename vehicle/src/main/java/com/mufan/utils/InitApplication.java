package com.mufan.utils;

import com.mufan.message.service.MessageServer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by aswe on 2016/9/21.
 */
//@Component
public class InitApplication implements InitializingBean {
//    @Resource
    private MessageServer messageServer;
    public void afterPropertiesSet() throws Exception {
        messageServer.starAcceptIOTMessageServer();
    }
}
