package org.mybeanframework.core.util;

import org.mybeanframework.core.annotation.MyBean;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 注解@MyBean的工具类，用于处理@MyBean注解相关的问题
 *
 * @author herenpeng
 */
public class MyBeanUtils {

    /**
     * 常量：MyBean.class
     */
    private final static Class<MyBean> MY_BEAN_CLASS = MyBean.class;

    /**
     * 获取所有被@MyBean注解的类，
     * 如果@MyBean有value属性值，就将key修改为@MyBean的value值
     *
     * @param packageScanBeanNameMap 传入一个全限定类名Map
     * @return 返回所有被@MyBean注解标识的类的全限定类名
     * @throws ClassNotFoundException 异常
     */
    private static Map<String, String> filter(Map<String, String> packageScanBeanNameMap) throws ClassNotFoundException {
        Map<String, String> newMap = new HashMap<>(16);
        Set<Map.Entry<String, String>> entries = packageScanBeanNameMap.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            String entryKey = entry.getKey();
            String entryValue = entry.getValue();
            Class<?> beanClass = Class.forName(entryValue);
            if (beanClass.isAnnotationPresent(MY_BEAN_CLASS)) {
                MyBean myBean = beanClass.getAnnotation(MY_BEAN_CLASS);
                String key = myBean.value();
                if (key != null && key.length() > 0) {
                    entryKey = key;
                }
                newMap.put(entryKey, entryValue);
            }
        }
        return newMap;
    }


    /**
     * 注解@MyBean的过滤器
     *
     * @param packageScanName 传入包扫描，经过包扫描，MyBean注解过滤
     * @return 返回Map集合
     * @throws IOException            IO异常
     * @throws ClassNotFoundException 类没有找到异常
     */
    public static Map<String, String> filter(String packageScanName) throws IOException, ClassNotFoundException {
        return filter(PackageScanUtils.getBeanNameMap(packageScanName));
    }

    /**
     * 注解@MyBean的过滤器
     *
     * @param classObject 传入字节码对象，经过包扫描，MyBean注解过滤
     * @return 返回Map集合
     * @throws IOException            IO异常
     * @throws ClassNotFoundException 类没有找到异常
     */
    public static Map<String, String> filter(Class<?> classObject) throws IOException, ClassNotFoundException {
        return filter(PackageScanUtils.getBeanNameMap(classObject));
    }


}