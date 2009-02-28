package nl.xs4all.banaan.tst8.service.impl;


import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.BUILD_NAME1;
import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import nl.xs4all.banaan.tst8.service.Services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/testContext.xml"})
public class ServicesImplTest {

    @Resource
    Services services;

    @Test
    public void testBuildInfoIsAccessibleAsService() {
        assertEquals(BUILD_NAME1, services.getBuildInfo().getName());
    }
}
