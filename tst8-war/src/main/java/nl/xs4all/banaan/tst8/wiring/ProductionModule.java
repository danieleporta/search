package nl.xs4all.banaan.tst8.wiring;

import com.google.inject.AbstractModule;

/** Guice configuration, both the shared and production-only part. */
public class ProductionModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new BaseApplicationModule());
        install(new ProductionOnlyModule());
    }
}
