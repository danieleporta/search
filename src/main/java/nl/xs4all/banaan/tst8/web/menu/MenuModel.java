package nl.xs4all.banaan.tst8.web.menu;

import java.util.List;

import nl.xs4all.banaan.tst8.util.Assoc;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.LoadableDetachableModel;

/** model that returns possible menu items. */
public class MenuModel extends LoadableDetachableModel<List<Assoc<Class<? extends Panel>>>> {
    private static final long serialVersionUID = 1328703313038270829L;

    @Override
    public List<Assoc<Class<? extends Panel>>> load () {
        return MenuList.getList();
    }
   

}
