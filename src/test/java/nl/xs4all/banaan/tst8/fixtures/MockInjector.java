package nl.xs4all.banaan.tst8.fixtures;

import org.easymock.EasyMock;

import nl.xs4all.banaan.tst8.wiring.BaseApplicationModule;
import nl.xs4all.banaan.tst8.wiring.TestApplicationModule;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.util.Modules;


/**
 * Wrapper for a normal test injector, except that
 * specific interfaces can be mocked.
 * @author konijn
 *
 */
public class MockInjector {
    private final Injector injector;
    private final Class<?>[] classes;
    
    public MockInjector(Class<?>... classes) {
        this.classes = classes;
        injector = Guice.createInjector(Modules.override(
                new BaseApplicationModule(),
                new TestApplicationModule()).
                with(new MockModule(classes)));
    }
    
    /** retrieve object from the underlying injector */
    public <T> T get(Class<T> clazz) {
        return injector.getInstance(clazz);
    }

    /** put all mock objects in replay mode */
    public void replay() {
        for (Class<?> c: classes) {
            EasyMock.replay(get(c));
        }
    }

    /** verify all mock objects have seen the expected invocations */
    public void verify() {
        for (Class<?> c: classes) {
            EasyMock.verify(get(c));
        }
    }
}
