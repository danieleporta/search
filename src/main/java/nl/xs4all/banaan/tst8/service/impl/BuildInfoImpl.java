package nl.xs4all.banaan.tst8.service.impl;

import java.util.Properties;

import com.google.inject.Inject;

import nl.xs4all.banaan.tst8.service.BuildInfo;

public class BuildInfoImpl implements  BuildInfo {
    Properties properties;
    
    @Inject
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public String getGroup() {
        return properties.getProperty("group");
    }

    public String getName() {
        return properties.getProperty("name"); 
    }

    public String getUser() {
        return properties.getProperty("user");
    }

    public String getVersion() {
        return properties.getProperty("version");
    }

}
