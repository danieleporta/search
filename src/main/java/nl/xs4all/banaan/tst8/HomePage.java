package nl.xs4all.banaan.tst8;

import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;

/**
 * Homepage
 */
public class HomePage extends WebPage {

    private static final long serialVersionUID = 1L;
    private Properties sysProps;

    /**
     * thingy to keep key and value together in list view.
     */
    private final class Binding {
        String key;
        String value;
        
        Binding (String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
    
    private List<Binding> propertyBindingList (Properties props){
        List<Binding> result = new LinkedList<Binding>();
        for (Enumeration<?> e = props.propertyNames(); e.hasMoreElements();) {
            String name = (String) e.nextElement();
            String value = props.getProperty(name);
            result.add (new Binding (name, value));
        }
        return result;
    }

    /**
     * Constructor that is invoked when page is invoked without a session.
     * 
     * @param parameters
     *            Page parameters
     */
    public HomePage(final PageParameters parameters) {
        // this has the old enum interface
        sysProps = System.getProperties();
        
        // Add the simplest type of label
        add(new Label("message",
                "If you see this message wicket is properly configured and running"));

        add (new ListView ("props", propertyBindingList(sysProps)) {
            private static final long serialVersionUID = 1L;

            @Override
            public void populateItem (ListItem item) {
                Binding b = (Binding) item.getModelObject();
                item.add(new Label("key", b.key));
                item.add(new Label("value", b.value));
            }
        });
    }
}
