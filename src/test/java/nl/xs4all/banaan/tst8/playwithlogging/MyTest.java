package nl.xs4all.banaan.tst8.playwithlogging;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class MyTest {

    @Test
    public void testObjectIsInjected() {
        Injector injector = Guice.createInjector(new MyModule());
        MyUser user = injector.getInstance(MyUser.class);
        assertEquals("nl.xs4all.banaan.tst8.playwithlogging.MyUser", user.getLoggerName());
    }

}
