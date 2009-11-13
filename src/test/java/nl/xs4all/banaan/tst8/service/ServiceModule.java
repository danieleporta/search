package nl.xs4all.banaan.tst8.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import nl.xs4all.banaan.tst8.fixtures.MailSenderFixture;
import nl.xs4all.banaan.tst8.service.impl.BuildInfoImpl;
import nl.xs4all.banaan.tst8.service.impl.JndiReaderImpl;
import nl.xs4all.banaan.tst8.service.impl.NotificatorImpl;
import nl.xs4all.banaan.tst8.service.impl.PropertyReaderImpl;

import org.springframework.mail.MailSender;

import com.google.inject.AbstractModule;

/**
 * How to inject a ServicesImpl for test purposes.
 * @author konijn
 *
 */
public class ServiceModule extends AbstractModule {

    @Override
    protected void configure() {
         bind(JndiReader.class).to(JndiReaderImpl.class);
         bind(Notificator.class).to(NotificatorImpl.class);
         bind(PropertyReader.class).to(PropertyReaderImpl.class);
         bind(BuildInfo.class).to(BuildInfoImpl.class);
         
         // TODO: this is only production, where eg catalina
         // has mail.jar to provide MessagingException,
         // which is not available from normal Maven repo.
         // bind(MailSender.class).to(JavaMailSenderImpl.class);
         bind(MailSender.class).to(MailSenderFixture.class);
         
         // TODO: limit this to build properties only
         // TODO: migrate to @Provides
         // TODO: separate test and production
         bind(Properties.class).toInstance(
                 getPropertiesFromResource("/dummy-build.properties"));
         
         
    }
    
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
