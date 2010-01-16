package nl.xs4all.banaan.tst8.web.property;

import java.util.List;

import nl.xs4all.banaan.tst8.service.PropertyReader;
import nl.xs4all.banaan.tst8.util.Assoc;

import org.apache.log4j.Logger;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.google.inject.Inject;

/**
 * Display key/value pairs in some property set.
 * @author konijn
 *
 */
public class PropertyPanel extends Panel {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(PropertyPanel.class);
    
    private TextField<String> field;
    private Form<Void> form;
    
    @Inject PropertyReader propertyReader;

    public PropertyPanel(String id) {
        this(id, "");
    }
    
    public PropertyPanel(String id, final String location) {
        super(id);
        getSession().info("building  property panel");
        
        IModel<List<Assoc<String>>> model = new PropertyModel(propertyReader, logger, location);
        
        form = new Form<Void>("form") {
            private static final long serialVersionUID = 1L;
            
            @Override
            public void onSubmit() {
                String result = field.getModelObject();
                setResponsePage(PropertyPage.class, 
                        new PageParameters("location="+result));
            }
        };
        field = new TextField<String>("field", 
                new Model<String>(location));
        form.add(field);

        add(form);
        
        add (new PropertyListView<Assoc<String>> ("props", model) {
            private static final long serialVersionUID = 1L;
           
            @Override
            public void populateItem (ListItem<Assoc<String>> item) {
                item.add(new Label("key"));
                item.add(new Label("value"));
            }
        });
    }
}
