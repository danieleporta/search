package nl.xs4all.banaan.tst8;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

public class PropertyPanel extends Panel {
    List<Binding> props;
    
    public PropertyPanel(String id, List<Binding> props) {
        super(id);
        this.props = props;
        add (new ListView ("props", this.props) {
            private static final long serialVersionUID = 1L;

            @Override
            public void populateItem (ListItem item) {
                Binding b = (Binding) item.getModelObject();
                item.add(new Label("key", b.key));
                item.add(new Label("value", b.value));
            }
        });
    }

    private static final long serialVersionUID = 1L;

}
