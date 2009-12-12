package nl.xs4all.banaan.tst8.fixtures;

import static org.easymock.EasyMock.createMock;

import com.google.inject.AbstractModule;
import com.google.inject.binder.AnnotatedBindingBuilder;

/**
 *  Guice config that provides mocks for classes indicated in constructor;
 *  use in combination with {@link MockInjector} 
 */
public class MockModule extends AbstractModule {
    private final Class<?>[] mockedClasses;

    public MockModule(Class<?>[] mockedClasses) {
        super();
        this.mockedClasses = mockedClasses;
    }
    
    @Override @SuppressWarnings("unchecked")
    protected void configure() {
        for (Class<?> c : mockedClasses) {
            ((AnnotatedBindingBuilder<Object>) bind(c)).toInstance(createMock(c));
        }
    }
}
