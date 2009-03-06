package nl.xs4all.banaan.tst8.web.menu;

import nl.xs4all.banaan.tst8.util.GenericBinding;
import nl.xs4all.banaan.tst8.web.DemoApplication;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

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

        IModel model = new LoadableDetachableModel () {
            private static final long serialVersionUID = 1328703313038270829L;

            @Override
            public Object load () {
                return DemoApplication.get().getMenuList().getList();
            }
        };
        
        add (new PropertyListView("bindings", model) {
            private static final long serialVersionUID = 1L;
           
            @Override
            protected void populateItem (ListItem item) {
                GenericBinding<Class<? extends WebPage>> binding = 
                    (GenericBinding<Class<? extends WebPage>>) item.getModelObject();
                item.add(new BookmarkablePageLink("value", binding.getValue()).
                    add(new Label("key")));
            }
        });
    }
}
