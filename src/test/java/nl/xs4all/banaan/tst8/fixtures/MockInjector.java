package nl.xs4all.banaan.tst8.fixtures;

import nl.xs4all.banaan.tst8.wiring.TestModule;

import org.easymock.EasyMock;

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
    protected final Injector injector;
    private final Class<?>[] mockedClasses;
    
    public MockInjector(Class<?>... mockedClasses) {
        this.mockedClasses = mockedClasses;
        injector = Guice.createInjector(
                Modules.override(new TestModule()).
                        with(new MockModule(mockedClasses)));
    }
    
    /** retrieve object from the underlying injector */
    public <T> T get(Class<T> clazz) {
        return injector.getInstance(clazz);
    }

    /** put all mock objects in replay mode */
    public void replay() {
        for (Class<?> c: mockedClasses) {
            EasyMock.replay(get(c));
        }
    }

    /** verify all mock objects have seen the expected invocations */
    public void verify() {
        for (Class<?> c: mockedClasses) {
            EasyMock.verify(get(c));
        }
    }
}
