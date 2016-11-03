package com.soddy.spark.entity;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by soddygo on 2016/11/3.
 */
public class SparkTaskEntity {
    final JavaSparkContext javaSparkContext;
    final String localAddress;
    final String hdfsInPutAddress;
    final String hdfsOutPutAddress;
    final LinkedBlockingQueue<String> files;
    final LinkedBlockingQueue<JavaPairRDD<String, CrashResult>> sparkResult = new LinkedBlockingQueue<JavaPairRDD<String, CrashResult>>();
    final String jarHome;
    final String jarName;
    final AtomicInteger crashcount;
    final String MasterInHDFS = "hdfs://192.168.80.128:9000/in/";
    final String MasterOutHDFS = "hdfs://192.168.80.128:9000/out/";
    final Configuration configuration = new Configuration();
}
