package nl.xs4all.banaan.tst8.wiring;


import javax.naming.Context;

import nl.xs4all.banaan.tst8.fixtures.InitializedNamingContext;
import nl.xs4all.banaan.tst8.fixtures.MailSenderFixture;

import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Ignore;
import org.springframework.mail.MailSender;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.Singleton;

/**
 * Instruction for Guice on how to wire the test part of the application.
 * 
 * @author konijn
 * 
 */
@Ignore
public class TestOnlyModule extends AbstractModule {
    private static final String DUMMY_BUILD_PROPERTIES = "/dummy-build.properties";

    @Override
    protected void configure() {
        bind(MailSenderFixture.class).in(Singleton.class);
        bind(MailSender.class).to(MailSenderFixture.class).in(Singleton.class);
        bind(Context.class).to(InitializedNamingContext.class).in(Singleton.class);
    }

    @Provides @Singleton @BuildInfoResourceName public String provideResourceName() {
        return DUMMY_BUILD_PROPERTIES;        
    }
   
    // In production, it is the GuiceWebApplicationFactory that
    // adds the instantiation listener to the application.
    @Provides @Singleton
    public WicketTester provideWicketTester(WebApplication application, Injector injector) {
        application.addComponentInstantiationListener(
                new GuiceComponentInjector(application, injector));
        return new WicketTester(application);
    }
}
