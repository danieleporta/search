package nl.xs4all.banaan.tst8.web.jndi;

import nl.xs4all.banaan.tst8.fixtures.Fixtures;
import nl.xs4all.banaan.tst8.web.jndi.JndiPage;

import org.apache.wicket.PageParameters;
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
    
    /**
     * Empty page parameters should end up in main page
     */
    @Test
    public void testRenderJndiPageParam1() {
        tester.startPage(new JndiPage(new PageParameters ()));
        fixtures.checkBasePage(JndiPage.class, "aapval");
    }
    
    /**
     * ignore garbage keys
     */
    @Test
    public void testRenderJndiPageParam2() {
        tester.startPage(new JndiPage(new PageParameters ("garbage=ignored")));
        fixtures.checkBasePage(JndiPage.class, "aapval");
    } 
    
    /**
     * empty location to root
     */
    @Test
    public void testRenderJndiPageParam3() {
        tester.startPage(new JndiPage(new PageParameters ("location=")));
        fixtures.checkBasePage(JndiPage.class, "aapval");
    } 
    
    /**
     * see that dir2 is in dir1
     */
    @Test
    public void testRenderJndiPageParam4() {
        tester.startPage(new JndiPage(new PageParameters ("location=dir1")));
        fixtures.checkBasePage(JndiPage.class, "DIR2");
    }
    
    /**
     * see that subdirs work
     */
    @Test
    public void testRenderJndiPageParam5() {
        tester.startPage(new JndiPage(new PageParameters ("location=dir1/dir2")));
        fixtures.checkBasePage(JndiPage.class, "entry3val");
    }
    
    /**
     * Clickthrough works better with bookmarkable pages than with
     * links: link wants to save whole rendered page in session,
     * which requires serialisability.
     */
    @Test
    public void testRenderJndiPage5() {
        // check clickability of jndi entries
        tester.startPage(JndiPage.class);
 
        // dir1 should lead to known page
        tester.clickLink("jndi:bindings:1:keylink");

        // in dir1, we see its own name, plus ptr to dir2
        fixtures.checkBasePage(JndiPage.class, "dir1", "dir2", "DIR2\\-NODE");
    }
    
    @Test
    public void testRenderJndiPage6() {
        // check clickability of jndi entries
        tester.startPage(new JndiPage(new PageParameters ("location=dir1")));
 
        // dir1 should lead to known page
        tester.clickLink("jndi:bindings:0:keylink");

        // in dir2, we see entry3
        tester.debugComponentTrees();
        fixtures.checkBasePage(JndiPage.class, "dir2", "entry3val");
    } 
}
