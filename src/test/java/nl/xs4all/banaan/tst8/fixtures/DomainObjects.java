package nl.xs4all.banaan.tst8.fixtures;

import nl.xs4all.banaan.tst8.service.Notification;

/**
 * Simple predefined domain objects for use in unit testing.
 * @author konijn
 *
 */
public class DomainObjects {
    public final static String TO1 = "test1@example.org";
    public final static String SUBJECT1 = "this is subject1";
    public final static String BODY1 = "this is body1";
    public final static Notification NOTIFICATION1 
            = new Notification(TO1, SUBJECT1, BODY1);
    
    public final static String TO2 = "test2@example.org";
    public final static String SUBJECT2 = "this is subject2";
    public final static String BODY2 = "this is body2";
    public final static Notification NOTIFICATION2 
            = new Notification(TO2, SUBJECT2, BODY2);    
}
