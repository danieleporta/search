package nl.xs4all.banaan.tst8.web.buildInfo;

import nl.xs4all.banaan.tst8.web.DemoApplication;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;

public class BuildInfoPanel extends Panel {
    private static final long serialVersionUID = -1202632910577552959L;

    public BuildInfoPanel(String id) {
        super(id);

        setModel(new CompoundPropertyModel(new LoadableDetachableModel(){
            private static final long serialVersionUID = 1L;

            @Override
            protected Object load() {
                return DemoApplication.get().getServices().getBuildInfo();
            }
        }));
        add (new Label("name"));
        add (new Label("group"));
        add (new Label("version"));
        add (new Label("user"));
    }
}
