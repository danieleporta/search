package nl.xs4all.banaan.tst8.web.param;


import nl.xs4all.banaan.tst8.service.ParamList;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

/**
 * The actual panel with initialisation parameters provided by servlet context.
 * @author konijn
 *
 */
public class ParamPanel extends Panel {
    private static final long serialVersionUID = 1L;
    
    public ParamPanel(String id) {
        super(id);
        getSession().info("building  param panel");

        // make model dynamic to avoid it being serialized into the session
        Model model = new Model () {
            private static final long serialVersionUID = 1L;
            @Override
            public Object getObject () {
                ParamList paramList = new ParamList();
                return paramList.getList();
            }
        };
        
        add (new PropertyListView("bindings", model) {
            private static final long serialVersionUID = 1L;
           
            @Override
            protected void populateItem (ListItem item) {
                item.add(new Label("key"));
                item.add(new Label("value"));
            }
        });
    }
}
