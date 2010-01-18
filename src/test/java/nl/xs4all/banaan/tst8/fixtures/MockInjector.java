package nl.xs4all.banaan.tst8.fixtures;


import java.util.List;

import org.easymock.EasyMock;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Module;
import com.google.inject.util.Modules;


/**
 * Wrapper for a normal test injector, except that
 * specific interfaces or keys can be mocked.
 * @author konijn
 *
 */
public class MockInjector {
    protected final Injector injector;
    private final List<Key<?>> mockedKeys;
   
    public MockInjector(Module module, List<Key<?>> mockedKeys) {
        this.mockedKeys = mockedKeys;
        injector = Guice.createInjector(
                Modules.override(module).
                with(new MockModule(this.mockedKeys)));
    }    
    
    /** retrieve object from the underlying injector */
    public <T> T get(Class<T> clazz) {
        return injector.getInstance(clazz);
    }
    
    /** retrieve object from underlying injector */
    public <T> T get(Key<T> key) {
        return injector.getInstance(key);
    }

    /** put all mock objects in replay mode */
    public void replay() {
        for (Key<?> key: mockedKeys) {
            EasyMock.replay(get(key));
        }
    }

    /** verify all mock objects have seen the expected invocations */
    public void verify() {
        for (Key<?> key: mockedKeys) {
            EasyMock.verify(get(key));
        }
    }
}
