package nl.xs4all.banaan.tst8.web.letter;

import nl.xs4all.banaan.tst8.fixtures.SpringJUnitWicketTest;

import org.junit.Test;

public class LetterPageTest extends SpringJUnitWicketTest {
    
    @Test
    public void testRenderMyPage() {
        tester.startPage(LetterPage.class);
        tester.checkBasePage(LetterPage.class, "twiet");
    }

}
