package com.baizhi.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextUtils implements ApplicationContextAware {
    //写一个参数用来接收实现aware接口方法通过参数传递的容器
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
    //提供在工厂中获取对象的方法
    public static Object getBean(String beanName){  //RedisTemplate redisTemplate
     return applicationContext.getBean(beanName);
    }
}
