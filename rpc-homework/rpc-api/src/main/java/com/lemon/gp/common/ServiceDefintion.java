package com.lemon.gp.common;

import com.lemon.gp.anntation.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by gujj on 2018/6/11.
 */
public class ServiceDefintion {
    private Properties properties = new Properties();
    private Map<String, Object> servicesMap = new ConcurrentHashMap<>();

    public Map<String, Object> getServicesMap() {
        return servicesMap;
    }


    public ServiceDefintion() {
        loadConfig();
        doScan(properties.getProperty("register.services.package"));
    }

    /**
     * 加载properties文件
     */
    private void loadConfig() {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("services.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 加载并注册需要发布的服务
     */
    private void doScan(String packageName) {
        if (!"".equals(packageName)) {
            URL url = this.getClass().getClassLoader().getResource(packageName.replaceAll("\\.", "/"));
            File classDir = new File(url.getFile());
            File[] files = classDir.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    doScan(packageName + "." + file.getName());
                } else {
                    try {
                        String name = packageName + "." + file.getName();
                        //去除文件最后的 .class 获取名称
                        name = name.substring(0, name.length() - 6);
                        Class clazz = Class.forName(name);
                        //如果有在类上添加@Service,则把其Class放入
                        if (clazz.isAnnotationPresent(Service.class)) {
                            Service service = (Service) clazz.getAnnotation(Service.class);
                            String serviceName = clazz.getName();
                            try {
                                Object obj = clazz.newInstance();
                                Class<?>[] interfaces = clazz.getInterfaces();
                                if (interfaces != null) {
                                    for (Class<?> inter : interfaces) {
                                        serviceName = inter.getName();
                                        if (servicesMap.get(serviceName) != null) {
                                            throw new Exception("服务注册失败，存在相同服务名的服务！");
                                        } else if (serviceName.indexOf(properties.getProperty("register.services" +
                                                ".package")) >= 0) {
                                            servicesMap.put(serviceName, obj);
                                        }
                                    }
                                }
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }


}