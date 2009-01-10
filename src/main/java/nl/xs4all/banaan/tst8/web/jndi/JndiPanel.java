package nl.xs4all.banaan.tst8.web.jndi;

import java.util.List;

import nl.xs4all.banaan.tst8.util.GenericBinding;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * Show JNDI data.
 *
 */
public class JndiPanel extends Panel {
    private static final long serialVersionUID = 1L;

    public JndiPanel(String id, List<GenericBinding<Object>> bindings) {
        super(id);

        add(new PropertyListView("bindings", bindings) {
            private static final long serialVersionUID = 1L;

            @Override
            public void populateItem (ListItem item) {
                item.add(new Label("key"));
                item.add(new Label("value"));
            }
        });

    }
}
