package com.soddy.base.factory;

import org.springframework.context.ApplicationContext;

/**
 * Created by soddygo on 2016/11/1.
 */
public class SpringFactory {

    /** applicationContext */
    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        SpringFactory.applicationContext = applicationContext;
    }
}
