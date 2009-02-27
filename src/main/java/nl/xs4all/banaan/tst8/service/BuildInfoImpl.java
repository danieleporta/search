package nl.xs4all.banaan.tst8.service;

import java.util.Properties;

public class BuildInfoImpl implements  BuildInfo {
    Properties properties;
    
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
