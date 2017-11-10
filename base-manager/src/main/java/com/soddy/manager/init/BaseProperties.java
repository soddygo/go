package com.soddy.manager.init;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class BaseProperties extends PropertyPlaceholderConfigurer {
	
	/**
	 * 缓存常量集合
	 */
	private static Map<String, String> propertyMap = new HashMap<String, String>();
	
	/**
	 * 获取缓存中常量值
	 * @param propertyName
	 * @return
	 */
	public static String getProperty(String propertyName){
		
		return propertyMap.get(propertyName);
	}
	
	
	
    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
    	
    	//将常量值放入缓存
    	propertyMap.put(propertyName, propertyValue);
    	
    	return propertyValue;
    }
    
	
}