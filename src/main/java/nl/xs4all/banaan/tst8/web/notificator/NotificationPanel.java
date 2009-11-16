package nl.xs4all.banaan.tst8.web.notificator;

import nl.xs4all.banaan.tst8.service.Notification;
import nl.xs4all.banaan.tst8.service.Notificator;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.validation.validator.EmailAddressValidator;

import com.google.inject.Inject;

public class NotificationPanel extends Panel {
    private static final long serialVersionUID = 1L;
    
    @Inject private Notificator notificator;
    
    private Notification notification;

    public NotificationPanel(String id) {
        super(id);
        notification = new Notification();
        
        final Form<Notification> form = new Form<Notification>("form", new CompoundPropertyModel<Notification>(notification)) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit() {
                Notification notification = getModelObject();
                notificator.send(notification);
            }
        };
        form.add(new TextField<String>("to")
                .setRequired(true)
                .add(EmailAddressValidator.getInstance()));
        form.add(new TextField<String>("subject").setRequired(true));
        form.add(new TextArea<String>("body").setRequired(true));
        form.add(new Button("confirm"));
        add (form);
    }
}
