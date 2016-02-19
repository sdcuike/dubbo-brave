package com.doctor.dubbo.extend.brave.helper;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.alibaba.dubbo.container.spring.SpringContainer;

/**
 * @author sdcuike
 *
 * @time 2016年2月2日 下午4:36:38
 */
public class ApplicationContextProvider implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
        if (context == null) {
            context = SpringContainer.getContext();
        }
    }

    public static ApplicationContext getApplicationContext() {
        return context;
    }

}
