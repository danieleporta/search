package nl.xs4all.banaan.tst8.service.impl;


import static nl.xs4all.banaan.tst8.fixtures.DomainObjects.BUILD_NAME1;
import static org.junit.Assert.assertEquals;
import nl.xs4all.banaan.tst8.fixtures.InjectedTest;
import nl.xs4all.banaan.tst8.service.Services;

import org.junit.Test;

/** test that top-level services locator is operational */
public class ServicesImplTest extends InjectedTest {

    @Test
    public void testBuildInfoIsAccessibleAsService() {
        Services services = get(Services.class);
        assertEquals(BUILD_NAME1, services.getBuildInfo().getName());
    }
}
