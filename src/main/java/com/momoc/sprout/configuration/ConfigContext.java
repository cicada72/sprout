package com.momoc.sprout.configuration;

import com.momoc.sprout.configuration.basictype.ConfigConstant;
import com.momoc.sprout.configuration.basictype.ConfigFileReaderType;
import com.momoc.sprout.configuration.exception.ConfigFileNotFoundException;
import com.momoc.sprout.configuration.exception.ConfigKeyNotFoundException;
import com.momoc.sprout.configuration.filereader.ConfigFileReaderFactory;
import org.apache.log4j.Logger;

import java.util.Properties;


public class ConfigContext {

    private static Properties CONFIG_PROPS;
    private static final Logger logger = Logger.getLogger(ConfigContext.class);

    static {
        try {
            CONFIG_PROPS = ConfigFileReaderFactory
                        .getReader(ConfigFileReaderType.PROPERTIES)
                        .read(ConfigConstant.CONFIG_FILE_NAME);
        } catch (ConfigFileNotFoundException e) {
            logger.error(e);
        }
    }

    public String getJdbcDriver() throws ConfigKeyNotFoundException {
        String jdbcDriver = CONFIG_PROPS.getProperty(ConfigConstant.JDBC_DRIVER);
        this.checkConfigValue(ConfigConstant.JDBC_DRIVER, jdbcDriver);
        return jdbcDriver;
    }

    public String getJdbcUrl() throws ConfigKeyNotFoundException {
        String jdbcUrl = CONFIG_PROPS.getProperty(ConfigConstant.JDBC_URL);
        this.checkConfigValue(ConfigConstant.JDBC_URL, jdbcUrl);
        return jdbcUrl;
    }

    public String getJdbcUserName() throws ConfigKeyNotFoundException {
        String jdbcUserName = CONFIG_PROPS.getProperty(ConfigConstant.JDBC_USER_NAME);
        this.checkConfigValue(ConfigConstant.JDBC_USER_NAME, jdbcUserName);
        return jdbcUserName;
    }

    public String getJdbcPassword() throws ConfigKeyNotFoundException {
        String jdbcPassword = CONFIG_PROPS.getProperty(ConfigConstant.JDBC_USER_PASSWORD);
        this.checkConfigValue(ConfigConstant.JDBC_USER_PASSWORD, jdbcPassword);
        return jdbcPassword;
    }

    public String getAppPackage() throws ConfigKeyNotFoundException {
        String appPackage = CONFIG_PROPS.getProperty(ConfigConstant.APP_BASE_PACKAGE);
        this.checkConfigValue(ConfigConstant.APP_BASE_PACKAGE, appPackage);
        return appPackage;
    }

    private void checkConfigValue(String configType, String configValue) throws ConfigKeyNotFoundException {
        String configCheckMsg = "Configuration not found: " + configType;
        if(configValue == null || configValue.equals("")){
            throw new ConfigKeyNotFoundException(configCheckMsg);
        }
    }



}
