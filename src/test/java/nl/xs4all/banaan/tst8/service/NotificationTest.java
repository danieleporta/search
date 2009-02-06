package nl.xs4all.banaan.tst8.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NotificationTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void testToString() {
        Notification notification = new Notification("t1", "s1", "b1");
        assertEquals("Notification[to=t1,subject=s1,body=b1]", 
                notification.toString());
    }
    
    @Test
    public void testEquals() {
        Notification notification = new Notification("t1", "s1", "b1");
        Notification sameNotification = new Notification("t1","s1","b1");
        Notification otherNotification = new Notification("t2","s1","b1");
        Notification subNotification = new NotificationSubclass("t1", "s1", "b1");
        Notification sameSubNotification = new NotificationSubclass("t1", "s1", "b1");
        
        assertTrue("reflexive", notification.equals(notification));
        assertTrue("symmetric", notification.equals(sameNotification));
        assertTrue("symmetric2", sameNotification.equals(notification));
        assertFalse("difference", notification.equals(otherNotification));
        
        try {
            assertFalse("null test", notification.equals(null));
        }
        catch (Exception e) {
            fail("equals null must return false without exception");
        }
        
        assertTrue("hashcode", 
                notification.hashCode() == sameNotification.hashCode());
        
        assertTrue("subclass", subNotification.equals(sameSubNotification));

        // Bloch disagrees, but here the equals contract prevails over Liskov.
        assertTrue("subclass may or may not be equal but must be symmetric", 
                notification.equals(subNotification)
                    == subNotification.equals(notification));
    }
    
    /**
     * dummy subclass to verify equals survives subclassing.
     * @author konijn
     *
     */
    public static class NotificationSubclass extends Notification {
        public NotificationSubclass (String to, String subject, String body) {
            super(to, subject, body);
        }
    };

}
