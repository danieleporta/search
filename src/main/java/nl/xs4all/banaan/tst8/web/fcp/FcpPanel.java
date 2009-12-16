package nl.xs4all.banaan.tst8.web.fcp;



import nl.xs4all.banaan.tst8.domain.Address;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.ComponentPropertyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;

public class FcpPanel extends Panel {
    private static final long serialVersionUID = 8379326263858269585L;

    public static Address theObject = new Address("twiet", "aap", "noot");
    
    public FcpPanel(String panelId) {
        this (panelId, new FcpPanel.AddressModel());
    }
    
    @SuppressWarnings("serial")
    public FcpPanel(String panelId, IModel<Address> model) {
        super(panelId);
        
        Form<Address> form = new Form<Address>("form", 
                new CompoundPropertyModel<Address>(model)) {
                    @Override
                    protected void onSubmit() {
                        System.out.println("name: " + theObject.getName());
                        System.out.println("street: " + theObject.getStreet());
                    }
            
        };
        add(form);
        
        form.add(new MyFcp("name", "What is the name?"));
        form.add(new MyFcp("street", "What is the street?"));
        
    }
    
    private static class AddressModel extends LoadableDetachableModel<Address> {
        private static final long serialVersionUID = 3207586619607652567L;
        
        @Override
        protected Address load() {
            return theObject;
        }
    }
}
