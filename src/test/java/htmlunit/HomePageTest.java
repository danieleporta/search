package htmlunit;


import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.w3c.dom.Node;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Basic test of the application from the outside,
 * assumes a running instance.  This needs to be moved
 * outside the unit test environment.
 */
@Ignore
public class HomePageTest {
    
    private static final String BASE = "http://localhost:8080/tst8-app/";

    @Test
    public void testHomePageHasTitle() throws Exception {
        final WebClient webClient = new WebClient();
        final HtmlPage page = webClient.getPage(BASE);
        Assert.assertEquals("Property and Parameter Demo", page.getTitleText());
        // System.out.println(page.asXml());
    }

    @Test
    public void testHomePageHasNamedAnchor() throws Exception {
        final WebClient webClient = new WebClient();
        final HtmlPage page = webClient.getPage(BASE);
        HtmlAnchor anchor = page.getAnchorByName("top");
        Assert.assertEquals("TOP",anchor.asText());
    }
    
    @Test
    public void testHomePageHasLinksInMenu() throws Exception {
        final WebClient webClient = new WebClient();
        final HtmlPage page = webClient.getPage(BASE);
        HtmlElement menu = page.getElementById("menu");
        // hmm, where does the web client get its default namespace?
        List<?> list = menu.getByXPath("ul/li/a");
        Assert.assertTrue(list.size() > 3);
    }
    
    @Test
    public void testHomePageHasFirstLinkInMenu() throws Exception {
        final WebClient webClient = new WebClient();
        final HtmlPage page = webClient.getPage(BASE);
        HtmlElement menu = page.getElementById("menu");
        // hmm, where does the web client get its default namespace?
        List<?> list = menu.getByXPath("ul/li[1]/a");
        Assert.assertTrue(list.size() == 1);
        HtmlAnchor anchor = (HtmlAnchor) list.get(0);
        Assert.assertEquals("a", anchor.getNodeName());
        // Page nextPage = anchor.click();
    }
    
    @Test
    public void testHomePageCanClickFirstLinkInMenu() throws Exception {
        final WebClient webClient = new WebClient();
        final HtmlPage page = webClient.getPage(BASE);
        HtmlAnchor anchor = getMenuAnchor(page, 1);
        HtmlPage nextPage = anchor.click();
        Assert.assertTrue(nextPage.asText().contains("A FormComponentPanel test Form"));
    }

    private HtmlAnchor getMenuAnchor(final HtmlPage page, int index) {
        return getAnchor(page, "id('menu')/ul/li[" + index + "]/a");
    }

    private HtmlAnchor getAnchor(final HtmlPage page, String xpathExpr) {
        return (HtmlAnchor) getUniqueNode(page, xpathExpr);
    }

    private Node getUniqueNode(final HtmlPage page, String xpathExpr) {
        List<?> list = page.getByXPath(xpathExpr);
        Assert.assertTrue(list.size() == 1);
        return (Node) list.get(0);
    }
}
