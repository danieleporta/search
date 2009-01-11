package nl.xs4all.banaan.tst8.web.jndi;

import nl.xs4all.banaan.tst8.fixtures.Fixtures;
import nl.xs4all.banaan.tst8.web.jndi.JndiPage;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

/**
 * Test jndi page: required components should exist,
 * and for some known models, expected content should show up.
 * @author konijn
 *
 */
public class TestJndiPage {
    private Fixtures fixtures;
    private WicketTester tester;

    @Before
    public void setUp() {
        fixtures = new Fixtures();
        tester = fixtures.getTester();
    }

    @Test
    public void testRenderJndiPage1() {
        tester.startPage(JndiPage.class);
        fixtures.checkBasePage(JndiPage.class, "aapval");
    }
 
    @Test
    public void testRenderJndiPage2() {
        tester.startPage(new JndiPage());
        fixtures.checkBasePage(JndiPage.class, "aapval");
    } 
    
    @Test
    public void testRenderJndiPage3() {
        tester.startPage(new JndiPage(""));
        fixtures.checkBasePage(JndiPage.class, "aapval");
    }  
    
    @Test
    public void testRenderJndiPage4() {
        // check for value found under jdbc key
        tester.startPage(new JndiPage("jdbc"));
        fixtures.checkBasePage(JndiPage.class, "miesval");
    }  
}
