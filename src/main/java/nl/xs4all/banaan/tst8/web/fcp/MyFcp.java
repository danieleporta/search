package nl.xs4all.banaan.tst8.web.fcp;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;

/**
 * On the outside, this looks like a FormComponent,
 * on the inside it is a panel.
 * 
 * @author konijn
 *
 */
public class MyFcp extends FormComponentPanel<String> {
    private static final long serialVersionUID = 1L;

    /** 
     * field equiped with getter and setter means that MyFcp
     * can be placed behind a property model and receive updates
     * from the enclosed textfield.
     * 
     */
    private String inputValue = "aap";
    private TextField<String> input;

    public String getInputValue() {
        return inputValue;
    }
    public void setInputValue(String inputValue) {
        this.inputValue = inputValue;
    }

    public MyFcp(String id) {
        super(id);
        add(new Label("prompt", "Prompt goes here"));
        input = new TextField<String>("input", new PropertyModel<String>(this, "inputValue"));
        add(input);
    }
   
    /**
     * convert input; probable would have to do validation here as well.
     * this will end up in model provided by enclosing component.
     */
    @Override
    protected void convertInput() {
        setConvertedInput(input.getConvertedInput());
    }
}
