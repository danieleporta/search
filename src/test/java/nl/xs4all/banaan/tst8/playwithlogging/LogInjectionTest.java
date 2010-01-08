package nl.xs4all.banaan.tst8.playwithlogging;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import nl.xs4all.banaan.tst8.fixtures.MockInjector;
import nl.xs4all.banaan.tst8.fixtures.MockInjectorBuilder;

import org.easymock.EasyMock;
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
        logModule = new LogModule(MyUser.class, MySecondUserImpl.class);
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
        assertFalse(annotation.equals(new LogForImpl(MySecondUserImpl.class)));
    }
    
    @Test
    public void testMyUserIsInjected() {
        MyUser user = injector.getInstance(MyUser.class);
        assertEquals("nl.xs4all.banaan.tst8.playwithlogging.MyUser", user.getLoggerName());
    }

    @Test
    public void testMySecondUserIsInjected() {
        MySecondUser user = injector.getInstance(MySecondUserImpl.class);
        assertEquals("nl.xs4all.banaan.tst8.playwithlogging.MySecondUserImpl", user.getLoggerName());
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
    
    @Test
    public void testMockInjectorBuilderFindsSimpleClasses() {
        MockInjector injector2 = new MockInjectorBuilder(logModule).build();
        MySecondUser user = injector2.get(MySecondUserImpl.class);
        assertEquals("nl.xs4all.banaan.tst8.playwithlogging.MySecondUserImpl", user.getLoggerName());
    }
    
    @Test
    public void testMockInjectorBuilderFindsSimpleClassThatIsMocked() {
        MockInjector injector2 = new MockInjectorBuilder(logModule)
            .mock(MySecondUser.class)
            .build();
        MySecondUser user = injector2.get(MySecondUser.class);
        EasyMock.expect(user.getLoggerName()).andReturn("banaan");
        injector2.replay();
        assertEquals("banaan", user.getLoggerName());
        injector2.verify();
    }
    
    @Test
    public void testMockInjectorBuilderFindsSimpleClassThatIsMockedByKey() {
        MockInjector injector2 = new MockInjectorBuilder(new MyUserModule())
            .mock(Key.get(MySecondUser.class))
            .build();
        MySecondUser user = injector2.get(MySecondUser.class);
        EasyMock.expect(user.getLoggerName()).andReturn("banaan");
        injector2.replay();
        assertEquals("banaan", user.getLoggerName());
        injector2.verify();
    }
    
    @Test
    public void testMockInjectorBuilderFindsLogger() {
        MockInjector injector2 = new MockInjectorBuilder(new MyUserModule())
            .build();
        Logger logger = injector2.get(LogModule.key(MySecondUserImpl.class));
        assertEquals("nl.xs4all.banaan.tst8.playwithlogging.MySecondUserImpl", logger.getName());
    }
    
    @Test
    public void testMockInjectorBuilderFindsLoggerAsSingleton() {
        MockInjector injector2 = new MockInjectorBuilder(new MyUserModule())
            .build();
        Logger logger = injector2.get(LogModule.key(MySecondUserImpl.class));
        assertTrue(logger == injector2.get(LogModule.key(MySecondUserImpl.class)));
    }

    @Test
    public void testTheLoggerWhenMockedIsSingleton() {
        Key<Logger> loggerKey = LogModule.key(MySecondUserImpl.class);
        MockInjector injector2 = new MockInjectorBuilder(new MyUserModule())
            .mock(loggerKey)
            .build();
        Logger logger = injector2.get(loggerKey);
        assertTrue(logger == injector2.get(loggerKey));
    }
    
    @Test
    public void testTheLoggerCanBeMockedButIsHighlyFragile() {
        // a normal injector, except that one logger is mocked
        Key<Logger> loggerKey = LogModule.key(MySecondUserImpl.class);
        MockInjector injector2 = new MockInjectorBuilder(new MyUserModule())
            .mock(loggerKey)
            .build();
 
        // that logger will see the following messages
        Logger logger = injector2.get(loggerKey);
        logger.info("MySecondUserImpl was created");
        logger.warn("banaan");
        injector2.replay();
        
        // letting injector create an object triggers first log
        // then trigger an additional log event
        MySecondUser user = injector2.get(MySecondUser.class);
        user.log("banaan");
        
        // verify log messages are as expected
        injector2.verify();
    }
    
}
