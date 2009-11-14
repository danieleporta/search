package nl.xs4all.banaan.tst8.service.impl;

import com.google.inject.Inject;

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
    private final JndiReader jndiReader;
    private final Notificator notificator;
    private final PropertyReader propertyReader;
    private final BuildInfo buildInfo;
 
    @Inject
    public ServicesImpl(
            JndiReader jndiReader,
            Notificator notificator,
            PropertyReader propertyReader,
            BuildInfo buildInfo) {
        this.jndiReader = jndiReader;
        this.notificator = notificator;
        this.propertyReader = propertyReader;
        this.buildInfo = buildInfo;
    }
    
    public JndiReader getJndiReader() {
        return jndiReader;
    }

    public Notificator getNotificator() {
        return notificator;
    }
    
    public PropertyReader getPropertyReader() {
        return propertyReader;
    }
     
    public BuildInfo getBuildInfo() {
        return buildInfo;
    }
}
