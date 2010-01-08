package nl.xs4all.banaan.tst8.playwithlogging;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class LogInjectionTest {

    @Test
    public void shouldLoggingAnnotationBeEqualForSameClass() {
        LogForImpl annotation = new LogForImpl(MyUser.class);
        assertEquals(annotation, new LogForImpl(MyUser.class));
    }
    
    @Test
    public void testMyUserIsInjected() {
        Injector injector = Guice.createInjector(new LogModule());
        MyUser user = injector.getInstance(MyUser.class);
        assertEquals("nl.xs4all.banaan.tst8.playwithlogging.MyUser", user.getLoggerName());
    }

    @Test
    public void testMySecondUserIsInjected() {
        Injector injector = Guice.createInjector(new LogModule());
        MySecondUser user = injector.getInstance(MySecondUser.class);
        assertEquals("nl.xs4all.banaan.tst8.playwithlogging.MySecondUser", user.getLoggerName());
    }
}
