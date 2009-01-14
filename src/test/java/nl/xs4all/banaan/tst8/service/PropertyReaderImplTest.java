package nl.xs4all.banaan.tst8.service;


import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * boring test for property reader.
 * @author konijn
 *
 */
public class PropertyReaderImplTest {

    @Test
    public void testPropsAvailable () {
        PropertyReader reader = new PropertyReaderImpl();
        PropertyList list = reader.read();
        assertTrue(list.getList().size() > 0);
    }

}
