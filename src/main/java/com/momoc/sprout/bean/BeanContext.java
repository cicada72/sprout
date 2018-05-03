package com.momoc.sprout.bean;

import com.momoc.sprout.classloader.SproutClassLoader;
import com.momoc.sprout.classloader.exception.IllegalPackageNameException;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BeanContext {

    private static Map<Class<?>, Object> BEAN_MAP = new HashMap<>();
    private static final Logger logger = Logger.getLogger(BeanContext.class);

    public BeanContext(SproutClassLoader classLoader, String packageName) throws IllegalPackageNameException {
        BeanContainer beanContainer = BeanContainer.build();
        beanContainer.open(classLoader, packageName);
        Set<Class<?>> beanSet = beanContainer.getAllBeanSet();
        for(Class<?> clazz : beanSet){
            Object obj = ReflectionUtil.newInstance(clazz);
            BEAN_MAP.put(clazz, obj);
        }
    }

    public Map<Class<?>, Object> getBeanMap(){
        return BEAN_MAP;
    }

    @SuppressWarnings("unchecked")
    public <T> T getBean(Class<T> clazz) throws BeanNotFoundException {
        if(! BEAN_MAP.containsKey(clazz)){
            throw new BeanNotFoundException("Cannot found bean by class:" + clazz);
        }
        return (T) BEAN_MAP.get(clazz);
    }


}
