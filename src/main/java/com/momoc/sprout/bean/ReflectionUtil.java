package com.momoc.sprout.bean;

import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionUtil {

    private static final Logger logger = Logger.getLogger("Class.Reflection");

    public static Object newInstance(Class<?> clazz){
        Object instance;
        try{
            instance = clazz.getDeclaredConstructor().newInstance();
        } catch (IllegalAccessException | InvocationTargetException
                | NoSuchMethodException | InstantiationException e) {
            logger.error(e);
            throw new RuntimeException();
        }
        return instance;
    }

    public static Object invokeMethod(Object obj, Method method, Object...args){
        Object result;
        try{
            method.setAccessible(true);
            result = method.invoke(obj, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            logger.error(e);
            throw new RuntimeException();
        }
        return result;
    }

    public static void setFiled(Object obj, Field field, Object value){
        field.setAccessible(true);
        try {
            field.set(obj, value);
        } catch (IllegalAccessException e) {
            logger.error(e);
            throw new RuntimeException();
        }
    }

}
