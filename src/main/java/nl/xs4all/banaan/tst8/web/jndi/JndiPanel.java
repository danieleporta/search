 package nl.xs4all.banaan.tst8.web.jndi;

import java.util.List;

import nl.xs4all.banaan.tst8.service.ServiceException;
import nl.xs4all.banaan.tst8.util.Assoc;
import nl.xs4all.banaan.tst8.web.DemoApplication;

import org.apache.log4j.Logger;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

/**
 * Show JNDI data, given a location to find them
 *
 */
public class JndiPanel extends Panel {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(JndiPanel.class);
    
    public JndiPanel(String id) throws ServiceException {
        this(id, "");
    }

    public JndiPanel(String id, final String location) throws ServiceException {
        super(id);
        getSession().info("building jndi panel");

        final IModel<List<Assoc<Object>>> model = new LoadableDetachableModel<List<Assoc<Object>>>() {
            private static final long serialVersionUID = -6205291386596032973L;

            @Override
            public List<Assoc<Object>> load() {
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
        add(new PropertyListView<Assoc<Object>>("bindings", model) {
            private static final long serialVersionUID = 1L;

            @Override
            public void populateItem (ListItem<Assoc<Object>> item) {
                final Assoc<Object> binding = item.getModelObject();
                final String path = location.equals("") 
                        ? binding.getKey()
                        : location + "/" + binding.getKey();
                item.add(
                    new BookmarkablePageLink<Void>(
                            "keylink", JndiPage.class,
                            new PageParameters("location=" + path)).
                                    add(new Label("keytext", binding.getKey())));
                        
                item.add(new Label("value"));
            }
        });

    }
}
