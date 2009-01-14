package nl.xs4all.banaan.tst8.web.property;

import nl.xs4all.banaan.tst8.service.PropertyList;
import nl.xs4all.banaan.tst8.service.ServiceException;
import nl.xs4all.banaan.tst8.web.DemoApplication;
import nl.xs4all.banaan.tst8.web.base.BasePage;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;


/**
 * Display key/value pairs in a property collection.
 * takes a param to determine what property resource is shown.
 * @author konijn
 *
 */

public class PropertyPage extends BasePage {
    private String location;
    private TextField field;
    private Form form;
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public PropertyPage() {
        setLocation("");
        init();
    }
    
    public PropertyPage(String location) {
        setLocation(location);
        init();
    }

    public PropertyPage(PageParameters parameters) {
        setLocation(parameters.getString("location", ""));
        init();
    }

    @Override
    public void doInit() throws ServiceException {
        form = new Form("form");
        field = new TextField("field", 
                new Model(location));
        form.add(field);
        form.add(new Button("confirm") {
            @Override
            public void onSubmit() {
                String result = field.getModelObjectAsString();
                setResponsePage(PropertyPage.class, 
                        new PageParameters("location="+result));
            }
        });
        add(form);
        
        PropertyList propertyList = 
            DemoApplication.get().getPropertyReader().read(location);
        add(new PropertyPanel("properties", propertyList.getList()));
    }
}
