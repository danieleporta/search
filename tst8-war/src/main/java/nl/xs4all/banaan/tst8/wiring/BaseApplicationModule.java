package nl.xs4all.banaan.tst8.wiring;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.wicket.protocol.http.WebApplication;

import nl.xs4all.banaan.tst8.service.BuildInfo;
import nl.xs4all.banaan.tst8.service.JndiReader;
import nl.xs4all.banaan.tst8.service.Notificator;
import nl.xs4all.banaan.tst8.service.ParamReader;
import nl.xs4all.banaan.tst8.service.PropertyReader;
import nl.xs4all.banaan.tst8.service.impl.BuildInfoImpl;
import nl.xs4all.banaan.tst8.service.impl.JndiReaderImpl;
import nl.xs4all.banaan.tst8.service.impl.NotificatorImpl;
import nl.xs4all.banaan.tst8.service.impl.ParamReaderImpl;
import nl.xs4all.banaan.tst8.service.impl.PropertyReaderImpl;
import nl.xs4all.banaan.tst8.web.DemoApplication;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

/**
 * Tell Guice how to wire the application; the parts shared between test and production.
 * @author konijn
 *
 */
public class BaseApplicationModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(DemoApplication.class).in(Singleton.class);
        bind(WebApplication.class).to(DemoApplication.class);
        bind(JndiReader.class).to(JndiReaderImpl.class).in(Singleton.class);
        bind(Notificator.class).to(NotificatorImpl.class).in(Singleton.class);
        bind(ParamReader.class).to(ParamReaderImpl.class).in(Singleton.class);
        bind(PropertyReader.class).to(PropertyReaderImpl.class).in(Singleton.class);
        bind(BuildInfo.class).to(BuildInfoImpl.class).in(Singleton.class);
        
        install(new LogModule(JndiReaderImpl.class));
    }
    
    @Provides @Singleton
    Properties provideBuildProperties(@BuildInfoResourceName String resourceName) {
        return getPropertiesFromResource(resourceName);
    }

    /** helper to read properties from named resource */
    private Properties getPropertiesFromResource(String resourceName) {
        InputStream stream = this.getClass().getResourceAsStream(resourceName);
        Properties properties = new Properties();
        try {
            properties.load(stream);
            return properties;
        } catch (IOException e) {
            throw new RuntimeException("missing resource" + resourceName, e);
        }
    }
}
