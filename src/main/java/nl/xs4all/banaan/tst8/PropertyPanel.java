package nl.xs4all.banaan.tst8;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.Panel;

public class PropertyPanel extends Panel {
    List<PropertyBinding> props;
    
    public PropertyPanel(String id, List<PropertyBinding> props) {
        super(id);
        getSession().info("building  property panel");
        this.props = props;
        add (new PropertyListView ("props", this.props) {
            private static final long serialVersionUID = 1L;
           
            @Override
            public void populateItem (ListItem item) {
                item.add(new Label("key"));
                item.add(new Label("value"));
            }
        });
    }

    private static final long serialVersionUID = 1L;

}
