package nl.xs4all.banaan.tst8.fixtures;

import java.util.Properties;

import nl.xs4all.banaan.tst8.domain.Address;
import nl.xs4all.banaan.tst8.domain.Letter;
import nl.xs4all.banaan.tst8.service.BuildInfoImpl;
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
    
    public static final String NAME1 = "This is name1";
    public static final String STREET1 = "This is street1";
    public static final String CITY1 = "This is city1";
    public static final Address ADDRESS1 
            = new Address (NAME1, STREET1, CITY1);

    public static final String NAME2 = "This is name2";
    public static final String STREET2 = "This is street2";
    public static final String CITY2 = "This is city2";
    public static final Address ADDRESS2 
            = new Address (NAME2, STREET2, CITY2);

    public static final Long POSTAGE1 = 43L;
    public static final Letter LETTER1 
            = new Letter(ADDRESS1, ADDRESS2, POSTAGE1);
    
    public static final String BUILD_NAME1 = "Groovy Goldfish";
    public static final String BUILD_GROUP1 = "nl.xs4all.banaan.tst-test";
    public static final String BUILD_VERSION1 = "1.0-dummy-version-label";
    public static final String BUILD_USER1 = "root";
    
    public static final Properties BUILD_PROPERTIES1;
    static {
        BUILD_PROPERTIES1 = new Properties();
        BUILD_PROPERTIES1.put("name", BUILD_NAME1);
        BUILD_PROPERTIES1.put("group", BUILD_GROUP1);
        BUILD_PROPERTIES1.put("version", BUILD_VERSION1);
        BUILD_PROPERTIES1.put("user", BUILD_USER1);
    }
    
    public static final BuildInfoImpl BUILD_INFO1;
    static {
        BUILD_INFO1 = new BuildInfoImpl();
        BUILD_INFO1.setProperties(BUILD_PROPERTIES1);
    }

}
