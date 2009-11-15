package nl.xs4all.banaan.tst8.service.impl;


import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.matchers.JUnitMatchers.containsString;

import nl.xs4all.banaan.tst8.service.PropertyList;
import nl.xs4all.banaan.tst8.service.PropertyReader;
import nl.xs4all.banaan.tst8.service.ServiceException;
import nl.xs4all.banaan.tst8.service.impl.PropertyReaderImpl;

import org.junit.Test;

/**
 * boring test for property reader.
 * @author konijn
 *
 */
public class PropertyReaderImplTest {

    @Test
    public void testPropsAvailable () throws ServiceException {
        PropertyReader reader = new PropertyReaderImpl();
        PropertyList list = reader.read(null);
        
        assertTrue(list.getList().size() > 0);
        // this should always occur in system properties
        assertTrue(list.filter("user.name").getList().size() > 0);
    }
    
    @Test
    public void testBuildPropsAvailable () throws ServiceException {
        PropertyReader reader = new PropertyReaderImpl();
        PropertyList list = reader.read("/build.properties");
        
        assertTrue(list.getList().size() > 0);
        assertTrue(list.filter("group").getList().size() > 0);
    }

    @Test
    public void testNotFoundException () {
        PropertyReader reader = new PropertyReaderImpl();
        try {
            reader.read("/not/found/path/build.properties");
            fail("property file absence undetected");
        }
        catch (ServiceException se) {
            // please, include path in exceptions
            assertThat(se.getMessage(), containsString("not found"));
            assertThat(se.getMessage(), containsString("not/found/path"));
        }
    }
}
