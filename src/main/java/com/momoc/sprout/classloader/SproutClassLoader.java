package com.momoc.sprout.classloader;

import com.momoc.sprout.classloader.exception.IllegalPackageNameException;

import java.util.Set;

public interface SproutClassLoader {

    ClassLoader getClassLoader();

    Class<?> loadClass(String className, boolean isInit);

    Set<Class<?>> getClassesByPackage(String packageName) throws IllegalPackageNameException;

}
