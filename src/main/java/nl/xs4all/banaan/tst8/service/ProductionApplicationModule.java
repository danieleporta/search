package nl.xs4all.banaan.tst8.service;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.springframework.mail.MailSender;

/**
 * Tell Guice how to wire production version of the application.
 * @author konijn
 *
 */
public class ProductionApplicationModule extends BaseApplicationModule {
    private static final String BUILD_PROPERTIES = "/build.properties";

    @Override
    public String getBuildPropertyResourceName() {
        return BUILD_PROPERTIES;
    }

    @Override
    public Context provideContext() {
        return jndiResourceLookup("env");
    }

    @Override
    public MailSender provideMailSender() {
        return jndiResourceLookup("mail/Session");
    }

    @SuppressWarnings("unchecked")
    private <T> T jndiResourceLookup(String name) {
        try {
            InitialContext context = new InitialContext();
            return (T) context.lookup("java:comp/" + name);
        } 
        catch (NamingException e) {
            throw new RuntimeException("lookup error - not found: " + name, e);
        }
        catch (ClassCastException e) {
            throw new RuntimeException("lookup error - wrong type: " + name, e);
        }
    }
}
