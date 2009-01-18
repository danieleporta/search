package nl.xs4all.banaan.tst8.service;


import static org.junit.Assert.assertTrue;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test that the JNDI reader works as expected.
 * @author konijn
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/testContext.xml"})
public class JndiReaderImplTest {
    // appropriate intitialContext wired in in spring
    @Resource
    private JndiReaderImpl jndiReader;

    @Test
    public void testRead () throws ServiceException {
        JndiList list = jndiReader.read("elders");
        assertTrue(list.getList().size() > 0);
    }

}
