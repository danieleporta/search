package nl.xs4all.banaan.tst8.web.letter;


import nl.xs4all.banaan.tst8.service.ServiceException;
import nl.xs4all.banaan.tst8.web.base.BasePage;

public class LetterPage extends BasePage {
    public LetterPage() {
        init();
    }
    
    @Override
    public void doInit() throws ServiceException {
        add(new LetterPanel("letter"));
    }

}
