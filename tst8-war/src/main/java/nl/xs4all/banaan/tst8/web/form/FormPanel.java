package nl.xs4all.banaan.tst8.web.form;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.value.ValueMap;


public class FormPanel extends Panel {
    private static final long serialVersionUID = -190918054405562036L;
    
    /**
     * these may be triggered by some button
     */
    public static final String[] allEvents = { "submitSeen", "errorSeen",
            "buttonBeforeSubmit", "button1Seen", "button2Seen", "button3Seen",
            "button4Seen", "button5Seen" };


    /**
     * Default content for the map that serves as model for the panel.
     * @return
     */
    private static ValueMap makeMap() {
        ValueMap map = new ValueMap("text=");
        for (String key: FormPanel.allEvents) {
            map.add(key, "false");
        }
        return map;
    }

    
    public FormPanel(String id) {
        this (id, new CompoundPropertyModel<ValueMap>(makeMap()));
    }
    
    public FormPanel(String id, IModel<ValueMap> model) {
        super(id, model);
        final Form<ValueMap> form = new Form<ValueMap>("form", model) {
            private static final long serialVersionUID = -5412242628570636316L;

            @Override
            protected void onSubmit() {
                ValueMap map = getModelObject();
                map.put("submitSeen", "true");
            }

            @Override
            protected void onError() {
                ValueMap map = getModelObject();
                map.put("errorSeen", "true");
            }
            
        };
        
        add(form);
        form.add(new TextField<String>("text", String.class).setRequired(true));
        
        // marked up as submit button
        form.add(new Button("button1") {
            private static final long serialVersionUID = -2440693329626812016L;

            @Override
            public void onSubmit() {
                // note that the model of the button is used to display text on it.
                // here we record an action in model object of enclosing form.
                ValueMap map = form.getModelObject();
                map.put("button1Seen", "true");
            }
        });
        
        // marked up as plain button
        form.add(new Button("button2") {
            private static final long serialVersionUID = -2440693329626812016L;

            @Override
            public void onSubmit() {
                ValueMap map = form.getModelObject();
                map.put("button2Seen", "true");
                map.put("buttonBeforeSubmit",
                        map.get("submitSeen").equals("true")
                            ? "false"
                            : "true");
            }
        });
        
        // a link, marked up as plain button
        form.add(new Link<ValueMap>("button3", model) {
            private static final long serialVersionUID = -2538713712765931224L;

            @Override
            public void onClick() {
                ValueMap map = getModelObject();
                map.put("button3Seen", "true");
            }
        });
        
        // marked up as plain button, without form processing
        form.add(new Button("button4") {
            private static final long serialVersionUID = -2440693329626812016L;

            @Override
            public void onSubmit() {
                ValueMap map = form.getModelObject();
                map.put("button4Seen", "true");
            }
        }.setDefaultFormProcessing(false));
        
        // ajax submit link can be outside form 
        add(new AjaxSubmitLink("button5", form) {
            private static final long serialVersionUID = -2473039751385349081L;

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                ValueMap map = (ValueMap) getDefaultModelObject();
                map.put("button5Seen", "true");
            }
        });

        form.add(new Label("submitSeen"));
        form.add(new Label("errorSeen"));
        form.add(new Label("buttonBeforeSubmit"));
        form.add(new Label("button1Seen"));
        form.add(new Label("button2Seen"));
        form.add(new Label("button3Seen"));
        form.add(new Label("button4Seen"));
        form.add(new Label("button5Seen"));
    }
}
