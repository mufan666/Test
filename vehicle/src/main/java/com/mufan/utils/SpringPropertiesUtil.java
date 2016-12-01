package com.mufan.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by aswe on 2016/8/5.
 */
@Component
public class SpringPropertiesUtil implements ApplicationContextAware {
    public static final String KEY = "propertyConfigurer";
    private static ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        SpringPropertiesUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取配置文件中的内容
     *
     * @param keyName
     * @return
     */
    public static String parseStr(String keyName) {
        CustomizedProperty cp = (CustomizedProperty) applicationContext
                .getBean(KEY);
        return cp.getContextProperty(keyName).toString();
    }

    /**
     * 获取配置文件中的内容
     *
     * @param keyName
     * @return
     */
    public static int parseInt(String keyName) {
        CustomizedProperty cp = (CustomizedProperty) applicationContext
                .getBean(KEY);
        return Integer.parseInt(cp.getContextProperty(keyName).toString());
    }

    /**
     * 获取配置文件中的内容
     *
     * @param keyName
     * @return
     */
    public static double parseDouble(String keyName) {
        CustomizedProperty cp = (CustomizedProperty) applicationContext
                .getBean(KEY);
        return Double.parseDouble(cp.getContextProperty(keyName).toString());
    }
}
