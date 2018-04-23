package com.momoc.sprout.classloader;

import org.apache.log4j.Logger;

import java.util.Set;

public class BasicClassLoader implements SproutClassLoader {

    private static final Logger logger = Logger.getLogger(BasicClassLoader.class);

    @Override
    public ClassLoader getClassLoader() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        logger.info("Get class loader from the current thread:" + classLoader.getName());
        return classLoader;
    }

    @Override
    public Class<?> loadClass(String className, boolean isInit) {
        Class<?> clazz = null;

        return null;
    }

    @Override
    public Set<Class<?>> getClassesByPackage(String packageName) {
        return null;
    }
}
