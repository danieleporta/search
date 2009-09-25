package nl.xs4all.banaan.tst8.web.letter;

import javax.annotation.Resource;

import nl.xs4all.banaan.tst8.fixtures.BasePageTester;
import nl.xs4all.banaan.tst8.web.DemoApplication;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/testContext.xml"})
public class LetterPageTest {
    @Resource
    private DemoApplication demoApplication;
    
    private BasePageTester tester;

    @Before
    public void setUp() {
        tester = new BasePageTester(demoApplication);
    }
    
    @Test
    public void testRenderMyPage() {
        tester.startPage(LetterPage.class);
        tester.checkBasePage(LetterPage.class, "twiet");
    }

}
