package nl.xs4all.banaan.tst8.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.naming.Context;

import nl.xs4all.banaan.tst8.fixtures.InitializedNamingContext;
import nl.xs4all.banaan.tst8.fixtures.MailSenderFixture;
import nl.xs4all.banaan.tst8.service.impl.BuildInfoImpl;
import nl.xs4all.banaan.tst8.service.impl.JndiReaderImpl;
import nl.xs4all.banaan.tst8.service.impl.NotificatorImpl;
import nl.xs4all.banaan.tst8.service.impl.PropertyReaderImpl;
import nl.xs4all.banaan.tst8.service.impl.ServicesImpl;
import nl.xs4all.banaan.tst8.web.DemoApplication;

import org.springframework.mail.MailSender;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.Singleton;

/**
 * Instruction for Guice on how to wire the test version of the application.
 * 
 * @author konijn
 * 
 */
public class TestApplicationModule extends AbstractModule {

    private static final String DUMMY_BUILD_PROPERTIES = "/dummy-build.properties";

    @Override
    protected void configure() {
        bind(DemoApplication.class).in(Scopes.SINGLETON);
        bind(Services.class).to(ServicesImpl.class).in(Scopes.SINGLETON);
        bind(JndiReader.class).to(JndiReaderImpl.class).in(Scopes.SINGLETON);

        bind(Notificator.class).to(NotificatorImpl.class);
        bind(PropertyReader.class).to(PropertyReaderImpl.class);
        bind(BuildInfo.class).to(BuildInfoImpl.class);
    }
    
    // TODO: limit this to build properties only
    // TODO: separate test and production
    @Provides @Singleton Properties provideBuildProperties() {
        return getPropertiesFromResource(getBuildPropertyResourceName());
    }
 
    // TODO: production version
    @Provides @Singleton Context provideContext() {
        return new InitializedNamingContext();
    }
    
    // TODO: production version
    @Provides @Singleton MailSender provideMailSender() {
        return new MailSenderFixture();
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
    
    private String getBuildPropertyResourceName() {
        return DUMMY_BUILD_PROPERTIES;
    }
}
