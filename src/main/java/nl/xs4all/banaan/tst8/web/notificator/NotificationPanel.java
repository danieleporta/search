package nl.xs4all.banaan.tst8.web.notificator;

import nl.xs4all.banaan.tst8.service.Notification;
import nl.xs4all.banaan.tst8.web.DemoApplication;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.validation.validator.EmailAddressValidator;

public class NotificationPanel extends Panel {
    private static final long serialVersionUID = 1L;
    
    private Notification notification;

    public NotificationPanel(String id) {
        super(id);
        notification = new Notification();
        
        Form form = new Form("form", new CompoundPropertyModel(notification)) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit() {
                Notification notification = (Notification) getModelObject();
                DemoApplication.get().
                        getServices().getNotificator().send(notification);
            }
        };
        form.add(new TextField("to")
                .setRequired(true)
                .add(EmailAddressValidator.getInstance()));
        form.add(new TextField("subject").setRequired(true));
        form.add(new TextArea("body").setRequired(true));
        form.add(new Button("confirm"));
        add (form);
    }
}
