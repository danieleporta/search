package nl.xs4all.banaan.tst8.wiring;

import javax.mail.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

/**
 * Tell Guice how to wire production version of the application.
 * @author konijn
 *
 */
public class ProductionApplicationModule extends AbstractModule {
    private static final String BUILD_PROPERTIES = "/build.properties";
    
    @Override
    protected void configure() {
    }

    @Provides @Singleton @BuildInfoResourceName public String provideResourceName() {
        return BUILD_PROPERTIES;        
    }

    @Provides @Singleton
    public Context provideContext() {
        return (Context) jndiResourceLookup("");
    }

    @Provides @Singleton
    public MailSender provideMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setSession((Session) jndiResourceLookup("/mail/Session"));
        return sender;
    }

    /** look up JNDI resource in predefined part of the namespace. */
    private Object jndiResourceLookup(String name) {
        try {
            InitialContext context = new InitialContext();
            return context.lookup("java:comp/env" + name);
        } 
        catch (NamingException e) {
            throw new RuntimeException("lookup error - not found: " + name, e);
        }
    }
}
