package nl.xs4all.banaan.tst8.playwithlogging;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;

public class LogInjectionTest {
    
    private LogModule logModule;
    private Injector injector;

    @Before
    public void setUp() {
        logModule = new LogModule(MyUser.class, MySecondUser.class);
        injector = Guice.createInjector(logModule);
    }

    @Test
    public void testLoggingAnnotationEqualForSameClass() {
        LogForImpl annotation = new LogForImpl(MyUser.class);
        assertEquals(annotation, new LogForImpl(MyUser.class));
    }
    
    @Test
    public void testLoggingAnnotationUnequalForOtherClass() {
        LogForImpl annotation = new LogForImpl(MyUser.class);
        assertFalse(annotation.equals(new LogForImpl(MySecondUser.class)));
    }
    
    @Test
    public void testMyUserIsInjected() {
        MyUser user = injector.getInstance(MyUser.class);
        assertEquals("nl.xs4all.banaan.tst8.playwithlogging.MyUser", user.getLoggerName());
    }

    @Test
    public void testMySecondUserIsInjected() {
        MySecondUser user = injector.getInstance(MySecondUser.class);
        assertEquals("nl.xs4all.banaan.tst8.playwithlogging.MySecondUser", user.getLoggerName());
    }
    
    @Test
    public void testExplicitRetrievalOfLogger() {
        Key<Logger> key = Key.get(Logger.class, new LogForImpl(MyUser.class));
        Logger logger = injector.getInstance(key);
        logger.info("found the logger");
    }
    
    @Test
    public void testRetrievalOfLoggerWithKeyHelper() {
        Logger logger = injector.getInstance(LogModule.key(MyUser.class));
        assertEquals("nl.xs4all.banaan.tst8.playwithlogging.MyUser", logger.getName());
    }    
    
}
