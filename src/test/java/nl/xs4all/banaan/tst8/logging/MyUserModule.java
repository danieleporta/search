package nl.xs4all.banaan.tst8.logging;

import nl.xs4all.banaan.tst8.wiring.LogModule;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

/** two meaningless classes for testing log and mock injection */
public class MyUserModule extends AbstractModule {

    @Override
    protected void configure() {
        // this pretends to be normal binding
        bind(MyUser.class).in(Singleton.class);
        bind(MySecondUser.class).to(MySecondUserImpl.class).in(Singleton.class);
        
        // and this is extra to make logger binding work
        install(new LogModule(MyUser.class, MySecondUserImpl.class));
    }
}
