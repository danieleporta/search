package nl.xs4all.banaan.tst8.web.jndi;

import nl.xs4all.banaan.tst8.service.ServiceException;
import nl.xs4all.banaan.tst8.util.GenericBinding;
import nl.xs4all.banaan.tst8.web.DemoApplication;

import org.apache.log4j.Logger;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

/**
 * Show JNDI data, given a location to find them
 *
 */
public class JndiPanel extends Panel {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(JndiPanel.class);

    public JndiPanel(String id, final String location) throws ServiceException {
        super(id);
        getSession().info("building jndi panel");

        // Make model dynamic to avoid it being serialised into the session.
        Model model = new Model() {
            @Override
            public Object getObject() {
                try {
                    return DemoApplication.get().getServices().
                        getJndiReader().read(location).getList();
                } catch (ServiceException se) {
                    logger.error("Caught Service Exception", se);
                    throw new RuntimeException(se);
                }
            }
        };
        
        add(new Label("location", location));
        add(new PropertyListView("bindings", model) {
            private static final long serialVersionUID = 1L;

            @Override
            public void populateItem (ListItem item) {
                GenericBinding<Object> binding = 
                    (GenericBinding<Object>) item.getModelObject();
                String path;
                if (location.equals("")) {
                    path = binding.getKey();
                }
                else {
                    path = location + "/" + binding.getKey();
                }
                
                item.add(
                    new BookmarkablePageLink(
                            "keylink", JndiPage.class,
                            new PageParameters("location=" + path)).
                                    add(new Label("keytext", binding.getKey())));
                        
                item.add(new Label("value"));
            }
        });

    }
}
