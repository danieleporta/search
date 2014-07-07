package nl.xs4all.banaan.tst8.web.jndi;

import nl.xs4all.banaan.tst8.fixtures.MockInjector;
import nl.xs4all.banaan.tst8.fixtures.MockInjectorBuilder;
import nl.xs4all.banaan.tst8.wiring.TestModule;

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

public class JndiPageTest {
    private MockInjector injector;
    private WicketTester tester;

    @Before
    public void setUp() {
        injector = new MockInjectorBuilder(new TestModule()).build();
        tester = injector.get(WicketTester.class);
    }

    @Test
    public void testRenderJndiPage1() {
        tester.startPage(JndiPage.class);
        tester.assertRenderedPage(JndiPage.class);
        tester.assertContains("aapval");
    }
 
    @Test
    public void testRenderJndiPage2() {
        tester.startPage(new JndiPage());
        tester.assertContains("aapval");    } 
    
    @Test
    public void testRenderJndiPage3() {
        tester.startPage(new JndiPage(""));
        tester.assertContains("aapval");
    }  
    
    @Test
    public void testRenderJndiPage4() {
        // check for value found under jdbc key
        tester.startPage(new JndiPage("jdbc"));
        tester.assertContains("miesval");   
    }
    
    /**
     * Empty page parameters should end up in main page
     */
    @Test
    public void testRenderJndiPageParam1() {
        tester.startPage(new JndiPage(new PageParameters ()));
        tester.assertContains("aapval");
    }
    
    @Test
    public void testRenderJndiPageIgnoresGarbageKeys() {
        tester.startPage(new JndiPage(new PageParameters ("garbage=ignored")));
        tester.assertContains("aapval");
    } 
    
    /**
     * empty location to root
     */
    @Test
    public void testRenderJndiPageParam3() {
        tester.startPage(new JndiPage(new PageParameters ("location=")));
        tester.assertContains("aapval");
    } 
    
    /**
     * see that dir2 is in dir1
     */
    @Test
    public void testRenderJndiPageParam4() {
        tester.startPage(new JndiPage(new PageParameters ("location=dir1")));
        tester.assertContains("DIR2");
    }
    
    /**
     * see that subdirs work
     */
    @Test
    public void testRenderJndiPageParam5() {
        tester.startPage(new JndiPage(new PageParameters ("location=dir1/dir2")));
        tester.assertContains("entry3val");
    }
   
    @Test()
    public void testServiceError() {
        tester.startPage(new JndiPage(new PageParameters ("location=never/never/land")));
        tester.assertInvisible("jndi:good");
        tester.assertErrorMessages(new String[] {"JNDI location not found: never/never/land"});
    }
    
    @Test
    public void testRenderJndiPage5() {
        // check clickability of jndi entries
        tester.startPage(JndiPage.class);
        tester.debugComponentTrees();
        // dir1 should lead to known page
        tester.clickLink("jndi:good:1:keylink");

        // in dir1, we see its own name, plus ptr to dir2
        tester.assertContains("dir1");
        tester.assertContains("dir2");
        tester.assertContains("DIR2\\-NODE");
    }
    
    @Test
    public void testRenderJndiPage6() {
        // check clickability of jndi entries
        tester.startPage(new JndiPage(new PageParameters ("location=dir1")));
 
        // click to dir2, find entry3val there
        tester.clickLink("jndi:good:0:keylink");
        tester.assertContains("dir2");
        tester.assertContains("entry3val");
    } 
}