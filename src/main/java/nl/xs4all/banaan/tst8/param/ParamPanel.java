package nl.xs4all.banaan.tst8.param;

import java.util.List;

import nl.xs4all.banaan.tst8.ParamBinding;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.Panel;

public class ParamPanel extends Panel {
    private static final long serialVersionUID = 1L;
    
    public ParamPanel(String id) {
        super(id);
        getSession().info("building  param panel");
        
        List<ParamBinding> params = ParamBinding.list();

        add (new PropertyListView("bindings", params) {
            private static final long serialVersionUID = 1L;
           
            @Override
            protected void populateItem (ListItem item) {
                item.add(new Label("key"));
                item.add(new Label("value"));
            }
        });
    }
}
