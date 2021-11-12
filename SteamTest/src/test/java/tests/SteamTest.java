package tests;

import org.testng.annotations.Test;
import pages.ActionPage;
import pages.GamePage;
import pages.HomePage;
import pages.SteamDownload;

public class SteamTest extends BaseTest {

    @Test
    public void start(){
        HomePage homePage = new HomePage();
        homePage.getLanguage();
        homePage.isRightPageOpenedAssertion();
        homePage.mainLabelNavigation();
        homePage.subsectionChoice();

        ActionPage actionPage = new ActionPage();
        actionPage.isRightPageOpenedAssertion();
        actionPage.lookingForBiggestSale();
        actionPage.ageCheck();

        GamePage gamePage = new GamePage();
        gamePage.isRightPageOpenedAssertion();
        gamePage.installSteamBtnClick();

        SteamDownload steamDownload = new SteamDownload();
        steamDownload.isRightPageOpenedAssertion();
        steamDownload.downloadSteam();
    }
}
