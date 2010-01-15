package nl.xs4all.banaan.tst8.wiring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;

/** Guice interface to slf4j logger factory. */
public class LoggerProvider implements Provider<Logger> {
    private final Class<?> type;

    /** the provider will get loggers for this class */
    @Inject
    public LoggerProvider(Class<?> type) {
        this.type = type;
    }

    public Logger get() {
        return LoggerFactory.getLogger(type);
    }
}
