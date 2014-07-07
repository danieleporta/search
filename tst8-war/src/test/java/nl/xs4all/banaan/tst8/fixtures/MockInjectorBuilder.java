package nl.xs4all.banaan.tst8.fixtures;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Key;
import com.google.inject.Module;

/** build an injector where some keys or classes may be mocked. */
public class MockInjectorBuilder {
    
    private final Module module;
    
    private List<Key<?>> mockedKeys;
    
    public MockInjectorBuilder (Module module) {
        mockedKeys = new ArrayList<Key<?>>();
        this.module = module;
    }
    
    public MockInjectorBuilder mock(Class<?> clazz) {
        return mock(Key.get(clazz));
    }
    
    public MockInjectorBuilder mock(Key<?> key) {
        mockedKeys.add(key);
        return this;
    }
    
    public MockInjector build() {
        return new MockInjector(module, mockedKeys);
    }
}
