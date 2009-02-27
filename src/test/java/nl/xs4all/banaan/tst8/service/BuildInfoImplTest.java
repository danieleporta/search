package nl.xs4all.banaan.tst8.service;


import static org.junit.Assert.assertEquals;

import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class BuildInfoImplTest {
    private static final String MOCK_BUILD_NAME = "Groovy Goldfish";
    private static final String MOCK_BUILD_GROUP = "nl.xs4all.banaan.tst-test";
    private static final String MOCK_BUILD_VERSION = "1.0-dummy-version-label";
    private static final String MOCK_BUILD_USER = "root";

    private Properties mockBuildProperties;
    private BuildInfo buildInfo;

    @Before
    public void setUp() throws Exception {
        mockBuildProperties = new Properties();
        mockBuildProperties.put("name", MOCK_BUILD_NAME);
        mockBuildProperties.put("group", MOCK_BUILD_GROUP);
        mockBuildProperties.put("version", MOCK_BUILD_VERSION);
        mockBuildProperties.put("user", MOCK_BUILD_USER);
        BuildInfoImpl buildInfoImpl = new BuildInfoImpl();
        buildInfoImpl.setProperties(mockBuildProperties);
        buildInfo = buildInfoImpl;
    }

    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void testBuildInfoFieldsExist() {

        assertEquals(MOCK_BUILD_NAME, buildInfo.getName());
        assertEquals(MOCK_BUILD_GROUP, buildInfo.getGroup());
        assertEquals(MOCK_BUILD_VERSION, buildInfo.getVersion());        
        assertEquals(MOCK_BUILD_USER, buildInfo.getUser());
    }

}
