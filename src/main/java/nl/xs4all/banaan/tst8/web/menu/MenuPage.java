package nl.xs4all.banaan.tst8.web.menu;

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
        try {
            return panelClass.getConstructor(signature).newInstance(panelId);
        } catch (Exception e) {
            throw new IllegalStateException("configuration error", e);
        }
    }
}
