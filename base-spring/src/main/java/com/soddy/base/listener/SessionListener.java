package com.soddy.base.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by soddy on 2016/10/28.
 */
public class SessionListener implements HttpSessionListener, HttpSessionActivationListener, HttpSessionAttributeListener {
    private static final Logger logger = LoggerFactory.getLogger(SessionListener.class);

    private static final List<String> sessionAttributeName = new ArrayList<String>();
    static {

//        for (Field f : SystemContext.HttpSession.class.getDeclaredFields()) {
//            try {
//                sessionAttributeName.add((String) f.get(HttpSession.class));
//            } catch (IllegalArgumentException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        }

    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {

        if (!sessionAttributeName.contains(event.getName()) && !event.getName().startsWith("javamelody.")) {

            event.getSession().removeAttribute(event.getName());

            logger.error("session 中不允许存取{},已从 session 中移除。session仅允许存取 SystemContext.HttpSession 中常量", event.getName());

        }

    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {

    }


}
