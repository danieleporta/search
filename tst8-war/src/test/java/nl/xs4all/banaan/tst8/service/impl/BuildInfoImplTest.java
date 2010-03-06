package nl.xs4all.banaan.tst8.service.impl;

import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.BUILD_GROUP1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.BUILD_INFO1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.BUILD_NAME1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.BUILD_USER1;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.BUILD_VERSION1;
import static org.junit.Assert.assertEquals;
import nl.xs4all.banaan.tst8.fixtures.MockInjector;
import nl.xs4all.banaan.tst8.fixtures.MockInjectorBuilder;
import nl.xs4all.banaan.tst8.service.BuildInfo;
import nl.xs4all.banaan.tst8.wiring.TestModule;

import org.junit.Before;
import org.junit.Test;

public class BuildInfoImplTest {
    private MockInjector injector;

    @Before
    public void setUp() {
        injector = new MockInjectorBuilder(new TestModule()).build();
    }
    
    @Test
    public void testBuildInfoFieldsExist() {
        assertEquals(BUILD_NAME1, BUILD_INFO1.getName());
        assertEquals(BUILD_GROUP1, BUILD_INFO1.getGroup());
        assertEquals(BUILD_VERSION1, BUILD_INFO1.getVersion());        
        assertEquals(BUILD_USER1, BUILD_INFO1.getUser());
    }
    
    @Test
    public void testBuildInfoFieldsExistForInjectedBean() {
        BuildInfo buildInfo = injector.get(BuildInfo.class);
        assertEquals(BUILD_NAME1, buildInfo.getName());
        assertEquals(BUILD_GROUP1, buildInfo.getGroup());
        assertEquals(BUILD_VERSION1, buildInfo.getVersion());        
        assertEquals(BUILD_USER1, buildInfo.getUser());
    }    
}
