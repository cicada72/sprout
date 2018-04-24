package com.momoc.sprout.classloader;

import com.momoc.sprout.classloader.exception.IllegalPackageNameException;
import com.momoc.sprout.common.ClassFileFinder;
import com.momoc.sprout.common.FileScanner;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.Set;

public class BasicClassLoader extends ClassLoader {

    private static final Logger logger = Logger.getLogger(BasicClassLoader.class);

    private BasicClassLoader(){
        super(Thread.currentThread().getContextClassLoader());
    }

    public static BasicClassLoader getClassLoader(){
        return new BasicClassLoader();
    }


    protected Class<?> loadClass(String className, byte[] bytes, int off, int len){
        Class<?> clazz = null;
        if(bytes != null){
            clazz = defineClass(className, bytes, off, len);
        }
        return clazz;
    }

    public Set<Class<?>> getClassesByPackage(String packageName) throws IllegalPackageNameException {
        File packageFolder = this.getPackageFolderInClassPath(packageName);

        return null;
    }



    private File getPackageFolderInClassPath(String packageName) throws IllegalPackageNameException {
        File packageFolder;
        String classPath = ClassFileFinder.getClassFolderPath(BasicClassLoader.class);
        String packagePath = FileScanner.convertPackageToPath(packageName);
        packageFolder = FileScanner.findTargetFileByPath(classPath, packagePath);
        return packageFolder;
    }

}
