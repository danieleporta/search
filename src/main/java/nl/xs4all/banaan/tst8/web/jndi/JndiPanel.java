package nl.xs4all.banaan.tst8.web.jndi;

import nl.xs4all.banaan.tst8.service.JndiList;
import nl.xs4all.banaan.tst8.service.ServiceException;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * Show JNDI data, given a location to find them
 *
 */
public class JndiPanel extends Panel {
    private static final long serialVersionUID = 1L;

    public JndiPanel(String id, String location) throws ServiceException {
        super(id);
        
        JndiList jndiList = new JndiList(location);
        add(new Label("location", location));
        add(new PropertyListView("bindings", jndiList.getList()) {
            private static final long serialVersionUID = 1L;

            @Override
            public void populateItem (ListItem item) {
                item.add(new Label("key"));
                item.add(new Label("value"));
            }
        });

    }
}
