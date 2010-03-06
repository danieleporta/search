package nl.xs4all.banaan.tst8.web.menu;


import nl.xs4all.banaan.tst8.fixtures.MockInjector;
import nl.xs4all.banaan.tst8.fixtures.MockInjectorBuilder;
import nl.xs4all.banaan.tst8.web.onchange.OnchangePanel;
import nl.xs4all.banaan.tst8.web.upload.UploadPanel;
import nl.xs4all.banaan.tst8.wiring.TestModule;

import org.apache.wicket.PageParameters;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

public class MenuPageTest {
    private MockInjector injector;
    private WicketTester tester;

    @Before
    public void setUp() {
        injector = new MockInjectorBuilder(new TestModule()).build();
        tester = injector.get(WicketTester.class);
    }

    @Test
    public void testConstructionWithGoodParameter() {
        tester.startPage(MenuPage.class, new PageParameters("panel=onchange"));
        tester.assertRenderedPage(MenuPage.class);
    }
    
    @Test(expected = RuntimeException.class)
    public void testConstructionWithBadParameter() {
        tester.startPage(MenuPage.class, new PageParameters("panel=IDontExist"));
        tester.assertRenderedPage(MenuPage.class);
    }  
    
    @Test
    public void testRenderedPanel() {
        tester.startPage(MenuPage.class, new PageParameters("panel=upload"));
        tester.assertRenderedPage(MenuPage.class);
        tester.assertComponent("content", UploadPanel.class);
    }
    
    @Test
    public void testAnotherRenderedPanel() {
        tester.startPage(MenuPage.class, new PageParameters("panel=onchange"));
        tester.assertRenderedPage(MenuPage.class);
        tester.assertComponent("content", OnchangePanel.class);
    }
    
}
