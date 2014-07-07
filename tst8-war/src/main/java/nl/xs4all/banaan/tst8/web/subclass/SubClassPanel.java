package nl.xs4all.banaan.tst8.web.subclass;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;

/** wrapper to test derivedComponent */
public class SubClassPanel extends Panel {
    private static final long serialVersionUID = 7639367862787724740L;

    public SubClassPanel(String id) {
        super(id);
        Component inner = new DerivedComponent("inner");
        add(inner);
    }

}
