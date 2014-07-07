package nl.xs4all.banaan.tst8.web.param;


import java.util.List;

import nl.xs4all.banaan.tst8.service.ParamReader;
import nl.xs4all.banaan.tst8.util.Assoc;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import com.google.inject.Inject;

/**
 * Panel with initialisation parameters provided by servlet context.
 */
public class ParamPanel extends Panel {
    private static final long serialVersionUID = 1L;
    
    @Inject ParamReader paramReader;
    
    public ParamPanel(String id) {
        super(id);
        getSession().info("building  param panel");

        IModel<List<Assoc<String>>> model = new LoadableDetachableModel<List<Assoc<String>>> () {
            private static final long serialVersionUID = 1L;
            @Override
            public List<Assoc<String>> load () {
                return paramReader.read();
            }
        };
        
        add (new PropertyListView<Assoc<String>>("bindings", model) {
            private static final long serialVersionUID = 1L;
           
            @Override
            protected void populateItem (ListItem<Assoc<String>> item) {
                item.add(new Label("key"));
                item.add(new Label("value"));
            }
        });
    }
}
