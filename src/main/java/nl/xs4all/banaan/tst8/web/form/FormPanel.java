package nl.xs4all.banaan.tst8.web.form;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.value.ValueMap;


public class FormPanel extends Panel {
    private static final long serialVersionUID = -190918054405562036L;
    
    public FormPanel(String id, IModel model) {
        super(id, model);
        Form form = new Form("form", model) {
            private static final long serialVersionUID = -5412242628570636316L;

            @Override
            protected void onSubmit() {
                ValueMap map = (ValueMap) getModelObject();
                map.put("submitSeen", "true");
            }
        };
        add(form);
        form.add(new TextField("text"));
        form.add(new Label("submitSeen"));
    }
}
