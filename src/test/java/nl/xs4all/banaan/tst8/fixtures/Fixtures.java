package nl.xs4all.banaan.tst8.fixtures;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.junit.matchers.JUnitMatchers.containsString;

import java.lang.reflect.InvocationTargetException;

import javax.naming.Context;
import javax.naming.NamingException;

import nl.xs4all.banaan.tst8.service.JndiReader;
import nl.xs4all.banaan.tst8.service.JndiReaderImpl;
import nl.xs4all.banaan.tst8.service.PropertyReader;
import nl.xs4all.banaan.tst8.service.PropertyReaderImpl;
import nl.xs4all.banaan.tst8.service.ServiceException;
import nl.xs4all.banaan.tst8.web.DemoApplication;
import nl.xs4all.banaan.tst8.web.base.BasePage;
import nl.xs4all.banaan.tst8.web.menu.MenuPanel;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.util.tester.ITestPageSource;
import org.apache.wicket.util.tester.WicketTester;
import org.springframework.mock.jndi.SimpleNamingContext;

/**
 * Various convenience objects for use during unit testing.
 * Useslazy initialisation, in future may be springified.
 * @author konijn
 *
 */
public class Fixtures {
    private Context initialContext;
    private WicketTester tester;
    private JndiReaderImpl jndiReader;
    private DemoApplication application;


    public Context getInitialContext() {
        if (initialContext == null) {
            try {
                initialContext = new SimpleNamingContext();
                initialContext.bind("elders/groet", "goedemorgen");
                initialContext.bind("aap", "aapval");
                initialContext.bind("dir1", "DIR1-NODE");
                initialContext.bind("jdbc", "JDBC-NODE");
                initialContext.bind("noot", "nootval");
                initialContext.bind("jdbc/mies", "miesval");
                initialContext.bind("jdbc/wim", "wimval");
                initialContext.bind("dir1/dir2", "DIR2-NODE");
                initialContext.bind("dir1/dir2/entry3", "entry3val");
            }
            catch (NamingException ne) {
                fail("JNDI fixture broken");
            }
        }
        return initialContext;
    }

    public JndiReader getJndiReader() {
        if (jndiReader == null) {
            jndiReader = new JndiReaderImpl();
            jndiReader.setInitialContext(getInitialContext());
        }
        return jndiReader;
    }

    public DemoApplication getApplication() {
        if (application == null) {
            application = new DemoApplication();
            PropertyReader propertyReader = new PropertyReaderImpl();
            application.setPropertyReader(propertyReader);
            application.setJndiReader(getJndiReader());
        }
        return application;
    }

    public WicketTester getTester() {
        if (tester == null) {
            tester = new WicketTester(getApplication());
        }
        return tester;
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
