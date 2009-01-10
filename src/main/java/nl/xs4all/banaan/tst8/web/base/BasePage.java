package nl.xs4all.banaan.tst8.web.base;

import nl.xs4all.banaan.tst8.web.menu.MenuPanel;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

public class BasePage extends WebPage {
    
    public BasePage(final PageParameters parameters) {
        add(new MenuPanel("menu"));
        add(new FeedbackPanel("feedback"));
        getSession().info("Setting up basepage");
    }

}
