package com.soddy.config.util;

import com.soddy.utils.common.IoUtil;

import java.io.*;
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
 * properties 参数解析工具
 * Created by soddy on 2016/10/28.
 */
public class ProPertiesUtil {
    private static String defaultBasePath = "";//默认的properties配置文件的路径

    private static List<Properties> propertiesList = new ArrayList<>();

    /**
     * 增加一个Properties对象
     *
     * @param inputStream properties输入流对象
     */
    public static void addProperties(InputStream inputStream) throws IOException {
        Properties properties = new Properties();
        properties.load(inputStream);
    }

    /**
     * 默认初始化
     */
    public static void initDefaultProperties() throws IOException {
        final String basePath = "com/trssearch/proxool/";
        Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(basePath);
        List<String> proxools = new ArrayList<String>();
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            String protocol = url.getProtocol();
            if ("file".equals(protocol)) {
                String path = URLDecoder.decode(url.getFile(), "utf-8");
                File file = new File(path);
                if (file.exists()) {
                    for (File f : file.listFiles(new FileFilter() {
                        public boolean accept(File pathname) {
                            return pathname.getName().endsWith(".properties");
                        }
                    })) {
                        if (proxools.contains(f.getName())) continue;
                        proxools.add(f.getName());
                        Properties properties = new Properties();
                        InputStream inputStream = new FileInputStream(f);
                        properties.load(inputStream);
                        IoUtil.closeQuietly(inputStream);
                        propertiesList.add(properties);
                    }
                }
            } else if ("jar".equals(protocol)) {
                JarURLConnection jrc = (JarURLConnection) url.openConnection();
                JarFile jarFile = jrc.getJarFile();
                Enumeration<JarEntry> entries = jarFile.entries();
                while (entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    String name = entry.getName();
                    if (!name.startsWith("com") || name.endsWith("/")) continue;
                    name = name.substring(name.lastIndexOf("/") + 1, name.length());
                    if (!proxools.contains(name) && name.endsWith(".properties")) {
                        proxools.add(name);
                        InputStream inputStream = jarFile.getInputStream(entry);
                        Properties properties = new Properties();
                        properties.load(inputStream);
                        IoUtil.closeQuietly(inputStream);
                        propertiesList.add(properties);
                    }
                }
            }
        }


    }

    /**
     * 读取配置文件的属性值
     *
     * @param key
     * @return
     */
    public static String getString(String key) {

        return null;
    }

//    InputStream inputStream2 = Object.class.getResourceAsStream("/resource.properties");
//    InputStream inputStream3 = Object.class.getClassLoader().getResourceAsStream("ipConfig.properties");


}