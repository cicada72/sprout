package com.momoc.sprout.bean;

import com.momoc.sprout.annotation.Controller;
import com.momoc.sprout.annotation.Service;
import java.util.HashSet;
import java.util.Set;

public class BeanContainer {

    private Set<Class<?>> CONTROLLER_BEAN_SET;
    private Set<Class<?>> SERVICE_BEAN_SET;
    private Set<Class<?>> ALL_CLASS_SET;
    private Set<Class<?>> ALL_BEAN_SET;

    private static BeanContainer beanContainer = new BeanContainer();

    private BeanContainer(){
        CONTROLLER_BEAN_SET = new HashSet<>();
        SERVICE_BEAN_SET = new HashSet<>();
        ALL_CLASS_SET = new HashSet<>();
        ALL_BEAN_SET = new HashSet<>();
    }

    public static BeanContainer build(){
        return beanContainer;
    }

    public void close(){
        CONTROLLER_BEAN_SET.clear();
        SERVICE_BEAN_SET.clear();
        ALL_CLASS_SET.clear();
        ALL_BEAN_SET.clear();
    }

    public Set<Class<?>> getAllClassSet(){
        return ALL_CLASS_SET;
    }

    public Set<Class<?>> getAllBeanSet(){
        return ALL_BEAN_SET;
    }

    public Set<Class<?>> getControllerBeanSet(){
        return CONTROLLER_BEAN_SET;
    }

    public Set<Class<?>> getServiceBeanSet(){
        return SERVICE_BEAN_SET;
    }

    public void setAllClassSet(Set<Class<?>> classSet){
        ALL_CLASS_SET.addAll(classSet);
    }

    public void classifyBean(){
        for(Class<?> c :ALL_CLASS_SET){
            if (c.isAnnotationPresent(Controller.class)){
                CONTROLLER_BEAN_SET.add(c);
            }
            if(c.isAnnotationPresent(Service.class)){
                SERVICE_BEAN_SET.add(c);
            }
        }
        ALL_BEAN_SET.addAll(CONTROLLER_BEAN_SET);
        ALL_BEAN_SET.addAll(SERVICE_BEAN_SET);
    }

    public void addControllerBean(Class<?> clazz){
        CONTROLLER_BEAN_SET.add(clazz);
    }

    public void addServiceBean(Class<?> clazz){
        SERVICE_BEAN_SET.add(clazz);
    }

    public void addAllClassSet(Class<?> clazz){
        ALL_CLASS_SET.add(clazz);
    }

    public void addAllBeanSet(Class<?> clazz){
        ALL_BEAN_SET.add(clazz);
    }

}