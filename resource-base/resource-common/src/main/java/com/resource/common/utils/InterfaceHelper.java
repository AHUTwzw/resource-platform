package com.resource.common.utils;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class InterfaceHelper {

    /**
     * 找到所有实现指定接口的类
     *
     * @param interfaceClass 接口的 Class 对象
     * @param packageName    要扫描的包名
     * @return 实现接口的类列表
     */
    public static List<Class<?>> findImplementations(Class<?> interfaceClass, String packageName) {
        List<Class<?>> implementations = new ArrayList<>();
        try {
            // 获取包路径
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            String path = packageName.replace('.', '/');
            URL resource = classLoader.getResource(path);

            if (resource == null) {
                throw new IllegalArgumentException("Package not found: " + packageName);
            }

            // 扫描包下的所有类
            File directory = new File(resource.getFile());
            if (directory.exists()) {
                scanDirectory(directory, packageName, interfaceClass, implementations);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return implementations;
    }

    /**
     * 递归扫描目录下的所有类
     */
    private static void scanDirectory(File directory, String packageName, Class<?> interfaceClass, List<Class<?>> implementations) {
        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                // 递归扫描子目录
                scanDirectory(file, packageName + "." + file.getName(), interfaceClass, implementations);
            } else if (file.getName().endsWith(".class")) {
                // 加载类并检查是否实现接口
                String className = packageName + '.' + file.getName().replace(".class", "");
                try {
                    Class<?> clazz = Class.forName(className);
                    if (interfaceClass.isAssignableFrom(clazz) && !clazz.isInterface()) {
                        implementations.add(clazz);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
