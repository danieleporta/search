package nl.xs4all.banaan.tst8.web.notificator;


import static org.easymock.EasyMock.createMock;

import com.google.inject.AbstractModule;
import com.google.inject.binder.AnnotatedBindingBuilder;

/**
 * Guice module that binds indicated classes to an EasyMock object.
 * @author konijn
 *
 */
public class MockModule extends AbstractModule {
    private final Class<?>[] classes;

    public MockModule(Class<?>[] classes) {
        this.classes = classes;
    }

    @Override
    protected void configure() {
        for (Class<?> c : classes) {
            ((AnnotatedBindingBuilder<Object>) bind(c)).toInstance(createMock(c));
        }
    }
}
