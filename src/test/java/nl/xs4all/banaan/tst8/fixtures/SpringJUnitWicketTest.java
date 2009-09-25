package nl.xs4all.banaan.tst8.fixtures;

import javax.annotation.Resource;

import nl.xs4all.banaan.tst8.web.DemoApplication;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Base class for tests that want a WicketTester
 * that uses a standard Spring-configured application.
 * 
 * @author konijn
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/testContext.xml"})
public abstract class SpringJUnitWicketTest {

    @Resource
    protected DemoApplication demoApplication;
    protected BasePageTester tester;

    @Before
    public void setUp() {
        tester = new BasePageTester(demoApplication);
    }

}
