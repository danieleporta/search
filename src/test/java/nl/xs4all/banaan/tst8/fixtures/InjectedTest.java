package nl.xs4all.banaan.tst8.fixtures;

import nl.xs4all.banaan.tst8.wiring.BaseApplicationModule;
import nl.xs4all.banaan.tst8.wiring.TestApplicationModule;

import org.junit.Before;
import org.junit.Ignore;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Base class for tests that need injection.
 * @author konijn
 *
 */
@Ignore
public abstract class InjectedTest {
    private Injector injector;

    @Before
    public void startUp() {
        injector = Guice.createInjector(
                new BaseApplicationModule(),
                new TestApplicationModule());
    }
   
    public <T> T get(Class<T> clazz) {
        return injector.getInstance(clazz);
    }
}
