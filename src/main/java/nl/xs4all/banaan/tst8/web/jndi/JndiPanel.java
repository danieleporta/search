package nl.xs4all.banaan.tst8.web.jndi;

import nl.xs4all.banaan.tst8.service.JndiList;
import nl.xs4all.banaan.tst8.service.ServiceException;
import nl.xs4all.banaan.tst8.util.GenericBinding;
import nl.xs4all.banaan.tst8.web.DemoApplication;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
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

        JndiList jndiList = DemoApplication.get().getJndiReader().read(location);
        add(new Label("location", location));
        add(new PropertyListView("bindings", jndiList.getList()) {
            private static final long serialVersionUID = 1L;

            @Override
            public void populateItem (ListItem item) {
                GenericBinding<Object> binding = 
                    (GenericBinding<Object>) item.getModelObject();
                String key = binding.getKey();
                
                item.add(
                    new BookmarkablePageLink(
                            "keylink", JndiPage.class,
                            new PageParameters("location=" + key)).
                                    add(new Label("keytext", key)));
                        
                item.add(new Label("value"));
            }
        });

    }
}
