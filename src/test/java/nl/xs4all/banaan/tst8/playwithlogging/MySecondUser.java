package nl.xs4all.banaan.tst8.playwithlogging;

import org.slf4j.Logger;

import com.google.inject.Inject;

/** some random service that needs a logger */
public class MySecondUser {
    private final Logger logger;

    @Inject
    public MySecondUser(@LogFor(MySecondUser.class) Logger logger) {
        this.logger = logger;
        logger.info("MySecondUser was created");
    }
    
    public String getLoggerName() {
        return logger.getName();
    }
}