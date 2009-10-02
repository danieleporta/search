package nl.xs4all.banaan.tst8.web.base;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import nl.xs4all.banaan.tst8.service.ServiceException;
import nl.xs4all.banaan.tst8.web.DemoApplication;
import nl.xs4all.banaan.tst8.web.buildInfo.BuildInfoPanel;
import nl.xs4all.banaan.tst8.web.menu.MenuList;
import nl.xs4all.banaan.tst8.web.menu.MenuPanel;
import nl.xs4all.banaan.tst8.web.upload.UploadPanel;

import org.apache.log4j.Logger;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * Page on which a single content panel is presented. Content panel is
 * hard-coded in this version and intended to be table driven in next iteration.
 * It's intended to be invoked from a menu, and will happen to contain a menu as
 * well.
 * 
 * @author konijn
 * 
 */
public class MenuPage extends WebPage {
    
    protected static Logger logger = Logger.getLogger(MenuPage.class);
    private Class<? extends Panel> panelClass;

    public MenuPage() {
        go();
    }
    
    public MenuPage(PageParameters pageParameters) {
        String panelName = pageParameters.getString("panel", "");
        MenuList menu = DemoApplication.get().getMenuList();
        menu.lookup(panelName);
        go();
    }

    private void go() {
        panelClass = UploadPanel.class;
        getSession().info("Setting up menupage");
        
        add(new MenuPanel("menu"));
        add(new FeedbackPanel("feedback"));
        add(new BuildInfoPanel("buildinfo"));
        try {
            String panelId = "content";
            Panel panel = getPanel(panelId);
            add(panel);
        }
        catch (ServiceException se) {
            logger.error("Caught Service Exception", se);
            throw new RuntimeException("Service Exception", se);
        }
    }

    
    public Panel getPanel(String panelId) throws ServiceException {
        Class<?>[] signature = new Class<?>[] { String.class };
        
        Constructor<? extends Panel> constructor;
        try {
            constructor = panelClass.getConstructor(signature);
            Panel panel;
            try {
                panel = constructor.newInstance(panelId);
                return panel;
            } catch (IllegalArgumentException e) {
                throw new ServiceException("configuration error", e);
            } catch (InstantiationException e) {
                throw new ServiceException("configuration error", e);
            } catch (IllegalAccessException e) {
                throw new ServiceException("configuration error", e);
            } catch (InvocationTargetException e) {
                throw new ServiceException("configuration error", e);
            }
        } catch (SecurityException e) {
            throw new ServiceException("configuration error", e);
        } catch (NoSuchMethodException e) {
            throw new ServiceException("configuration error", e);
        }
    }
}
