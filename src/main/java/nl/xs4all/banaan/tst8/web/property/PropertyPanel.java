package nl.xs4all.banaan.tst8.web.property;

import nl.xs4all.banaan.tst8.service.ServiceException;
import nl.xs4all.banaan.tst8.web.DemoApplication;

import org.apache.log4j.Logger;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

/**
 * Display key/value pairs in some property set.
 * @author konijn
 *
 */
public class PropertyPanel extends Panel {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(PropertyPanel.class);
    
    public PropertyPanel(String id, final String location) {
        super(id);
        getSession().info("building  property panel");
        
        IModel model = new LoadableDetachableModel () {
            private static final long serialVersionUID = 1L;
            @Override
            public Object load() {
                try {
                    return DemoApplication.get().getServices().
                        getPropertyReader().read(location).getList();
                } catch (ServiceException se) {
                    logger.error("Caught Service Exception", se);
                    throw new RuntimeException(se);
                }
            }
        };
        
        add (new PropertyListView ("props", model) {
            private static final long serialVersionUID = 1L;
           
            @Override
            public void populateItem (ListItem item) {
                item.add(new Label("key"));
                item.add(new Label("value"));
            }
        });
    }
}
