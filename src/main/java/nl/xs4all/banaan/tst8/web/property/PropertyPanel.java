package nl.xs4all.banaan.tst8.web.property;

import java.util.List;

import nl.xs4all.banaan.tst8.service.PropertyReader;
import nl.xs4all.banaan.tst8.util.Assoc;
import nl.xs4all.banaan.tst8.util.Either;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

import com.google.inject.Inject;

/**
 * Display key/value pairs in some property set.
 * @author konijn
 *
 */
public class PropertyPanel extends Panel {
    private static final long serialVersionUID = 1L;
    
    private TextField<String> field;
    private Form<Void> form;
    
    @Inject PropertyReader propertyReader;

    private final PropertyModel propertyModel;

    public PropertyPanel(String id) {
        this(id, "");
    }
    
    public PropertyPanel(String id, final String location) {
        super(id);
        getSession().info("building  property panel");

        propertyModel = new PropertyModel(propertyReader, location);
        setDefaultModel(new CompoundPropertyModel<Either<List<Assoc<String>>,String>>(propertyModel));
        
        
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
        
        PropertyListView<Assoc<String>> propertyListView = new PropertyListView<Assoc<String>> ("good") {
            private static final long serialVersionUID = 1L;
           
            @Override
            public void populateItem (ListItem<Assoc<String>> item) {
                item.add(new Label("key"));
                item.add(new Label("value"));
            }
            
            @Override
            public boolean isVisible() {
                return propertyModel.getObject().isGood();
            }
        };
        add(propertyListView);
        
        // perhaps cleaner to do this via getSession.error?
        Label label = new Label("bad") {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isVisible() {
                return ! propertyModel.getObject().isGood();
            }            
        };
        add(label);
        
    }
}
