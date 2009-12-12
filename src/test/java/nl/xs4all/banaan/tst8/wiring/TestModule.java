package nl.xs4all.banaan.tst8.wiring;

import com.google.inject.AbstractModule;

/** Guice module containing both shared and testing bindings */
public class TestModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new BaseApplicationModule());
        install(new TestOnlyModule());
    }
}
