package nl.xs4all.banaan.tst8.web.buildInfo;

import nl.xs4all.banaan.tst8.service.BuildInfo;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;

import com.google.inject.Inject;

public class BuildInfoPanel extends Panel {
    private static final long serialVersionUID = -1202632910577552959L;
    @Inject BuildInfo buildInfo;


    public BuildInfoPanel(String id) {
        super(id);
        setDefaultModel(new CompoundPropertyModel<BuildInfo>(buildInfo));
        add (new Label("name"));
        add (new Label("group"));
        add (new Label("version"));
        add (new Label("user"));
    }
}
