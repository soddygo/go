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
    public  void initJdbcPool() throws IOException {
        ProPertiesUtil.initDefaultProperties();
            comboPooledDataSource.setDataSourceName("mydatasource");
            comboPooledDataSource.setJdbcUrl("");
            try {
                comboPooledDataSource.setDriverClass("");
            } catch (PropertyVetoException e) {
                e.printStackTrace();
            }
            comboPooledDataSource.setUser("");
            comboPooledDataSource.setPassword("");
            comboPooledDataSource.setMaxPoolSize(100);
            comboPooledDataSource.setMinPoolSize(50);
            comboPooledDataSource.setAcquireIncrement(10);
            comboPooledDataSource.setInitialPoolSize(200);
            comboPooledDataSource.setMaxIdleTime(3000);

    }
}
