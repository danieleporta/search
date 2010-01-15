package nl.xs4all.banaan.tst8.logging;

import nl.xs4all.banaan.tst8.logging.LogFor;

import org.slf4j.Logger;

import com.google.inject.Inject;

/** some random service that needs a logger */
public class MyUser {
    private final Logger logger;

    @Inject
    public MyUser(@LogFor(MyUser.class) Logger logger) {
        this.logger = logger;
        logger.info("Created {}", this);
    }
    
    public String getLoggerName() {
        return logger.getName();
    }
}
