package nl.xs4all.banaan.tst8.fixtures;

import nl.xs4all.banaan.tst8.service.JndiReader;
import nl.xs4all.banaan.tst8.web.DemoApplication;

import org.apache.wicket.util.tester.WicketTester;

/**
 * Various convenience objects for use during unit testing.
 * @author konijn
 *
 */
public class Fixtures {
    private WicketTester tester;

    public Fixtures () {
	JndiReader jndiReader = new JndiReaderFixture();
        DemoApplication app = new DemoApplication();
        tester = new WicketTester(app);
        app.setJndiReader(jndiReader);
    }
    
    public WicketTester getTester() {
	return tester;
    }    
}
