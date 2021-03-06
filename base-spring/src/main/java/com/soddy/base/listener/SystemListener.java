package com.soddy.base.listener;

import com.soddy.base.factory.SpringFactory;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletRegistration;
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
        servletContext.setInitParameter("contextConfigLocation", "classpath*:spring/spring-base.xml");//从spring-base.xml文件，去读取其他的配置文件信息
        servletContext.addListener(SessionListener.class);
        final ServletRegistration.Dynamic dynamic = servletContext.addServlet("dispatcher",org.springframework.web.servlet.DispatcherServlet.class);
        dynamic.addMapping("/");
        dynamic.setInitParameter("contextConfigLocation","classpath*:mvc/springMvc.xml");

        //启动spring TODO
        final long begin = System.currentTimeMillis();
        this.initSpring(event);
        final long end = System.currentTimeMillis();
        logger.info("启动耗时：" + (end - begin));
    }

    /**
     * spring初始化
     * @param event
     * @throws Exception
     */
    private void initSpring(ServletContextEvent event) {

        super.contextInitialized(event);

        {
            WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
            SpringFactory.setApplicationContext(springContext);
//            SpringFactory.Spring.setSpringContext(springContext);
//            SpringFactory.Spring.springLog();
        }
    }

}
