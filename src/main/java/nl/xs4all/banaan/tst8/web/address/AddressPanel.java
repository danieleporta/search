package nl.xs4all.banaan.tst8.web.address;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

/**
 * Simple panel, intended to demonstrate composition of panels.
 * @author konijn
 *
 */
public class AddressPanel extends Panel {
    private static final long serialVersionUID = -8781531432652666470L;

    /** model must return an address as object */
    public AddressPanel(String id, IModel model) {
        super(id);
        
        // without the wrap, passing an enclosing ComponentPropertyModel breaks;
        // see http://www.nabble.com/N-level-CompoundPropertyModel-td22024267.html
        setModel(new CompoundPropertyModel(wrap(model)));
        add (new Label("name"));
        add (new Label("street"));
        add (new Label("city"));
    }

}
