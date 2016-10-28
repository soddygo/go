package com.soddy.config.service;

import java.io.IOException;

/**
 * C3p0 数据库连接池初始化
 * Created by soddy on 2016/10/28.
 */
public interface JdbcPoolInitService {

    /**
     * 初始化数据库连接池
     */
    void  initJdbcPool() throws IOException;
}
