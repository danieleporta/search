package nl.xs4all.banaan.tst8.service;

import static org.junit.Assert.assertEquals;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.*;

import org.junit.Test;

public class BuildInfoImplTest {
   
    @Test
    public void testBuildInfoFieldsExist() {
        assertEquals(BUILD_NAME1, BUILD_INFO1.getName());
        assertEquals(BUILD_GROUP1, BUILD_INFO1.getGroup());
        assertEquals(BUILD_VERSION1, BUILD_INFO1.getVersion());        
        assertEquals(BUILD_USER1, BUILD_INFO1.getUser());
    }

}
