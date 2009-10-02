package nl.xs4all.banaan.tst8.web.menu;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import nl.xs4all.banaan.tst8.service.ServiceException;
import nl.xs4all.banaan.tst8.web.DemoApplication;
import nl.xs4all.banaan.tst8.web.base.BasePage;

import org.apache.wicket.PageParameters;
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
public class MenuPage extends BasePage {
    private String panelClassName;

    public MenuPage(PageParameters pageParameters) {
        panelClassName = pageParameters.getString("panel", "");
        init();
    }
    
    @Override
    public void doInit() throws ServiceException {
        String panelId = "content";
        Panel panel = getPanel(panelId, panelClassName);
        add(panel);
    }

    public Panel getPanel(String panelId, String panelClassName) throws ServiceException {
        MenuList menu = DemoApplication.get().getMenuList();
        Class<? extends Panel> panelClass = menu.lookup(panelClassName);
        
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
