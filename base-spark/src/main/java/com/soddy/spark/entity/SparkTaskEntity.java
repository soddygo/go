package com.soddy.spark.entity;

import org.apache.hadoop.conf.Configuration;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * spark任务对象
 * Created by soddygo on 2016/11/3.
 */
public class SparkTaskEntity {
     JavaSparkContext javaSparkContext;
     String localAddress;
     String hdfsInPutAddress;
     String hdfsOutPutAddress;
     LinkedBlockingQueue<String> files;
     LinkedBlockingQueue<JavaPairRDD<String, Object>> sparkResult = new LinkedBlockingQueue<JavaPairRDD<String, Object>>();
     String jarHome;
     String jarName;
     AtomicInteger crashcount;
     String MasterInHDFS = "hdfs://192.168.80.128:9000/in/";
     String MasterOutHDFS = "hdfs://192.168.80.128:9000/out/";
     Configuration configuration = new Configuration();


    public JavaSparkContext getJavaSparkContext() {
        return javaSparkContext;
    }

    public void setJavaSparkContext(JavaSparkContext javaSparkContext) {
        this.javaSparkContext = javaSparkContext;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }

    public String getHdfsInPutAddress() {
        return hdfsInPutAddress;
    }

    public void setHdfsInPutAddress(String hdfsInPutAddress) {
        this.hdfsInPutAddress = hdfsInPutAddress;
    }

    public String getHdfsOutPutAddress() {
        return hdfsOutPutAddress;
    }

    public void setHdfsOutPutAddress(String hdfsOutPutAddress) {
        this.hdfsOutPutAddress = hdfsOutPutAddress;
    }

    public LinkedBlockingQueue<String> getFiles() {
        return files;
    }

    public void setFiles(LinkedBlockingQueue<String> files) {
        this.files = files;
    }

    public LinkedBlockingQueue<JavaPairRDD<String, Object>> getSparkResult() {
        return sparkResult;
    }

    public void setSparkResult(LinkedBlockingQueue<JavaPairRDD<String, Object>> sparkResult) {
        this.sparkResult = sparkResult;
    }

    public String getJarHome() {
        return jarHome;
    }

    public void setJarHome(String jarHome) {
        this.jarHome = jarHome;
    }

    public String getJarName() {
        return jarName;
    }

    public void setJarName(String jarName) {
        this.jarName = jarName;
    }

    public AtomicInteger getCrashcount() {
        return crashcount;
    }

    public void setCrashcount(AtomicInteger crashcount) {
        this.crashcount = crashcount;
    }

    public String getMasterInHDFS() {
        return MasterInHDFS;
    }

    public void setMasterInHDFS(String masterInHDFS) {
        MasterInHDFS = masterInHDFS;
    }

    public String getMasterOutHDFS() {
        return MasterOutHDFS;
    }

    public void setMasterOutHDFS(String masterOutHDFS) {
        MasterOutHDFS = masterOutHDFS;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
}
