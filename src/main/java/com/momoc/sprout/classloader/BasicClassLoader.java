package com.momoc.sprout.classloader;

import com.momoc.sprout.classloader.entity.JFile;
import com.momoc.sprout.classloader.entity.JFileType;
import com.momoc.sprout.classloader.exception.IllegalPackageNameException;
import com.momoc.sprout.common.ClassFileFinder;
import com.momoc.sprout.common.FileScanner;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.*;

public class BasicClassLoader extends ClassLoader {

    private static final Logger logger = Logger.getLogger(BasicClassLoader.class);

    private BasicClassLoader(){
        super(Thread.currentThread().getContextClassLoader());
    }

    public static BasicClassLoader getClassLoader(){
        return new BasicClassLoader();
    }


    private Class<?> loadClass(String className, byte[] bytes, int off, int len){
        Class<?> clazz = null;
        if(bytes != null){
            if(!this.isClassLoaded(className)){
                clazz = defineClass(className, bytes, off, len);
            }
            else {
                logger.info("Class has already benn loaded:" + className);
            }
        }
        return clazz;
    }

    public Set<Class<?>> getClassesByPackage(String packageName) throws IllegalPackageNameException {
        Set<Class<?>> classes = new HashSet<>();
        File packageFolder = this.getPackageFolderInClassPath(packageName);
        JFileReader jFileReader = new JFileReader();
        jFileReader.read(packageFolder);
        List<JFile> jFileList = jFileReader.getJFiles();
        for(JFile jFile : jFileList){
            if(jFile.getJFileType().equals(JFileType.CLASS)){
                String className = this.getWholeClassName(packageName, jFile.getFile());
                byte[] bytes = jFile.getClassBytes();
                Class clzz = this.loadClass(className, bytes, 0, bytes.length);
                logger.info("Load class:" + className);
                classes.add(clzz);
            }
            if(jFile.getJFileType().equals(JFileType.JAR)){
                Map<String, byte[]> map = jFile.getJarBytes();
                Set<String> keySet = map.keySet();
                for (String className : keySet) {
                    byte[] bytes = map.get(className);
                    Class clzz = this.loadClass(className, bytes, 0, bytes.length);
                    logger.info("Load class:" + className);
                    classes.add(clzz);
                }
            }
        }
        return classes;
    }


    private String getWholeClassName(String packageName, File classFile){
        String absolutePath = classFile.getAbsolutePath().replace(".class", "");
        absolutePath = FileScanner.pretreatmentPath(absolutePath).replaceAll("/", ".");
        int off = absolutePath.indexOf(packageName);
        absolutePath = absolutePath.substring(off, absolutePath.length());
        return absolutePath;
    }

    private File getPackageFolderInClassPath(String packageName) throws IllegalPackageNameException {
        File packageFolder;
        String classPath = ClassFileFinder.getClassFolderPath(BasicClassLoader.class);
        String packagePath = FileScanner.convertPackageToPath(packageName);
        packageFolder = FileScanner.findTargetFileByPath(classPath, packagePath);
        return packageFolder;
    }

    private boolean isClassLoaded(String className){
        boolean isClassLoaded = false;
        if(this.findLoadedClass(className) != null){
            isClassLoaded = true;
        }
        return isClassLoaded;
    }

}
