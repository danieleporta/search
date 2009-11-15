package nl.xs4all.banaan.tst8.web;


import static org.junit.Assert.fail;
import nl.xs4all.banaan.tst8.fixtures.BasePageTester;
import nl.xs4all.banaan.tst8.fixtures.WicketMockInjector;
import nl.xs4all.banaan.tst8.web.error.ErrorPage;
import nl.xs4all.banaan.tst8.web.home.HomePage;
import nl.xs4all.banaan.tst8.web.onchange.OnchangePanel;

import org.apache.wicket.protocol.http.MockHttpServletRequest;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

public class DemoApplicationTest {

    private WicketMockInjector injector;
    private BasePageTester tester;

    @Before
    public void setUp() {
        injector = new WicketMockInjector();
        tester = injector.tester();
    }
    
    @Test
    public void testProcessRequestCycle() {
        tester.processRequestCycle(HomePage.class);
        tester.assertRenderedPage(HomePage.class);
    }

    @Test
    public void testProcessRequestCycle2() {
        tester.setupRequestAndResponse();
        MockHttpServletRequest request = tester.getServletRequest();
        // the url includes some default for contextroot and servlet name
        request.setURL("http://localhost/DemoApplication/DemoApplication/error");
        tester.processRequestCycle();
        tester.assertRenderedPage(ErrorPage.class);
    }
    
    @Test
    public void testProcessRequestCycleBreaksWithoutContextRoot() {
        tester.setupRequestAndResponse();
        MockHttpServletRequest request = tester.getServletRequest();
        try {
            request.setURL("http://localhost/error");
            tester.processRequestCycle();
            fail("Wicket is expected to break without fake content root");
        }
        catch (StringIndexOutOfBoundsException e) {
            // OK
        }
    }
    
    @Test
    public void testProcessRequestCycle3() {
        tester.setupRequestAndResponse();
        MockHttpServletRequest request = tester.getServletRequest();
        request.setURL(makeUrl(request, "error"));
        tester.processRequestCycle();
        tester.assertRenderedPage(ErrorPage.class);
    }
    
    @Test
    public void testProcessRequestCycle4() {
        visitUrl(tester, "error");
        tester.assertRenderedPage(ErrorPage.class);
    }
    
    @Test
    public void testMountedPageWithParameters() {
        visitUrl(tester, "menu/panel/onchange");
        tester.assertComponent("content", OnchangePanel.class);
    }

    /**
     * Make the WicketTester move to the url.
     * @param tester
     * @param path
     */
    private void visitUrl(WicketTester tester, String path) {
        tester.setupRequestAndResponse();
        MockHttpServletRequest request = tester.getServletRequest();
        request.setURL(makeUrl(request, path));
        tester.processRequestCycle();
    }

    /**
     * return an URL that matches where the WicketTester pretends
     * that the application is mounted.
     * @param request
     * @param rest
     * @return
     */
    private String makeUrl(MockHttpServletRequest request, String rest) {
        String serverName = request.getServerName();
        String contextPath = request.getContextPath();
        String servletPath = request.getServletPath();
        return "http://" + serverName + contextPath + servletPath + "/" + rest;
    }
}
