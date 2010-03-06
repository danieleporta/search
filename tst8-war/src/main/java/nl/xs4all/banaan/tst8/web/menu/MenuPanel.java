package nl.xs4all.banaan.tst8.web.menu;

import java.util.List;
import java.util.Map.Entry;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

/**
 * Show links to the various environments the visitor may be interested in
 * @author konijn
 *
 */
public class MenuPanel extends Panel {
    private static final long serialVersionUID = 1L;
    
    public MenuPanel(String id) {
        super(id);
        getSession().info("building  menu panel");

        final IModel<List<Entry<String, Class<? extends Panel>>>> model = new MenuModel();
        
        add (new PropertyListView<Entry<String, Class<? extends Panel>>>("bindings", model) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem (ListItem<Entry<String, Class<? extends Panel>>> item) {
                Entry<String, Class<? extends Panel>> assoc = item.getModelObject();
                
                final PageParameters parameters = new PageParameters();
                parameters.put("panel", assoc.getKey());
                final BookmarkablePageLink<Void> pageLink = new BookmarkablePageLink<Void>("value", 
                        MenuPage.class,
                        parameters);
                pageLink.add(new Label("key"));
                item.add(pageLink);
            }
        });
    }
}
