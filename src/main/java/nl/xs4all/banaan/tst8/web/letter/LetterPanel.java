package nl.xs4all.banaan.tst8.web.letter;

import nl.xs4all.banaan.tst8.domain.Address;
import nl.xs4all.banaan.tst8.domain.Letter;
import nl.xs4all.banaan.tst8.web.address.AddressPanel;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.ComponentPropertyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

/**
 * Example panel intended to show how a part of the model
 * can be rendered by a sub panel.
 * @author konijn
 *
 */
public class LetterPanel extends Panel {

    private static final long serialVersionUID = 8379326263858269585L;
    
    public LetterPanel(String panelId) {
        this (panelId, new LetterPanel.LetterModel());
    }
    
    public LetterPanel(String panelId, IModel model) {
        super(panelId, new CompoundPropertyModel(model));
        
        add(new AddressPanel("from", new ComponentPropertyModel("from")));
        add(new AddressPanel("to", new ComponentPropertyModel("to")));
        add(new Label("postage"));
    }

    private static class LetterModel extends LoadableDetachableModel {
        private static final long serialVersionUID = 3207586619607652567L;
        
        @Override
        protected Object load() {
            return new Letter (
                    new Address("twiet", "aap", "noot"),
                    new Address("mies", "wim", "zus"),
                    43L);
        }
    }
}
