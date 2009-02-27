package nl.xs4all.banaan.tst8.service;

import static org.junit.Assert.assertEquals;
import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/testContext.xml"})
public class BuildInfoImplTest {
    
    @Resource
    private BuildInfo buildInfo;
   
    @Test
    public void testBuildInfoFieldsExist() {
        assertEquals(BUILD_NAME1, BUILD_INFO1.getName());
        assertEquals(BUILD_GROUP1, BUILD_INFO1.getGroup());
        assertEquals(BUILD_VERSION1, BUILD_INFO1.getVersion());        
        assertEquals(BUILD_USER1, BUILD_INFO1.getUser());
    }
    
    @Test
    public void testBuildInfoFieldsExistForSpringWiredBean() {
        assertEquals(BUILD_NAME1, buildInfo.getName());
        assertEquals(BUILD_GROUP1, buildInfo.getGroup());
        assertEquals(BUILD_VERSION1, buildInfo.getVersion());        
        assertEquals(BUILD_USER1, buildInfo.getUser());
    }    

}
