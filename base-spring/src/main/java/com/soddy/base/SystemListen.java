package com.soddy.base;

import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

/**
 * Created by soddygo on 2016/10/9.
 */
@WebListener
public class SystemListen extends ContextLoaderListener {
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        super.contextDestroyed(event);
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {

        //启动spring TODO


    }
}
