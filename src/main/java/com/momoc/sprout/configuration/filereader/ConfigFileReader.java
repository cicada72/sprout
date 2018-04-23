package com.momoc.sprout.configuration.filereader;

import java.util.Properties;

public interface ConfigFileReader {

    Properties read(String propertiesFileName);
}
