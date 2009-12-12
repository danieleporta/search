 package nl.xs4all.banaan.tst8.web.jndi;

import java.util.List;

import nl.xs4all.banaan.tst8.service.JndiReader;
import nl.xs4all.banaan.tst8.service.ServiceException;
import nl.xs4all.banaan.tst8.util.Assoc;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import com.google.inject.Inject;

/**
 * Show JNDI data, given a location to find them
 *
 */
public class JndiPanel extends Panel {
    private static final long serialVersionUID = 1L;
   
    @Inject JndiReader jndiReader;
    
    public JndiPanel(String id) throws ServiceException {
        this(id, "");
    }

    public JndiPanel(String id, final String location) throws ServiceException {
        super(id);
        getSession().info("building jndi panel");

        final IModel<List<Assoc<Object>>> model = new JndiModel(location, jndiReader);
        
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
