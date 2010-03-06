package nl.xs4all.banaan.tst8.web.subclass;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

/** 
 * Pretend this is a reusable component, say a calendar or generic menu-bar.
 * It has markup.
 * @author konijn
 *
 */
public class BaseComponent extends Panel {
    private static final long serialVersionUID = 1L;

    public BaseComponent(String id, String value) {
        super(id);
        // to verify which file is read:
        // System.out.println("MARKUP: " + getMarkupStream());
        add (new Label("label", value));
    }

}
