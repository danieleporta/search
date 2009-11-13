package nl.xs4all.banaan.tst8.wiring;


import javax.naming.Context;

import nl.xs4all.banaan.tst8.fixtures.InitializedNamingContext;
import nl.xs4all.banaan.tst8.fixtures.MailSenderFixture;

import org.junit.Ignore;
import org.springframework.mail.MailSender;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.Singleton;

/**
 * Instruction for Guice on how to wire the test part of the application.
 * 
 * @author konijn
 * 
 */
@Ignore
public class TestApplicationModule extends AbstractModule {
    private static final String DUMMY_BUILD_PROPERTIES = "/dummy-build.properties";

    @Override
    protected void configure() {
        bind(MailSenderFixture.class).in(Scopes.SINGLETON);
        bind(MailSender.class).to(MailSenderFixture.class).in(Scopes.SINGLETON);
        bind(Context.class).to(InitializedNamingContext.class).in(Scopes.SINGLETON);
    }

    @Provides @Singleton @BuildInfoResourceName public String provideResourceName() {
        return DUMMY_BUILD_PROPERTIES;        
    }
}
