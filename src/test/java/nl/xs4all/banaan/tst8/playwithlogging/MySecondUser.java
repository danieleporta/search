package nl.xs4all.banaan.tst8.playwithlogging;

import com.google.inject.Inject;

/** some random service that needs a logger */
public class MySecondUser {
    private final MyLogger<MySecondUser> logger;

    @Inject
    public MySecondUser(MyLogger<MySecondUser> logger) {
        this.logger = logger;
        logger.info("MySecondUser was created");
    }
    
    public String getLoggerName() {
        return logger.getName();
    }
}