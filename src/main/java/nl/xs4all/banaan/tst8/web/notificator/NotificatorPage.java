package nl.xs4all.banaan.tst8.web.notificator;

import nl.xs4all.banaan.tst8.service.Notification;
import nl.xs4all.banaan.tst8.web.base.BasePage;

/**
 * Notification page is where you can send E-mails.
 * @author konijn
 *
 */
public class NotificatorPage extends BasePage {
   
    private Notification notification;

    public NotificatorPage() {
        init();
    }
    
    @Override
    public void doInit() {
        notification = new Notification();
        add(new NotificationPanel("notification", notification));
    }
}
