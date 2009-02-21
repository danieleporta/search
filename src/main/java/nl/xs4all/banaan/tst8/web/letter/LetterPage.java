package nl.xs4all.banaan.tst8.web.letter;

import org.apache.wicket.model.LoadableDetachableModel;

import nl.xs4all.banaan.tst8.domain.Address;
import nl.xs4all.banaan.tst8.domain.Letter;
import nl.xs4all.banaan.tst8.service.ServiceException;
import nl.xs4all.banaan.tst8.web.base.BasePage;

public class LetterPage extends BasePage {
    public LetterPage() {
        init();
    }
    
    @Override
    public void doInit() throws ServiceException {
        add(new LetterPanel("letter", new LetterModel()));
    }
    
    private static class LetterModel extends LoadableDetachableModel {
        private static final long serialVersionUID = 3207586619607652567L;

        @Override
        protected Object load() {
            return new Letter (
                    new Address("twiet", "aap", "noot"),
                    new Address("mies", "wim", "zus"),
                    43L);
        }
        
    }

}
