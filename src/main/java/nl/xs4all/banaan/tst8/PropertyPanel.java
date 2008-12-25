package nl.xs4all.banaan.tst8;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

public class PropertyPanel extends Panel {
    List<Binding> props;
    
    public PropertyPanel(String id, List<Binding> props) {
        super(id);
        this.props = props;
        add (new ListView ("props", this.props) {
            private static final long serialVersionUID = 1L;
            
            @Override
            protected IModel getListItemModel(final IModel listViewModel, final int index)
            {
                return new CompoundPropertyModel(super.getListItemModel(listViewModel, index));
            }
            
            @Override
            public void populateItem (ListItem item) {
                item.add(new Label("key"));
                item.add(new Label("value"));
            }
        });
    }

    private static final long serialVersionUID = 1L;

}
