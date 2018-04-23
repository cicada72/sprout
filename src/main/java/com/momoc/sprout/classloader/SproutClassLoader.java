package com.momoc.sprout.classloader;

import java.util.Set;

public interface SproutClassLoader {

    ClassLoader getClassLoader();

    Class<?> loadClass(String className, boolean isInit);

    Set<Class<?>> getClassesByPackage(String packageName);

}
