package nl.xs4all.banaan.tst8.playwithlogging;

import org.junit.Ignore;
import org.slf4j.Logger;

import com.google.inject.AbstractModule;
import com.google.inject.Key;

/** 
 * Bind slf4j loggers for a list of classes provided in the module constructor. 
 * Users that want such a logger to be injected in the consructor must annotate it
 * with @LogFor(User.class).
 */
@Ignore
public class LogModule extends AbstractModule {
 
    // It would be nice if we could just read the annotation
    // without having to bind every use of Logger, but that
    // feature is unlikely to be implemented
    // http://code.google.com/p/google-guice/issues/detail?id=27

    /** bind logger for these classes*/
    private final Class<?>[] loggedClasses;

    /**
     * Create a logmodule that binds slf4j Logger with annotation
     * @LogFor(User.class), for each User in loggedClasses.
     */
    public LogModule(Class<?> ... loggedClasses) {
        super();
        this.loggedClasses = loggedClasses;
    }
    
    @Override
    protected void configure() {
        for (Class<?> c : loggedClasses) {
            bindLogger(c);
        }
    }

    private <T >void bindLogger(Class<T> type) {
        bind(Logger.class)
        .annotatedWith(new LogForImpl(type))
        .toProvider(new LoggerProvider(type));
    }

    /**
     * put this key in the injector to find logger for type.
     * Intended to integrate mock loggers.
     */
    public static Key<Logger> key(Class<?> type) {
        return Key.get(Logger.class, new LogForImpl(type));
    }
}

