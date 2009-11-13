package nl.xs4all.banaan.tst8.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.naming.Context;

import org.springframework.mail.MailSender;

import nl.xs4all.banaan.tst8.service.impl.BuildInfoImpl;
import nl.xs4all.banaan.tst8.service.impl.JndiReaderImpl;
import nl.xs4all.banaan.tst8.service.impl.NotificatorImpl;
import nl.xs4all.banaan.tst8.service.impl.PropertyReaderImpl;
import nl.xs4all.banaan.tst8.service.impl.ServicesImpl;
import nl.xs4all.banaan.tst8.web.DemoApplication;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.Singleton;

/**
 * Tell Guice how to wire the application; the parts shared between test and production.
 * @author konijn
 *
 */
public abstract class BaseApplicationModule extends AbstractModule {

    public abstract String getBuildPropertyResourceName();
    public abstract MailSender provideMailSender();
    public abstract Context provideContext();


    @Override
    protected void configure() {
        bind(DemoApplication.class).in(Scopes.SINGLETON);
        bind(Services.class).to(ServicesImpl.class).in(Scopes.SINGLETON);
        bind(JndiReader.class).to(JndiReaderImpl.class).in(Scopes.SINGLETON);
        bind(Notificator.class).to(NotificatorImpl.class).in(Scopes.SINGLETON);
        bind(PropertyReader.class).to(PropertyReaderImpl.class).in(Scopes.SINGLETON);
        bind(BuildInfo.class).to(BuildInfoImpl.class);
    }

    @Provides @Singleton
    Properties provideBuildProperties() {
        return getPropertiesFromResource(getBuildPropertyResourceName());
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
