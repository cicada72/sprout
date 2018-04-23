package com.momoc.sprout.configuration;

import com.momoc.sprout.configuration.basictype.ConfigConstant;
import com.momoc.sprout.configuration.basictype.ConfigFileReaderType;
import com.momoc.sprout.configuration.exception.ConfigNotFoundException;
import com.momoc.sprout.configuration.filereader.ConfigFileReaderFactory;

import java.util.Properties;

public class ConfigContext {

    private static final Properties CONFIG_PROPS = ConfigFileReaderFactory
            .getReader(ConfigFileReaderType.PROPERTIES)
            .read(ConfigConstant.CONFIG_FILE_NAME);

    public String getJdbcDriver() throws ConfigNotFoundException {
        String jdbcDriver = CONFIG_PROPS.getProperty(ConfigConstant.JDBC_DRIVER);
        this.checkConfigValue(ConfigConstant.JDBC_DRIVER, jdbcDriver);
        return jdbcDriver;
    }

    public String getJdbcUrl() throws ConfigNotFoundException {
        String jdbcUrl = CONFIG_PROPS.getProperty(ConfigConstant.JDBC_URL);
        this.checkConfigValue(ConfigConstant.JDBC_URL, jdbcUrl);
        return jdbcUrl;
    }

    public String getJdbcUserName() throws ConfigNotFoundException{
        String jdbcUserName = CONFIG_PROPS.getProperty(ConfigConstant.JDBC_USER_NAME);
        this.checkConfigValue(ConfigConstant.JDBC_USER_NAME, jdbcUserName);
        return jdbcUserName;
    }

    public String getJdbcPassword() throws ConfigNotFoundException{
        String jdbcPassword = CONFIG_PROPS.getProperty(ConfigConstant.JDBC_USER_PASSWORD);
        this.checkConfigValue(ConfigConstant.JDBC_USER_PASSWORD, jdbcPassword);
        return jdbcPassword;
    }

    public String getAppPackage() throws ConfigNotFoundException{
        String appPackage = CONFIG_PROPS.getProperty(ConfigConstant.APP_BASE_PACKAGE);
        this.checkConfigValue(ConfigConstant.APP_BASE_PACKAGE, appPackage);
        return appPackage;
    }

    private void checkConfigValue(String configType, String configValue) throws ConfigNotFoundException {
        String configCheckMsg = "Configuration not found: " + configType;
        if(configValue == null || configValue.equals("")){
            throw new ConfigNotFoundException(configCheckMsg);
        }
    }



}
