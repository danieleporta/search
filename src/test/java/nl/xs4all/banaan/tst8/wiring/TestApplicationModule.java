package nl.xs4all.banaan.tst8.wiring;


import javax.naming.Context;

import nl.xs4all.banaan.tst8.fixtures.InitializedNamingContext;
import nl.xs4all.banaan.tst8.fixtures.MailSenderFixture;

import org.junit.Ignore;
import org.springframework.mail.MailSender;

/**
 * Instruction for Guice on how to wire the test version of the application.
 * 
 * @author konijn
 * 
 */
@Ignore
public class TestApplicationModule extends BaseApplicationModule {
    private static final String DUMMY_BUILD_PROPERTIES = "/dummy-build.properties";

    @Override public Context provideContext() {
        return new InitializedNamingContext();
    }
    
    @Override public MailSender provideMailSender() {
        return new MailSenderFixture();
    }

    // TODO: decide visibility
    @Override public String getBuildPropertyResourceName() {
        return DUMMY_BUILD_PROPERTIES;
    }
}
