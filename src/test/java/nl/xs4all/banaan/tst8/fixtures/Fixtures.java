package nl.xs4all.banaan.tst8.fixtures;



import javax.naming.Context;

import nl.xs4all.banaan.tst8.service.JndiReaderImpl;
import nl.xs4all.banaan.tst8.service.Notificator;
import nl.xs4all.banaan.tst8.service.PropertyReader;
import nl.xs4all.banaan.tst8.web.DemoApplication;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Various convenience objects for use during unit testing.
 * @author konijn
 *
 */
public class Fixtures {
    private BasePageTester tester;
    private Context jndiRootContext;
    private JndiReaderImpl jndiReader;
    private MailSenderFixture mailSenderFixture;
    private PropertyReader propertyReader;
    private Notificator notificator;
    private DemoApplication application;

    public static Fixtures get() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("testContext.xml");
        return (Fixtures) ctx.getBean("fixtures", Fixtures.class);
    }
    
    public Context getInitialContext() {
        return jndiRootContext;
    }
    
    public void setJndiRootContext(Context jndiRootContext) {
        this.jndiRootContext = jndiRootContext;
    }
    
    public JndiReaderImpl getJndiReader() {
        return jndiReader;
    }

    public void setJndiReader(JndiReaderImpl jndiReader) {
        this.jndiReader = jndiReader;
    }
    
    public MailSenderFixture getMailSenderFixture() {
        return mailSenderFixture;
    }
    
    public void setMailSenderFixture(MailSenderFixture mailSenderFixture) {
        this.mailSenderFixture = mailSenderFixture;
    }

    public PropertyReader getPropertyReader() {
        return propertyReader;
    }
    
    public void setPropertyReader(PropertyReader propertyReader) {
        this.propertyReader = propertyReader;
    }
    
    public Notificator getNotificator() {
        return notificator;
    }
    
    public void setNotificator(Notificator notificator) {
        this.notificator = notificator;
    }

    public DemoApplication getApplication() {
        return application;
    }
    
    public void setApplication(DemoApplication application) {
        this.application = application;
    }

    public BasePageTester getTester() {
        return tester;
    }

    public void setTester(BasePageTester tester) {
        this.tester = tester;
    }
}
