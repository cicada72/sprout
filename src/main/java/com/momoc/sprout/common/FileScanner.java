package com.momoc.sprout.common;

import com.momoc.sprout.classloader.exception.IllegalPackageNameException;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class FileScanner {

    private static final Logger logger = Logger.getLogger(FileScanner.class);

    public static File findTargetFileByPath(String basePath, String targetPath){
        File targetFile = null;
        String[] targetPathArray = FileScanner.pretreatmentPath(targetPath).split("/");
        for(int index = 0; index < targetPathArray.length; index++){
            if(index != 0){
                basePath = targetFile.getAbsolutePath();
            }
            targetFile = FileScanner.findTargetFileByName(basePath, targetPathArray[index]);
        }
        return targetFile;
    }

    public static File findTargetFileByName(String basePath, String targetFileName){
        File parent = new File(basePath);
        File target = null;
        if(parent.exists() && parent.getName().equals(targetFileName)){
            return parent;
        }
        if(parent.exists() && parent.isDirectory()){
            File [] subFileList = parent.listFiles();
            if(subFileList != null){
                for(int index = 0; index < subFileList.length; index++){
                    File currentFile = subFileList[index];
                    if(currentFile.getName().equals(targetFileName)){
                        target = currentFile;
                        break;
                    }
                    if(index == (subFileList.length -1)){
                        for(File f : subFileList){
                            target = findTargetFileByName(f.getAbsolutePath(), targetFileName);
                            if(target != null){
                                break;
                            }
                        }
                    }
                }
            }
        }
        return target;
    }

    public static String convertPackageToPath(String packageName) throws IllegalPackageNameException {
        String path = null;
        if(packageName != null){
            String pattern = "^([a-zA-Z]+[.][a-zA-Z]+)[.]*.*"; //非字母开头，以点号分割，最少两段的包名
            if(!Pattern.matches(pattern, packageName)){
                throw new IllegalPackageNameException(packageName);
            }
            path = packageName.replaceAll("[.]", "/");
        }
        return path;
    }

    public static byte[] getBytes(InputStream inputStream){
        byte[] bytes = null;
        if(inputStream != null){
            try{
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                int bufferSize = 4096;
                byte[] buffer = new byte[bufferSize];
                int bytesNumRead;
                while ((bytesNumRead = inputStream.read(buffer)) != -1) {
                    byteArrayOutputStream.write(buffer, 0, bytesNumRead);
                }
                bytes = byteArrayOutputStream.toByteArray();
            } catch (IOException e) {
                logger.error(e);
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error(e);
                }
            }
        }
        return bytes;
    }

    public static byte[] getBytes(File file){
        byte[] bytes = null;
        if(file.exists()){
            InputStream input;
            try{
                input = new FileInputStream(file);
                bytes = getBytes(input);
            } catch (IOException e) {
                logger.error(e);
            }
        }
        return bytes;
    }

    public static String pretreatmentPath(String originPath){
        String path = "";
        if(originPath != null){
            path = originPath;
            if(originPath.contains("\\")){
                path = originPath.replaceAll("\\\\", "/");
            }
            if(path.startsWith("/")){
                path = path.substring(1, originPath.length());
            }
            if(path.endsWith("/")){
                path = path.substring(0, path.length() - 1);
            }
        }
        return path;
    }

}
