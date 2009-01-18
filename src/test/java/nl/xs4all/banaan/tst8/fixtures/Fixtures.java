package nl.xs4all.banaan.tst8.fixtures;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.junit.matchers.JUnitMatchers.containsString;

import java.lang.reflect.InvocationTargetException;

import javax.naming.Context;

import nl.xs4all.banaan.tst8.service.JndiReader;
import nl.xs4all.banaan.tst8.service.JndiReaderImpl;
import nl.xs4all.banaan.tst8.service.Notificator;
import nl.xs4all.banaan.tst8.service.PropertyReader;
import nl.xs4all.banaan.tst8.service.ServiceException;
import nl.xs4all.banaan.tst8.web.DemoApplication;
import nl.xs4all.banaan.tst8.web.base.BasePage;
import nl.xs4all.banaan.tst8.web.menu.MenuPanel;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.util.tester.ITestPageSource;
import org.apache.wicket.util.tester.WicketTester;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Various convenience objects for use during unit testing.
 * @author konijn
 *
 */
public class Fixtures {
    private WicketTester tester;
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

    public WicketTester getTester() {
        return tester;
    }

    public void setTester(WicketTester tester) {
        this.tester = tester;
    }
    
    /**
     * check that a rendered subclass of basepage has expected class 
     * and contents
     */
    public void checkBasePage(Class<? extends BasePage> pageClass, String ... patterns) {
        tester.assertRenderedPage(pageClass);
        
        //assert rendered label components that any basepage has
        tester.assertComponent("feedback", FeedbackPanel.class);
        tester.assertComponent("menu", MenuPanel.class);
        
        for (String pattern : patterns) {
            tester.assertContains(pattern);
        }
    }

    /**
     * verify that creating this page results in just the right kind
     * of exception.
     * @param source
     * @param patterns
     */
    public void checkServiceException(ITestPageSource source,
            String... patterns) {
        try {
            tester.startPage(source);
            fail("Exception expected");
        }
        catch (Exception e) {
            Throwable cause = e;
            if (cause instanceof WicketRuntimeException) {
                cause = cause.getCause();
            }
            if (cause instanceof InvocationTargetException) {
                cause = cause.getCause();
            }
            if (cause instanceof RuntimeException) {
                cause = cause.getCause();
            }
            if (cause instanceof ServiceException) {
                for (String pattern : patterns) {
                    String message = cause.getMessage();
                    assertThat(message, containsString(pattern));
                }
            }
            else {
                fail("Unexcepted type of exception");
            }
        }
        
    }

}
