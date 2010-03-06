package nl.xs4all.banaan.tst8.logging;

import nl.xs4all.banaan.tst8.logging.LogFor;

import org.slf4j.Logger;

import com.google.inject.Inject;

/** some random service that needs a logger */
public class MySecondUserImpl implements MySecondUser {
    private final Logger logger;

    @Inject
    public MySecondUserImpl(@LogFor(MySecondUserImpl.class) Logger logger) {
        this.logger = logger;
        logger.info("MySecondUserImpl was created");
    }
    
    public String getLoggerName() {
        return logger.getName();
    }

    public void log(String message) {
        logger.warn(message);
    }

    public Logger getLogger() {
        return logger;
    }
}