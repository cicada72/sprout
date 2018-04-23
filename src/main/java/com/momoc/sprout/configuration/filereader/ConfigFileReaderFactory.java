package com.momoc.sprout.configuration.filereader;

import com.momoc.sprout.configuration.basictype.ConfigFileReaderType;
import org.apache.log4j.Logger;

public class ConfigFileReaderFactory {

    private static final Logger logger = Logger.getLogger(ConfigFileReaderFactory.class);

    public static ConfigFileReader getReader(ConfigFileReaderType type) {
        ConfigFileReader propertiesReader = null;
        String eMsg = "Cannot support the config file type:";
        if(type.equals(ConfigFileReaderType.PROPERTIES)){
            propertiesReader = new PropertiesReader();
        }
        else {
            logger.error(eMsg + type);
        }
        return propertiesReader;
    }
}
