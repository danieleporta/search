package nl.xs4all.banaan.tst8.playwithlogging;

import org.junit.Ignore;
import org.slf4j.Logger;

import com.google.inject.AbstractModule;

/** bind slf4j loggers for some specific classes */
@Ignore
public class LogModule extends AbstractModule {
 
    // It would be nice if we could just read the annotation
    // without having to bind every use of Logger, but that
    // feature is unlikely to be implemented
    // http://code.google.com/p/google-guice/issues/detail?id=27

    @Override
    protected void configure() {
        bindLogger(MyUser.class);
        bindLogger(MySecondUser.class);
    }

    private <T >void bindLogger(Class<T> type) {
        bind(Logger.class)
            .annotatedWith(new LogForImpl(type))
            .toProvider(new LoggerProvider(type));
    }
}
