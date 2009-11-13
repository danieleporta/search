package nl.xs4all.banaan.tst8.fixtures;

import nl.xs4all.banaan.tst8.web.DemoApplication;

import org.junit.Before;

/**
 * Base class for tests that want a WicketTester
 * that uses a standard injected application.
 * 
 * @author konijn
 *
 */
public abstract class InjectedWicketTest extends InjectedTest {
    protected DemoApplication demoApplication;
    protected BasePageTester tester;

    @Before
    public void setUp() {
        demoApplication = get(DemoApplication.class);
        tester = new BasePageTester(demoApplication);
    }
}
