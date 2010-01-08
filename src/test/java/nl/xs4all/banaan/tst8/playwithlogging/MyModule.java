package nl.xs4all.banaan.tst8.playwithlogging;

import org.junit.Ignore;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

@Ignore
public class MyModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(new TypeLiteral<MyLogger<MyUser>>(){}).
            toProvider(new MyLoggerProvider<MyUser>(
                    new TypeLiteral<MyLogger<MyUser>>(){}));
    }
}
