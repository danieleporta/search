package nl.xs4all.banaan.tst8.web.form;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class FormPanel extends Panel {
    private static final long serialVersionUID = -190918054405562036L;
    
    public FormPanel(String id, IModel model) {
        super(id, model);
        add(new TextField("text"));
    }
}
