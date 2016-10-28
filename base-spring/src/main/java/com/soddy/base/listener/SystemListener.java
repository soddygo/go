package com.soddy.base.listener;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by soddygo on 2016/10/9.
 */
@WebListener
public class SystemListener extends ContextLoaderListener {

    private static final Logger logger = LoggerFactory.getLogger(SystemListener.class);


    @Override
    public void contextDestroyed(ServletContextEvent event) {
        super.contextDestroyed(event);
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext servletContext = event.getServletContext();
        servletContext.setInitParameter("contextConfigLocation", "classpath*:com/soddy/spring/*.xml");


        //启动spring TODO
        final long begin = System.currentTimeMillis();

        final long end = System.currentTimeMillis();
        logger.info("启动耗时：" + (end - begin));
    }

    private void initSpring(ServletContextEvent event) throws Exception{

        super.contextInitialized(event);
        {
            WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
//            SpringFactory.Spring.setSpringContext(springContext);
//            SpringFactory.Spring.springLog();
        }
    }

}
