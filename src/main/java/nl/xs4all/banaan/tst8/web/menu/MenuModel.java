package nl.xs4all.banaan.tst8.web.menu;

import java.util.List;
import java.util.Map.Entry;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.LoadableDetachableModel;

/** model that returns possible menu items. */
public class MenuModel extends LoadableDetachableModel<List<Entry<String, Class<? extends Panel>>>> {
    private static final long serialVersionUID = 1328703313038270829L;

    @Override
    public List<Entry<String,Class<? extends Panel>>> load () {
        return MenuList.getList();
    }
   

}
