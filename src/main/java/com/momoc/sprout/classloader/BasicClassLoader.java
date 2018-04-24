package com.momoc.sprout.classloader;

import com.momoc.sprout.classloader.exception.IllegalPackageNameException;
import com.momoc.sprout.common.ClassFileFinder;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.Set;
import java.util.regex.Pattern;

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
        try{
            clazz = Class.forName(className, isInit, this.getClassLoader());
            logger.info("Load class:" + className + ", IsInitialize:" + isInit);
        } catch (ClassNotFoundException e) {
            logger.error("Load class failed:" + className + ", IsInitialize:" + isInit, e);
        }
        return clazz;
    }

    @Override
    public Set<Class<?>> getClassesByPackage(String packageName) throws IllegalPackageNameException {

        return null;
    }

    private File getPackageFolderInClassPath(String packageName) throws IllegalPackageNameException {
        File packageFolder = null;
        String classPath = ClassFileFinder.getClassFolderPath(BasicClassLoader.class);
        File classPathFolder = new File(classPath);
        String[] packagePath = this.convertPackageToPath(packageName);

        return packageFolder;
    }

    private String[] convertPackageToPath(String packageName) throws IllegalPackageNameException {
        String[] path = null;
        if(packageName != null){
            String pattern = "^([a-zA-Z]+[.][a-zA-Z]+)[.]*.*"; //非字母开头，以点号分割，最少两段的包名
            if(!Pattern.matches(pattern, packageName)){
                throw new IllegalPackageNameException(packageName);
            }
            path = packageName.replaceAll("[.]", "/").split("/");
        }
       return path;
    }
}
