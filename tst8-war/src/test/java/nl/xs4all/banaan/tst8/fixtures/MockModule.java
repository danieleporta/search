package nl.xs4all.banaan.tst8.fixtures;

import static org.easymock.EasyMock.createMock;

import java.util.List;

import com.google.inject.AbstractModule;
import com.google.inject.Key;
import com.google.inject.binder.AnnotatedBindingBuilder;

/**
 *  Guice config that provides mocks for keys indicated in constructor;
 *  use in combination with {@link MockInjector} 
 */
public class MockModule extends AbstractModule {
    private final List<Key<?>> mockedKeys;

    public MockModule(List<Key<?>> mockedKeys) {
        super();
        this.mockedKeys = mockedKeys;
    }
    
    @Override @SuppressWarnings("unchecked")
    protected void configure() {
        for (Key<?> key : mockedKeys) {
            ((AnnotatedBindingBuilder<Object>) bind(key)).toInstance(createMock(interfaceToMock(key)));
        }
    }

    /** the interface to be mocked for this key */
    public Class<?> interfaceToMock(Key<?> key) {
        return key.getTypeLiteral().getRawType();
    }
}
