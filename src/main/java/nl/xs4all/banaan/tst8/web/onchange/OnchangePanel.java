package nl.xs4all.banaan.tst8.web.onchange;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.value.ValueMap;

/**
 * Show how an AJAX callback may be used to put content
 * in another field.  The canonical use case is filling
 * in street and city when zipcode is entered.
 * @author konijn
 *
 */
public class OnchangePanel extends Panel {
    private static final long serialVersionUID = 4815145731202521073L;
    public OnchangePanel(String id, IModel model) {
        super(id, model);
        getSession().info("building onchange panel");
        
        final Form form = new Form("form", model) {
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
        
        TextField field = new TextField("zipcode");
        
        // This happens after leaving the field 
        // (with tab, return or click elsewhere).
        //
        // After updating target, focus is lost.
        // An alternative is target.addFocus(),
        // which risks unintended overwrite of prefill.
        //
        field.add(new AjaxFormComponentUpdatingBehavior("onchange"){
            private static final long serialVersionUID = 3982553539546743877L;

            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                ValueMap map = (ValueMap) getModelObject();
                map.put("changeSeen", "true");
                map.put("street", "prefill");
                target.addComponent(form);
            }
        });
        form.add (field);
        
        // second field with onchange behaviour, to testinteraction with required.
        TextField zip2 = new TextField("zipcode2");
        zip2.setRequired(true);
        zip2.add(new AjaxFormComponentUpdatingBehavior("onchange"){
            private static final long serialVersionUID = 3982553539546743877L;

            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                ValueMap map = (ValueMap) getModelObject();
                map.put("changeSeen", "true");
                map.put("street", "prefill2");
                target.addComponent(form);
            }
        });
        form.add (zip2);
        
        form.add (new TextField("street"));
        form.add((new Label("submitSeen")));
        form.add((new Label("changeSeen")));
        form.add((new Label("errorSeen")));
        form.add((new Label("streetSeen", new PropertyModel(model, "street"))));
        form.add((new Label("zip2Seen", new PropertyModel(model, "zipcode2"))));
    }
}
