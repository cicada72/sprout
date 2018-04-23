package com.momoc.sprout.configuration.filereader;

import com.momoc.sprout.common.ClassFileFinder;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesReader implements ConfigFileReader {

    private static final Logger logger = Logger.getLogger("PropertiesReader");

    @Override
    public Properties read(String propertiesFileName){
        String currentFolderPath = ClassFileFinder.getClassFolderPath(PropertiesReader.class);
        File propertiesFile = ergodicFolderToFindPropertiesFile(propertiesFileName, currentFolderPath);
        FileInputStream fileInputStream = null;
        Properties properties = new Properties();
        try{
            fileInputStream = new FileInputStream(propertiesFile);
            properties.load(fileInputStream);
            logger.info("读取配置文件成功, 文件名:" + propertiesFile.getAbsolutePath());
        }catch(Exception e){
            logger.error("读取配置信息失败", e);
        }finally{
            if(fileInputStream != null){
                try{
                    fileInputStream.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        return properties;
    }

    private File ergodicFolderToFindPropertiesFile(String targetFileName, String currentFolderPath){
        File propertiesFile = null;
        File currentFold = new File(currentFolderPath);
        File[] files = currentFold.listFiles();
        if(files != null){
            for(int index = 0; index < files.length; index++){
                File f = files[index];
                if(f.getName().equals(targetFileName)){
                    propertiesFile = f;
                    break;
                }
                if(index == (files.length - 1)){
                    ergodicFolderToFindPropertiesFile(targetFileName, currentFold.getParent());
                }
            }
        }
        return propertiesFile;
    }
}
