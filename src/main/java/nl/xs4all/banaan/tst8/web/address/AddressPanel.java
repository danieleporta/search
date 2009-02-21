package nl.xs4all.banaan.tst8.web.address;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

/**
 * Simple panel, intended to demonstrate compositin of panels.
 * @author konijn
 *
 */
public class AddressPanel extends Panel {
    private static final long serialVersionUID = -8781531432652666470L;

    public AddressPanel(String id, IModel model) {
        super(id, model);
        add (new Label("name"));
        add (new Label("street"));
        add (new Label("city"));
    }

}
