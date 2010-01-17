package nl.xs4all.banaan.tst8.web.menu;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import nl.xs4all.banaan.tst8.web.base.BasePage;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * Page on which a single content panel is presented. 
 * It's intended to be invoked from a menu, and will happen to contain a menu as
 * well.
 */
public class MenuPage extends BasePage {
    private final String panelClassName;

    public MenuPage(PageParameters pageParameters) {
        panelClassName = pageParameters.getString("panel", "");
        add(createPanel("content", panelClassName));
    }

    public Panel createPanel(String panelId, String panelClassName)  {
        Class<? extends Panel> panelClass = MenuList.lookup(panelClassName);
        
        Class<?>[] signature = new Class<?>[] { String.class };
        Constructor<? extends Panel> constructor;
        try {
            constructor = panelClass.getConstructor(signature);
            Panel panel;
            try {
                // any exception here is a design error,
                // not to be signalled with a service exception
                panel = constructor.newInstance(panelId);
                return panel;
            } catch (IllegalArgumentException e) {
                throw new IllegalStateException("configuration error", e);
            } catch (InstantiationException e) {
                throw new IllegalStateException("configuration error", e);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException("configuration error", e);
            } catch (InvocationTargetException e) {
                throw new IllegalStateException("configuration error", e);
            }
        } catch (SecurityException e) {
            throw new IllegalStateException("configuration error", e);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("configuration error", e);
        }
    }
}
