package nl.xs4all.banaan.tst8.service.impl;

import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.BUILD_GROUP1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.BUILD_INFO1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.BUILD_NAME1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.BUILD_USER1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.BUILD_VERSION1;
import static org.junit.Assert.assertEquals;
import nl.xs4all.banaan.tst8.fixtures.InjectedTest;
import nl.xs4all.banaan.tst8.service.BuildInfo;

import org.junit.Test;

public class BuildInfoImplTest extends InjectedTest {
   
    @Test
    public void testBuildInfoFieldsExist() {
        assertEquals(BUILD_NAME1, BUILD_INFO1.getName());
        assertEquals(BUILD_GROUP1, BUILD_INFO1.getGroup());
        assertEquals(BUILD_VERSION1, BUILD_INFO1.getVersion());        
        assertEquals(BUILD_USER1, BUILD_INFO1.getUser());
    }
    
    @Test
    public void testBuildInfoFieldsExistForInjectedBean() {
        BuildInfo buildInfo = get(BuildInfo.class);
        assertEquals(BUILD_NAME1, buildInfo.getName());
        assertEquals(BUILD_GROUP1, buildInfo.getGroup());
        assertEquals(BUILD_VERSION1, buildInfo.getVersion());        
        assertEquals(BUILD_USER1, buildInfo.getUser());
    }    
}
