package nl.xs4all.banaan.tst8.service.impl;

import nl.xs4all.banaan.tst8.service.BuildInfo;
import nl.xs4all.banaan.tst8.service.JndiReader;
import nl.xs4all.banaan.tst8.service.Notificator;
import nl.xs4all.banaan.tst8.service.PropertyReader;
import nl.xs4all.banaan.tst8.service.Services;

/**
 * Centralised access to services.
 * @author konijn
 *
 */
public class ServicesImpl implements Services {
    /**
     * services injected via spring
     */
    private JndiReader jndiReader;
    private Notificator notificator;
    private PropertyReader propertyReader;
    private BuildInfo buildInfo;
    
    public void setJndiReader(JndiReader jndiReader) {
        this.jndiReader = jndiReader;
    }
    
    /**
     * @{inheritDoc}
     */
    public JndiReader getJndiReader() {
        return jndiReader;
    }

    public void setNotificator(Notificator notificator) {
        this.notificator = notificator;
    }
    
    /**
     * @{inheritDoc}
     */
    public Notificator getNotificator() {
        return notificator;
    }
    
    public void setPropertyReader(PropertyReader propertyReader) {
        this.propertyReader = propertyReader;
    }
    
    /**
     * @{inheritDoc}
     */
    public PropertyReader getPropertyReader() {
        return propertyReader;
    }
    
    public void setBuildInfo(BuildInfo buildInfo) {
        this.buildInfo = buildInfo;
    }
    
    /**
     * @{inheritDoc}
     */
    public BuildInfo getBuildInfo() {
        return buildInfo;
    }
}
