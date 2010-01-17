package nl.xs4all.banaan.tst8.web.jndi;

import nl.xs4all.banaan.tst8.fixtures.BasePageTester;
import nl.xs4all.banaan.tst8.fixtures.WicketMockInjector;

import org.apache.wicket.PageParameters;
import org.apache.wicket.WicketRuntimeException;
import org.junit.Before;
import org.junit.Test;

/**
 * Test jndi page: required components should exist,
 * and for some known models, expected content should show up.
 * @author konijn
 *
 */

public class JndiPageTest {
    private WicketMockInjector injector;
    private BasePageTester tester;

    @Before
    public void setUp() {
        injector = new WicketMockInjector();
        tester = injector.tester();
    }

    @Test
    public void testRenderJndiPage1() {
        tester.startPage(JndiPage.class);
        tester.checkBasePage(JndiPage.class, "aapval");
    }
 
    @Test
    public void testRenderJndiPage2() {
        tester.startPage(new JndiPage());
        tester.checkBasePage(JndiPage.class, "aapval");
    } 
    
    @Test
    public void testRenderJndiPage3() {
        tester.startPage(new JndiPage(""));
        tester.checkBasePage(JndiPage.class, "aapval");
    }  
    
    @Test
    public void testRenderJndiPage4() {
        // check for value found under jdbc key
        tester.startPage(new JndiPage("jdbc"));
        tester.checkBasePage(JndiPage.class, "miesval");
    }
    
    /**
     * Empty page parameters should end up in main page
     */
    @Test
    public void testRenderJndiPageParam1() {
        tester.startPage(new JndiPage(new PageParameters ()));
        tester.checkBasePage(JndiPage.class, "aapval");
    }
    
    /**
     * ignore garbage keys
     */
    @Test
    public void testRenderJndiPageParam2() {
        tester.startPage(new JndiPage(new PageParameters ("garbage=ignored")));
        tester.checkBasePage(JndiPage.class, "aapval");
    } 
    
    /**
     * empty location to root
     */
    @Test
    public void testRenderJndiPageParam3() {
        tester.startPage(new JndiPage(new PageParameters ("location=")));
        tester.checkBasePage(JndiPage.class, "aapval");
    } 
    
    /**
     * see that dir2 is in dir1
     */
    @Test
    public void testRenderJndiPageParam4() {
        tester.startPage(new JndiPage(new PageParameters ("location=dir1")));
        tester.checkBasePage(JndiPage.class, "DIR2");
    }
    
    /**
     * see that subdirs work
     */
    @Test
    public void testRenderJndiPageParam5() {
        tester.startPage(new JndiPage(new PageParameters ("location=dir1/dir2")));
        tester.checkBasePage(JndiPage.class, "entry3val");
    }
    
    /**
     * see that unknown location results in error
     */
    @Test(expected = WicketRuntimeException.class)
    public void testServiceError() {
        tester.startPage(new JndiPage(new PageParameters ("location=never/never/land")));
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
        tester.debugComponentTrees();
        // dir1 should lead to known page
        tester.clickLink("jndi:bindings:1:keylink");
tester.debugComponentTrees();
        // in dir1, we see its own name, plus ptr to dir2
        tester.checkBasePage(JndiPage.class, "dir1", "dir2", "DIR2\\-NODE");
    }
    
    @Test
    public void testRenderJndiPage6() {
        // check clickability of jndi entries
        tester.startPage(new JndiPage(new PageParameters ("location=dir1")));
 
        // click to dir2, find entry2val there
        tester.clickLink("jndi:bindings:0:keylink");
        tester.checkBasePage(JndiPage.class, "dir2", "entry3val");
    } 
}
