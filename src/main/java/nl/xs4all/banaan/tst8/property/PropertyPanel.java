package nl.xs4all.banaan.tst8.property;

import java.util.List;

import nl.xs4all.banaan.tst8.GenericBinding;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.Panel;

public class PropertyPanel extends Panel {
    private static final long serialVersionUID = 1L;
    
    public PropertyPanel(String id, List<GenericBinding<String>> props) {
        super(id);
        getSession().info("building  property panel");
        add (new PropertyListView ("props", props) {
            private static final long serialVersionUID = 1L;
           
            @Override
            public void populateItem (ListItem item) {
                item.add(new Label("key"));
                item.add(new Label("value"));
            }
        });
    }
}
