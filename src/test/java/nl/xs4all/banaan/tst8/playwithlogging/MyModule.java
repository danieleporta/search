package nl.xs4all.banaan.tst8.playwithlogging;

import org.junit.Ignore;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.util.Types;

/** bind loggers for some specific classes */
@Ignore
public class MyModule extends AbstractModule {

    @Override
    protected void configure() {
        bindLogger(MyUser.class);
        bindLogger(MySecondUser.class);
    }

    private <T >void bindLogger(Class<T> type) {
        bind(myLoggerType(type)).toProvider(new MyLoggerProvider<T>(myLoggerType(type)));
    }

    @SuppressWarnings("unchecked")
    private <T> TypeLiteral<MyLogger<T>> myLoggerType(Class <T> type) {
        return (TypeLiteral<MyLogger<T>>) TypeLiteral.get(Types.newParameterizedType(MyLogger.class, type));
    }
}
