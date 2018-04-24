package com.momoc.sprout.configuration.filereader;

import com.momoc.sprout.common.ClassFileFinder;
import com.momoc.sprout.common.FileScanner;
import com.momoc.sprout.configuration.exception.ConfigFileNotFoundException;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesReader implements ConfigFileReader {

    private static final Logger logger = Logger.getLogger("PropertiesReader");

    @Override
    public Properties read(String propertiesFileName) throws ConfigFileNotFoundException {
        String currentFolderPath = ClassFileFinder.getClassFolderPath(PropertiesReader.class);
        File propertiesFile = FileScanner.findTargetFileByName(currentFolderPath, propertiesFileName);
        FileInputStream fileInputStream = null;
        Properties properties = new Properties();
        if(propertiesFile != null){
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
        }
        else {
            throw new ConfigFileNotFoundException("Cannot find the config file in class path:" + propertiesFileName);
        }
        return properties;
    }


}
