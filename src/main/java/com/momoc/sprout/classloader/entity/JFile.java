package com.momoc.sprout.classloader.entity;

import com.momoc.sprout.common.FileScanner;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JFile {

    private static final Logger logger = Logger.getLogger(JFile.class);
    private JFileType jFileType;
    private File file;
    private String className;

    public JFileType getJFileType() {
        return jFileType;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
        String fileName = file.getName();
        if(fileName.toLowerCase().trim().endsWith(".class")){
            this.jFileType = JFileType.CLASS;
        }
        if(fileName.toLowerCase().trim().endsWith(".jar")){
            this.jFileType = JFileType.JAR;
        }
    }

    public Map<String, byte[]> getJarBytes(){
        Map<String, byte[]> map = null;
        if(jFileType.equals(JFileType.JAR)) {
            map = new HashMap<>();
            JarFile jarFile = this.convertFileToJar();
            Enumeration<JarEntry> jarEntryEnumeration = jarFile.entries();
            while (jarEntryEnumeration.hasMoreElements()){
                JarEntry jarEntry = jarEntryEnumeration.nextElement();
                String name = jarEntry.getName();
                if(name.endsWith(".class")){
                    name = FileScanner.pretreatmentPath(name).replace(".class", "")
                            .replaceAll("/", ".");
                    try {
                        byte[] bytes = FileScanner.getBytes(jarFile.getInputStream(jarEntry));
                        map.put(name, bytes);
                    } catch (IOException e) {
                        logger.error(e);
                    }
                }

            }
        }
        return map;
    }

    public byte[] getClassBytes(){
        byte[] bytes = null;
        if(jFileType.equals(JFileType.CLASS)){
            bytes = FileScanner.getBytes(this.file);
        }
        return bytes;
    }

    private JarFile convertFileToJar(){
        JarFile jarFile = null;
        if(this.jFileType.equals(JFileType.JAR)){
            try {
                jarFile = new JarFile(this.file);
            } catch (IOException e) {
                logger.error(e);
            }
        }
        return jarFile;
    }
}
