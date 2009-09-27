package nl.xs4all.banaan.tst8.web.base;


import nl.xs4all.banaan.tst8.fixtures.SpringJUnitWicketTest;

import org.junit.Test;

public class MenuPageTest extends SpringJUnitWicketTest {

    @Test
    public void testConstruction() {
        tester.startPage(MenuPage.class);
        tester.assertRenderedPage(MenuPage.class);
    }
}
