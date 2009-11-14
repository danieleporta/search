package nl.xs4all.banaan.tst8.fixtures;

import org.junit.Ignore;

import nl.xs4all.banaan.tst8.web.DemoApplication;

/**
 * Base class for tests that want a wicket tester and application
 * with some parts mocked.
 * @author konijn
 *
 */
@Ignore
public class MockInjectedWicketTest {
    private MockInjector mockInjector;
    protected DemoApplication demoApplication;
    protected BasePageTester tester;

    /**
     * Create new injector and use it to populate application and tester.
     * @param classes in injector, replace these with an easymock object
     */
    protected void mock(Class<?> ... classes) {
        mockInjector = new MockInjector(classes);
        demoApplication = get(DemoApplication.class);
        tester = new BasePageTester(demoApplication);
    }
    
    /** retrieve object from injector */
    protected <T> T get(Class<T> clazz) {
        return mockInjector.get(clazz);
    }

    /** verify that all mocked objects have received expected operations */
    protected void verify() {
        mockInjector.verify();
    }
    
    /** put all mocked objects in replay mode */
    protected void replay() {
        mockInjector.replay();
    }
}
