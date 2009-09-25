package nl.xs4all.banaan.tst8.web.onchange;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

/**
 * Show how an AJAX callback may be used to put content
 * in another field.  The canonical use case is filling
 * in street and city when zipcode is entered.
 * @author konijn
 *
 */
public class OnchangePanel extends Panel {
    private static final long serialVersionUID = 4815145731202521073L;

    public OnchangePanel(String id, IModel model) {
        super(id, model);
        getSession().info("building onchange panel");

    }
}
