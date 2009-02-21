package nl.xs4all.banaan.tst8.web.letter;



import nl.xs4all.banaan.tst8.web.address.AddressPanel;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.ComponentPropertyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

public class LetterPanel extends Panel {
    private static final long serialVersionUID = 8379326263858269585L;

    /** model must return letter as object */
    public LetterPanel(String panelId, IModel model) {
        super(panelId, new CompoundPropertyModel(model));
        
        add(new AddressPanel("from", new ComponentPropertyModel("from")));
        add(new AddressPanel("to", new ComponentPropertyModel("to")));
        add(new Label("postage"));
    }

}
