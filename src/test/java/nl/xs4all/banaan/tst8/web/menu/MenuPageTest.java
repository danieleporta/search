package nl.xs4all.banaan.tst8.web.menu;


import static org.junit.Assert.fail;
import nl.xs4all.banaan.tst8.fixtures.BasePageTester;
import nl.xs4all.banaan.tst8.fixtures.WicketMockInjector;
import nl.xs4all.banaan.tst8.web.onchange.OnchangePanel;
import nl.xs4all.banaan.tst8.web.upload.UploadPanel;

import org.apache.wicket.PageParameters;
import org.junit.Before;
import org.junit.Test;

public class MenuPageTest {
    private WicketMockInjector injector;
    private BasePageTester tester;

    @Before
    public void setUp() {
        injector = new WicketMockInjector();
        tester = injector.tester();
    }


    @Test
    public void testConstructionWithGoodParameter() {
        tester.startPage(MenuPage.class, new PageParameters("panel=onchange"));
        tester.assertRenderedPage(MenuPage.class);
    }
    
    @Test
    public void testConstructionWithBadParameter() {
        try {
            tester.startPage(MenuPage.class, new PageParameters("panel=IDontExist"));
            tester.assertRenderedPage(MenuPage.class);
            // TODO: consider returning not-found
            fail("undetected error");
        }
        catch (RuntimeException e) {
            // ok
        }
    }  
    
    @Test
    public void testRenderedPanel() {
        tester.startPage(MenuPage.class, new PageParameters("panel=upload"));
        tester.assertRenderedPage(MenuPage.class);
        tester.debugComponentTrees();
        tester.assertComponent("content", UploadPanel.class);
    }
    
    @Test
    public void testAnotherRenderedPanel() {
        tester.startPage(MenuPage.class, new PageParameters("panel=onchange"));
        tester.assertRenderedPage(MenuPage.class);
        tester.debugComponentTrees();
        tester.assertComponent("content", OnchangePanel.class);
    }
    
}
