package nl.xs4all.banaan.tst8.service;


import static org.junit.Assert.assertTrue;
import nl.xs4all.banaan.tst8.fixtures.Fixtures;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test that the JNDI reader works as expected.
 * @author konijn
 *
 */
public class JndiReaderImplTest {
    private JndiReaderImpl reader;
    private Fixtures fixtures;

    @Before
    public void setUp() throws Exception {
        fixtures = Fixtures.get();
        reader = new JndiReaderImpl();
        reader.setInitialContext(fixtures.getInitialContext());
    }

    @After
    public void tearDown() throws Exception {
        reader = null;
    }
    
    @Test
    public void testRead () throws ServiceException {
        JndiList list = reader.read("elders");
        assertTrue(list.getList().size() > 0);
    }

}
