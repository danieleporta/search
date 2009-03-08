package nl.xs4all.banaan.tst8.web.form;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
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

            @Override
            protected void onError() {
                ValueMap map = (ValueMap) getModelObject();
                map.put("errorSeen", "true");
            }
            
        };
        
        add(form);
        form.add(new TextField("text", String.class).setRequired(true));
        
        // marked up as submit button
        form.add(new Button("button1", model) {
            private static final long serialVersionUID = -2440693329626812016L;

            @Override
            public void onSubmit() {
                ValueMap map = (ValueMap) getModelObject();
                map.put("button1Seen", "true");
            }
        });
        
        // marked up as plain button
        form.add(new Button("button2", model) {
            private static final long serialVersionUID = -2440693329626812016L;

            @Override
            public void onSubmit() {
                ValueMap map = (ValueMap) getModelObject();
                map.put("button2Seen", "true");
            }
        });
        
        // a link, marked up as plain button
        form.add(new Link("button3", model) {
            private static final long serialVersionUID = -2538713712765931224L;

            @Override
            public void onClick() {
                ValueMap map = (ValueMap) getModelObject();
                map.put("button3Seen", "true");
            }
        });
        
        // marked up as plain button, without form processing
        form.add(new Button("button4", model) {
            private static final long serialVersionUID = -2440693329626812016L;

            @Override
            public void onSubmit() {
                ValueMap map = (ValueMap) getModelObject();
                map.put("button4Seen", "true");
            }
        }.setDefaultFormProcessing(false));

        form.add(new Label("submitSeen"));
        form.add(new Label("errorSeen"));
        form.add(new Label("button1Seen"));
        form.add(new Label("button2Seen"));
        form.add(new Label("button3Seen"));
        form.add(new Label("button4Seen")); 
    }
}
