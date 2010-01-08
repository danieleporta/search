package nl.xs4all.banaan.tst8.playwithlogging;

import com.google.inject.Inject;

/** some random service that needs a logger */
public class MyUser {
    private final MyLogger<MyUser> logger;

    @Inject
    public MyUser(@MyLogging(MyUser.class) MyLogger<MyUser> logger) {
        this.logger = logger;
        logger.info("Created {}", this);
    }
    
    public String getLoggerName() {
        return logger.getName();
    }
}
