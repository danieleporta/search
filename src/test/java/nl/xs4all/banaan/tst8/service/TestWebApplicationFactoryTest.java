package nl.xs4all.banaan.tst8.service;


import static org.junit.Assert.assertTrue;
import nl.xs4all.banaan.tst8.web.DemoApplication;

import org.apache.wicket.protocol.http.IWebApplicationFactory;
import org.apache.wicket.protocol.http.WebApplication;
import org.junit.Before;
import org.junit.Test;

public class TestWebApplicationFactoryTest {

    private IWebApplicationFactory factory;

    @Before
    public void setUp() throws Exception {
        factory = new TestWebApplicationFactory();
    }

    @Test
    public void testFactoryCanBuildStuff() {
        WebApplication application = factory.createApplication(null);
        assertTrue(application instanceof DemoApplication);
    }
}
