package nl.xs4all.banaan.tst8.web.upload;

import nl.xs4all.banaan.tst8.service.ServiceException;
import nl.xs4all.banaan.tst8.web.base.BasePage;

/**
 * Container for an upload panel.
 * @author konijn
 *
 */
public class UploadPage extends BasePage {
    public UploadPage() {
        init();
    }
    
    @Override
    public void doInit() throws ServiceException {
        add (new UploadPanel("upload"));
    }
}
