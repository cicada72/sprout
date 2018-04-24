package com.momoc.sprout.configuration.filereader;

import com.momoc.sprout.configuration.exception.ConfigFileNotFoundException;

import java.util.Properties;

public interface ConfigFileReader {

    Properties read(String propertiesFileName) throws ConfigFileNotFoundException;
}
