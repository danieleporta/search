package nl.xs4all.banaan.tst8.wiring;

import org.slf4j.Logger;

import com.google.inject.AbstractModule;
import com.google.inject.Key;
import com.google.inject.Singleton;

/** 
 * Bind slf4j loggers for a list of classes provided in the module constructor. 
 * Users that want such a logger to be injected in the constructor must annotate it
 * with @LogFor(User.class).
 */
public class LogModule extends AbstractModule {
 
    // It would be nice if we could just read the annotation
    // without having to bind every use of Logger, but that
    // feature is unlikely to be implemented
    // http://code.google.com/p/google-guice/issues/detail?id=27

    /** bind logger for these classes*/
    private final Class<?>[] loggedClasses;

    /**
     * Create a logmodule that binds a singleton slf4j Logger with annotation
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
        bind(loggerKey(type))
        .toProvider(new LoggerProvider(type))
        .in(Singleton.class);
    }

    /**
     * Key for SLF4J Logger that is annotated with @LogFor(type).
     * Can be used in module to bind the logger, or in injector
     * to retrieve it.
     */
    public static Key<Logger> loggerKey(Class<?> type) {
        return Key.get(Logger.class, new LogForImpl(type));
    }
}

