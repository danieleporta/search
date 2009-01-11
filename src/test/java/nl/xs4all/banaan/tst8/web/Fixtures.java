package nl.xs4all.banaan.tst8.web;

import org.apache.wicket.util.tester.WicketTester;

/**
 * Various convenience objects for use during unit testing.
 * @author konijn
 *
 */
public class Fixtures {
    private WicketTester tester;

    public Fixtures () {
        DemoApplication app = new DemoApplication();
        tester = new WicketTester(app);
    }
    
    public WicketTester getTester() {
	return tester;
    }    
}
