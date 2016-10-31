package com.soddy.config.service.impl;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.soddy.config.service.JdbcPoolInitService;
import com.soddy.config.util.ProPertiesUtil;

import java.beans.PropertyVetoException;
import java.io.IOException;

/**
 * Created by soddy on 2016/10/28.
 */
public class C3p0InitServiceImpl implements JdbcPoolInitService {
    //    private static final MDBManagerDBManager instance=new MDBManager();

    private static ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource(true);

    @Override
    public void initJdbcProperties() throws IOException {
        ProPertiesUtil.initDefaultProperties();

    }

    @Override
    public void initJdbcPool() throws IOException {

        comboPooledDataSource.setDataSourceName("mydatasource");
        comboPooledDataSource.setJdbcUrl(ProPertiesUtil.getPropertyValue("jdbc.url"));
        try {
            comboPooledDataSource.setDriverClass(ProPertiesUtil.getPropertyValue("jdbc.driver"));
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        comboPooledDataSource.setUser(ProPertiesUtil.getPropertyValue("jdbc.username"));
        comboPooledDataSource.setPassword(ProPertiesUtil.getPropertyValue("jdbc.password"));
        comboPooledDataSource.setMaxPoolSize(Integer.valueOf(ProPertiesUtil.getPropertyValue("connection_pools.max_pool_size")));
        comboPooledDataSource.setMinPoolSize(Integer.valueOf(ProPertiesUtil.getPropertyValue("connection_pools.min_pool_size")));
        comboPooledDataSource.setAcquireIncrement(1);
        comboPooledDataSource.setInitialPoolSize(Integer.valueOf(ProPertiesUtil.getPropertyValue("connection_pools.initial_pool_size")));
        comboPooledDataSource.setMaxIdleTime(Integer.valueOf(ProPertiesUtil.getPropertyValue("connection_pools.max_idle_time")));

    }

    @Override
    public <T> T getDataSource() {
        return (T) comboPooledDataSource;
    }
}
