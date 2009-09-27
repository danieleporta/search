package nl.xs4all.banaan.tst8.web.onchange;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.value.ValueMap;
import org.apache.wicket.validation.validator.PatternValidator;

/**
 * Show how an AJAX callback may be used to put content
 * in another field.  The canonical use case is filling
 * in street and city when zipcode is entered.
 * @author konijn
 *
 */
public class OnchangePanel extends Panel {
    private static final long serialVersionUID = 4815145731202521073L;

    public OnchangePanel(String id) {
        this(id, new CompoundPropertyModel(makeModel()));
    }

    /** default model for this panel */
    public static ValueMap makeModel() {
        return new ValueMap("zipcode=,zipcode2=junk,zipcode3=123,street=,submitSeen=false");
    }
    
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
        
        // onchange is called after leaving the field (with tab, return or click elsewhere).
        // After updating target, focus is lost.  An alternative is target.addFocus(),
        // which risks unintended overwrite of prefill.
        TextField zip = new TextField("zipcode");
        zip.add(new AjaxFormComponentUpdatingBehavior("onchange"){
            private static final long serialVersionUID = 3982553539546743877L;

            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                ValueMap map = (ValueMap) getModelObject();
                map.put("changeSeen", "true");
                map.put("street", "prefill");
                target.addComponent(form);
            }
        });
        form.add (zip);
        
        // second field with onchange behavior, to test interaction with required.
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

            @Override
            protected void onError(AjaxRequestTarget target, RuntimeException e) {
                super.onError(target, e);
                // get here if no exception in onUpdate(),
                // eg validation error or required-but-missing.
                ValueMap map = (ValueMap) getModelObject();
                map.put("errorSeen", "inZip2");
                target.addComponent(form);
            }
        });
        form.add (zip2);
        
        // third ajax field, this one both required and with validator
        TextField zip3 = new TextField("zipcode3");
        zip3.setRequired(true);
        zip3.add(new PatternValidator("\\d+"));
        zip3.add(new AjaxFormComponentUpdatingBehavior("onchange"){
            private static final long serialVersionUID = 3982553539546743877L;

            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                ValueMap map = (ValueMap) getModelObject();
                map.put("changeSeen", "true");
                map.put("street", "prefill3");
                target.addComponent(form);
            }

            @Override
            protected void onError(AjaxRequestTarget target, RuntimeException e) {
                super.onError(target, e);
                // get here if no exception in onUpdate(),
                // eg validation error or required-but-missing.
                ValueMap map = (ValueMap) getModelObject();
                map.put("errorSeen", "inZip3");
                map.put("street", "that was an error");
                target.addComponent(form);
            }
        });
        form.add (zip3);
        
        form.add (new TextField("street"));
        form.add((new Label("submitSeen")));
        form.add((new Label("changeSeen")));
        form.add((new Label("errorSeen")));
        form.add((new Label("streetSeen", new PropertyModel(model, "street"))));
        form.add((new Label("zip2Seen", new PropertyModel(model, "zipcode2"))));
        form.add((new Label("zip3Seen", new PropertyModel(model, "zipcode3"))));
    }
}
